package com.firemerald.additionalplacements.commands;

import java.util.Set;

import com.firemerald.additionalplacements.block.interfaces.IStairBlock;
import com.firemerald.additionalplacements.util.ComplexFacing;
import com.firemerald.additionalplacements.util.stairs.StairShape;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.blocks.BlockInput;
import net.minecraft.commands.arguments.blocks.BlockStateArgument;
import net.minecraft.commands.arguments.coordinates.BlockPosArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;

public class CommandGenerateStairsDebugger
{
	public static void register(CommandDispatcher<CommandSourceStack> dispatch)
	{
		dispatch.register(Commands.literal("ap_stairs_state_debug")
				.requires(source -> source.hasPermission(2))
				.then(Commands.argument("pos", BlockPosArgument.blockPos())
						.then(Commands.argument("block", BlockStateArgument.block())
								.executes(context -> {
									BlockPos center = BlockPosArgument.getLoadedBlockPos(context, "pos");
									BlockInput blockInput = BlockStateArgument.getBlock(context, "block");
									if (blockInput.getState().getBlock() instanceof IStairBlock stair && stair.hasAdditionalStates()) {
										ServerLevel serverLevel = context.getSource().getLevel();
										int minorOffset = StairShape.ALL_SHAPES.length + 1;
										int majorOffset = minorOffset + 2;
										BlockPos.MutableBlockPos middle = new BlockPos.MutableBlockPos();
										BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();
										for (int x = center.getX() - majorOffset - 1; x <= center.getX() + majorOffset + 1; ++x) {
											for (int y = center.getY() - majorOffset - 1; y <= center.getY() + majorOffset + 1; ++y) {
												for (int z = center.getZ() - majorOffset - 1; z <= center.getZ() + majorOffset + 1; ++z) {
													pos.set(x, y, z);
													serverLevel.setBlock(pos, Blocks.AIR.defaultBlockState(), 2);
												}
											}
										}
										for (ComplexFacing facing : ComplexFacing.ALL_FACING) {
											BlockState state = stair.getBlockState(facing, StairShape.STRAIGHT, blockInput.getState());
											middle.set(
													center.getX() + facing.up.getStepX() * majorOffset + facing.forward.getStepX() * minorOffset,
													center.getY() + facing.up.getStepY() * majorOffset + facing.forward.getStepY() * minorOffset,
													center.getZ() + facing.up.getStepZ() * majorOffset + facing.forward.getStepZ() * minorOffset
													);
											CompoundTag tag = null; //TODO tag?
											new BlockInput(state, blockInput.getDefinedProperties(), tag).place(serverLevel, middle, 2);
											{
												set(serverLevel, stair, state, blockInput.getDefinedProperties(), tag, facing, middle, pos, 2 ,  1,  0);
												set(serverLevel, stair, state, blockInput.getDefinedProperties(), tag, facing, middle, pos, 4 ,  0,  1);
												set(serverLevel, stair, state, blockInput.getDefinedProperties(), tag, facing, middle, pos, 6 ,  1,  1);
												set(serverLevel, stair, state, blockInput.getDefinedProperties(), tag, facing, middle, pos, 8 , -1,  0);
												set(serverLevel, stair, state, blockInput.getDefinedProperties(), tag, facing, middle, pos, 10,  0, -1);
												set(serverLevel, stair, state, blockInput.getDefinedProperties(), tag, facing, middle, pos, 12, -1, -1);
												setTwist(serverLevel, stair, state, blockInput.getDefinedProperties(), tag, facing, middle, pos, 14);
											}
										}
									}
									else throwInvalidBlock(blockInput.getState());
									return 1;
								}))));
	}
	
	private static void throwInvalidBlock(BlockState state) throws CommandSyntaxException {
		throw new SimpleCommandExceptionType(new TranslatableComponent("commands.ap_stairs_state_debug.wrong_block", Registry.BLOCK.getKey(state.getBlock()))).create();
	}
	
	private static void set(ServerLevel serverLevel, IStairBlock<?> stair, BlockState rootState, Set<Property<?>> props, CompoundTag tag, ComplexFacing facing, BlockPos middle, BlockPos.MutableBlockPos pos, int offset, int offFront, int offTop) {
		new BlockInput(rootState, props, tag).place(serverLevel, pos.set(middle).move(facing.left, offset), 2);
		new BlockInput(rootState, props, tag).place(serverLevel, pos.set(middle).move(facing.right, offset), 2);
		if (offFront != 0) {
			new BlockInput(stair.getBlockState(ComplexFacing.forFacing(facing.left, facing.up), 
					StairShape.STRAIGHT, rootState), props, tag).place(serverLevel, pos.set(middle).move(facing.left, offset).move(facing.forward, offFront), 2);
			new BlockInput(stair.getBlockState(ComplexFacing.forFacing(facing.right, facing.up), 
					StairShape.STRAIGHT, rootState), props, tag).place(serverLevel, pos.set(middle).move(facing.right, offset).move(facing.forward, offFront), 2);
		}
		if (offTop != 0) {
			new BlockInput(stair.getBlockState(ComplexFacing.forFacing(facing.forward, facing.left), 
					StairShape.STRAIGHT, rootState), props, tag).place(serverLevel, pos.set(middle).move(facing.left, offset).move(facing.up, offTop), 2);
			new BlockInput(stair.getBlockState(ComplexFacing.forFacing(facing.forward, facing.right), 
					StairShape.STRAIGHT, rootState), props, tag).place(serverLevel, pos.set(middle).move(facing.right, offset).move(facing.up, offTop), 2);
		}
	}
	
	private static void setTwist(ServerLevel serverLevel, IStairBlock<?> stair, BlockState rootState, Set<Property<?>> props, CompoundTag tag, ComplexFacing facing, BlockPos middle, BlockPos.MutableBlockPos pos, int offset) {
		new BlockInput(rootState, props, tag).place(serverLevel, pos.set(middle).move(facing.left, offset), 2);
		new BlockInput(rootState, props, tag).place(serverLevel, pos.set(middle).move(facing.right, offset), 2);
		new BlockInput(stair.getBlockState(ComplexFacing.forFacing(facing.left, facing.up), 
				StairShape.STRAIGHT, rootState), props, tag).place(serverLevel, pos.set(middle).move(facing.left, offset).move(facing.backward, 1), 2);
		new BlockInput(stair.getBlockState(ComplexFacing.forFacing(facing.right, facing.up), 
				StairShape.STRAIGHT, rootState), props, tag).place(serverLevel, pos.set(middle).move(facing.right, offset).move(facing.backward, 1), 2);
		new BlockInput(stair.getBlockState(ComplexFacing.forFacing(facing.forward, facing.right), 
				StairShape.STRAIGHT, rootState), props, tag).place(serverLevel, pos.set(middle).move(facing.left, offset).move(facing.down, 1), 2);
		new BlockInput(stair.getBlockState(ComplexFacing.forFacing(facing.forward, facing.left), 
				StairShape.STRAIGHT, rootState), props, tag).place(serverLevel, pos.set(middle).move(facing.right, offset).move(facing.down, 1), 2);
	}
}