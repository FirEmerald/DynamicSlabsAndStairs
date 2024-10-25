package com.firemerald.additionalplacements.block;

import com.firemerald.additionalplacements.AdditionalPlacementsMod;
import com.firemerald.additionalplacements.block.interfaces.ISimpleRotationBlock;
import com.firemerald.additionalplacements.block.interfaces.ISlabBlock;
import com.firemerald.additionalplacements.util.BlockRotation;
import com.firemerald.additionalplacements.util.VoxelShapes;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SlabBlock;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.SlabType;
import net.minecraft.util.Direction;
import net.minecraft.util.Direction.Axis;
import net.minecraft.util.Direction.AxisDirection;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;

public class VerticalSlabBlock extends AdditionalPlacementLiquidBlock<SlabBlock> implements ISlabBlock<SlabBlock>, ISimpleRotationBlock
{
	static final ResourceLocation SLAB_BLOCKSTATES = new ResourceLocation(AdditionalPlacementsMod.MOD_ID, "blockstate_templates/slab.json");
	public static final EnumProperty<Axis> AXIS = BlockStateProperties.HORIZONTAL_AXIS;

	public static VerticalSlabBlock of(SlabBlock slab)
	{
		return new VerticalSlabBlock(slab);
	}
	
	public boolean rotateLogic = true, rotateModel = true, rotateTex = true;

	@SuppressWarnings("deprecation")
	private VerticalSlabBlock(SlabBlock slab)
	{
		super(slab);
		this.registerDefaultState(copyProperties(getModelState(), this.stateDefinition.any()).setValue(AXIS, Axis.Z));
		((IVanillaSlabBlock) slab).setOtherBlock(this);
	}

	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder)
	{
		builder.add(AXIS);
		super.createBlockStateDefinition(builder);
	}

	@Override
	@SuppressWarnings("deprecation")
	public VoxelShape getShapeInternal(BlockState state, IBlockReader level, BlockPos pos, ISelectionContext context)
	{
		switch (state.getValue(SlabBlock.TYPE)) {
		case DOUBLE: return VoxelShapes.BLOCK;
		case TOP: return state.getValue(AXIS) == Axis.Z ? VoxelShapes.SLAB_SOUTH : VoxelShapes.SLAB_EAST;
		case BOTTOM: return state.getValue(AXIS) == Axis.Z ? VoxelShapes.SLAB_NORTH : VoxelShapes.SLAB_WEST;
		default: return super.getShape(state, level, pos, context);
		}
	}

	@Override
	public boolean canBeReplaced(BlockState state, BlockItemUseContext context)
	{
		return enablePlacement(context.getClickedPos(), context.getLevel(), context.getClickedFace(), context.getPlayer()) && canBeReplacedImpl(state, context);
	}

	@Override
	public Direction getPlacing(BlockState blockState)
	{
		SlabType type = blockState.getValue(SlabBlock.TYPE);
		return type == SlabType.DOUBLE ? null : Direction.fromAxisAndDirection(blockState.getValue(VerticalSlabBlock.AXIS), type == SlabType.TOP ? AxisDirection.POSITIVE : AxisDirection.NEGATIVE);
	}

	@Override
	public BlockState getDefaultVanillaState(BlockState currentState)
	{
		return currentState.is(parentBlock) ? currentState : copyProperties(currentState, parentBlock.defaultBlockState());
	}

	@Override
	public BlockState getDefaultAdditionalState(BlockState currentState)
	{
		return currentState.is(this) ? currentState : copyProperties(currentState, this.defaultBlockState());
	}

	@Override
	public String getTagTypeName()
	{
		return "slab";
	}

	@Override
	public String getTagTypeNamePlural()
	{
		return "slabs";
	}

	@Override
	public BlockState updateShapeImpl(BlockState state, Direction direction, BlockState otherState, IWorld level, BlockPos pos, BlockPos otherPos)
	{
		return state;
	}

	@Override
	public boolean rotatesLogic(BlockState state) {
		return rotateLogic;
	}

	@Override
	public boolean rotatesTexture(BlockState state) {
		return rotateTex;
	}

	@Override
	public boolean rotatesModel(BlockState state) {
		return rotateModel;
	}

	@Override
	public BlockRotation getRotation(BlockState state) {
		return state.getValue(AXIS) == Axis.X ? 
				BlockRotation.X_270_Y_270 : 
					BlockRotation.X_270;
	}

	@Override
	public void setLogicRotation(boolean useLogicRotation) {
		this.rotateLogic = useLogicRotation;
	}

	@Override
	public void setModelRotation(boolean useTexRotation, boolean useModelRotation) {
		this.rotateTex = useTexRotation;
		this.rotateModel = useModelRotation;
	}

	@Override
	public BlockState withUnrotatedPlacement(BlockState worldState, BlockState modelState) {
		return modelState; //no changes needed
	}

	@Override
	public ResourceLocation getDynamicBlockstateJson() {
		return SLAB_BLOCKSTATES;
	}
}
