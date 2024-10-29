package com.firemerald.additionalplacements.command;

import java.util.*;

import com.firemerald.additionalplacements.block.interfaces.IStairBlock;
import com.firemerald.additionalplacements.util.ComplexFacing;
import com.firemerald.additionalplacements.util.stairs.StairShape;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.BlockPosArgument;
import net.minecraft.command.arguments.BlockStateArgument;
import net.minecraft.command.arguments.BlockStateInput;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.state.Property;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.server.ServerWorld;

public class CommandGenerateStairsDebugger
{
    private static final SimpleCommandExceptionType ERROR_WRONG_BLOCK = new SimpleCommandExceptionType(new TranslationTextComponent("commands.ap_stairs_state_debug.wrong_block"));
    
	public static void register(CommandDispatcher<CommandSource> dispatch)
	{
		dispatch.register(Commands.literal("ap_stairs_state_debug")
				.requires(source -> source.hasPermission(2))
				.then(Commands.argument("pos", BlockPosArgument.blockPos())
						.then(Commands.argument("block", BlockStateArgument.block())
								.executes(context -> {
									BlockPos center = BlockPosArgument.getLoadedBlockPos(context, "pos");
									BlockStateInput blockInput = BlockStateArgument.getBlock(context, "block");
									if (blockInput.getState().getBlock() instanceof IStairBlock) {
										IStairBlock<?> stair = (IStairBlock<?>) blockInput.getState().getBlock();
										if (stair.hasAdditionalStates()) {
											ServerWorld serverLevel = context.getSource().getLevel();
											int minorOffset = StairShape.ALL_SHAPES.length + 1;
											int majorOffset = minorOffset + 2;
											BlockPos.Mutable middle = new BlockPos.Mutable();
											BlockPos.Mutable pos = new BlockPos.Mutable();
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
												CompoundNBT tag = null; //TODO tag?
												Set<Property<?>> properties = new HashSet<>(state.getProperties()); //TODO properties
												new BlockStateInput(state, properties, tag).place(serverLevel, middle, 2);
												{
													set(serverLevel, stair, state, properties, tag, facing, middle, pos, 2 ,  1,  0);
													set(serverLevel, stair, state, properties, tag, facing, middle, pos, 4 ,  0,  1);
													set(serverLevel, stair, state, properties, tag, facing, middle, pos, 6 ,  1,  1);
													set(serverLevel, stair, state, properties, tag, facing, middle, pos, 8 , -1,  0);
													set(serverLevel, stair, state, properties, tag, facing, middle, pos, 10,  0, -1);
													set(serverLevel, stair, state, properties, tag, facing, middle, pos, 12, -1, -1);
													setTwist(serverLevel, stair, state, properties, tag, facing, middle, pos, 14);
												}
											}
										}
										else throw ERROR_WRONG_BLOCK.create(); //TODO
									}
									else throw ERROR_WRONG_BLOCK.create(); //TODO
									return 0;
								}))));
	}
	
	private static void set(ServerWorld serverLevel, IStairBlock<?> stair, BlockState rootState, Set<Property<?>> props, CompoundNBT tag, ComplexFacing facing, BlockPos middle, BlockPos.Mutable pos, int offset, int offFront, int offTop) {
		new BlockStateInput(rootState, props, tag).place(serverLevel, pos.set(middle).move(facing.left, offset), 2);
		new BlockStateInput(rootState, props, tag).place(serverLevel, pos.set(middle).move(facing.right, offset), 2);
		if (offFront != 0) {
			new BlockStateInput(stair.getBlockState(ComplexFacing.forFacing(facing.left, facing.up), 
					StairShape.STRAIGHT, rootState), props, tag).place(serverLevel, pos.set(middle).move(facing.left, offset).move(facing.forward, offFront), 2);
			new BlockStateInput(stair.getBlockState(ComplexFacing.forFacing(facing.right, facing.up), 
					StairShape.STRAIGHT, rootState), props, tag).place(serverLevel, pos.set(middle).move(facing.right, offset).move(facing.forward, offFront), 2);
		}
		if (offTop != 0) {
			new BlockStateInput(stair.getBlockState(ComplexFacing.forFacing(facing.forward, facing.left), 
					StairShape.STRAIGHT, rootState), props, tag).place(serverLevel, pos.set(middle).move(facing.left, offset).move(facing.up, offTop), 2);
			new BlockStateInput(stair.getBlockState(ComplexFacing.forFacing(facing.forward, facing.right), 
					StairShape.STRAIGHT, rootState), props, tag).place(serverLevel, pos.set(middle).move(facing.right, offset).move(facing.up, offTop), 2);
		}
	}
	
	private static void setTwist(ServerWorld serverLevel, IStairBlock<?> stair, BlockState rootState, Set<Property<?>> props, CompoundNBT tag, ComplexFacing facing, BlockPos middle, BlockPos.Mutable pos, int offset) {
		new BlockStateInput(rootState, props, tag).place(serverLevel, pos.set(middle).move(facing.left, offset), 2);
		new BlockStateInput(rootState, props, tag).place(serverLevel, pos.set(middle).move(facing.right, offset), 2);
		new BlockStateInput(stair.getBlockState(ComplexFacing.forFacing(facing.left, facing.up), 
				StairShape.STRAIGHT, rootState), props, tag).place(serverLevel, pos.set(middle).move(facing.left, offset).move(facing.backward, 1), 2);
		new BlockStateInput(stair.getBlockState(ComplexFacing.forFacing(facing.right, facing.up), 
				StairShape.STRAIGHT, rootState), props, tag).place(serverLevel, pos.set(middle).move(facing.right, offset).move(facing.backward, 1), 2);
		new BlockStateInput(stair.getBlockState(ComplexFacing.forFacing(facing.forward, facing.right), 
				StairShape.STRAIGHT, rootState), props, tag).place(serverLevel, pos.set(middle).move(facing.left, offset).move(facing.down, 1), 2);
		new BlockStateInput(stair.getBlockState(ComplexFacing.forFacing(facing.forward, facing.left), 
				StairShape.STRAIGHT, rootState), props, tag).place(serverLevel, pos.set(middle).move(facing.right, offset).move(facing.down, 1), 2);
	}
}