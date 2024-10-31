package com.firemerald.additionalplacements.generation;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;

import com.firemerald.additionalplacements.block.AdditionalPlacementBlock;
import com.firemerald.additionalplacements.block.interfaces.ISimpleRotationBlock;
import com.firemerald.additionalplacements.block.interfaces.IStairBlock;
import com.firemerald.additionalplacements.config.GenerationBlacklist;
import com.firemerald.additionalplacements.util.MessageTree;
import com.firemerald.additionalplacements.util.stairs.StairConnections;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.StairBlock;
import net.minecraftforge.common.ForgeConfigSpec;

public class VerticalStairsGenerationType<T extends StairBlock, U extends AdditionalPlacementBlock<T> & ISimpleRotationBlock> extends SimpleRotatableGenerationType<T, U> {
	protected abstract static class BuilderBase<T extends StairBlock, U extends AdditionalPlacementBlock<T> & ISimpleRotationBlock, V extends SimpleRotatableGenerationType<T, U>, W extends BuilderBase<T, U, V, W>> extends SimpleRotatableGenerationType.BuilderBase<T, U, V, W> {
		protected Constructor<? super T, ? extends U> constructor;
		protected GenerationBlacklist 
		vertcialConnectionsBlacklist = new GenerationBlacklist.Builder().build(),
		mixedConnectionsBlacklist = new GenerationBlacklist.Builder().build();

		@Override
		public W constructor(Function<? super T, ? extends U> constructor) {
			throw new IllegalStateException("Function<? super T, ? extends U> constructor not supported");
		}
		
		public W constructor(Constructor<? super T, ? extends U> constructor) {
			this.constructor = constructor;
			return me();
		}
		
		public W blacklistVerticalConnections(GenerationBlacklist blacklist) {
			this.vertcialConnectionsBlacklist = blacklist;
			return me();
		}
		
		public W blacklistMixedConnections(GenerationBlacklist blacklist) {
			this.mixedConnectionsBlacklist = blacklist;
			return me();
		}
	}
	
	public static class Builder<T extends StairBlock, U extends AdditionalPlacementBlock<T> & ISimpleRotationBlock> extends BuilderBase<T, U, VerticalStairsGenerationType<T, U>, Builder<T, U>> {
		@Override
		public VerticalStairsGenerationType<T, U> construct(ResourceLocation name, String description) {
			return new VerticalStairsGenerationType<>(name, description, this);
		}
	}
	
	private final Constructor<? super T, ? extends U> constructor;
	private final GenerationBlacklist vertcialConnectionsBlacklist;
	private final GenerationBlacklist mixedConnectionsBlacklist;

	protected VerticalStairsGenerationType(ResourceLocation name, String description, BuilderBase<T, U, ?, ?> builder) {
		super(name, description, builder);
		this.constructor = builder.constructor;
		this.vertcialConnectionsBlacklist = builder.vertcialConnectionsBlacklist;
		this.mixedConnectionsBlacklist = builder.mixedConnectionsBlacklist;
	}

	public void buildStartupConfig(ForgeConfigSpec.Builder builder) {
		super.buildStartupConfig(builder);
		builder
		.comment("Options to control which blocks will allow for vertical stair connections.\nKeep in mind vertical is RELATIVE to the placement of the stair - \"vertical\" for a vertically placed stair will be one of the two horizontal directions.")
		.push("allow_vertical_connections");
		vertcialConnectionsBlacklist.addToConfig(builder);
		builder.pop();
		builder
		.comment("Options to control which blocks will allow for mixed stair connections.\nThese are any valid combination of horizontal and vertical connection - as such, a stair that cannot connect vertically cannot connect complexly.\nThis also controls connections between stairs who's facings don't necessarily match up - I.E. a stair facing UP_EAST and one facing EAST_UP.\nKeep in mind horizontal and vertical are RELATIVE to the placement of the stair - \"vertical\" and \"horizontal\" for a vertically placed stair are both horizontal directions.")
		.push("allow_mixed_connections");
		mixedConnectionsBlacklist.addToConfig(builder);
		builder.pop();
	}
	
	@Override
	public CompoundTag getClientCheckData() {
		CompoundTag tag = new CompoundTag();
		CompoundTag connections = new CompoundTag();
		this.forEachCreated((id, block) -> {
			StairConnections allowedConnections = ((IStairBlock<?>) block).allowedConnections();
			if (allowedConnections != StairConnections.ALL) {
				String typeName = allowedConnections.shortName;
				CompoundTag typeTag;
				ListTag modList;
				if (connections.contains(typeName, Tag.TAG_COMPOUND)) {
					typeTag = connections.getCompound(typeName);
					if (typeTag.contains(id.getNamespace(), Tag.TAG_LIST)) modList = typeTag.getList(id.getNamespace(), Tag.TAG_STRING);
					else typeTag.put(id.getNamespace(), modList = new ListTag());
				}
				else {
					connections.put(typeName, typeTag = new CompoundTag());
					typeTag.put(id.getNamespace(), modList = new ListTag());
				}
				modList.add(StringTag.valueOf(id.getPath()));
			}
		});
		tag.put("connections", connections);
		return tag;
	}

	@Override
	public void checkClientData(CompoundTag tag, Consumer<MessageTree> onError) {
		if (tag != null) {
			CompoundTag connections = tag.getCompound("connections");
			if (connections != null) {
				Map<ResourceLocation, StairConnections> connectionTypes = new HashMap<>(); 
				connections.getAllKeys().forEach(typeName -> {
					StairConnections type = StairConnections.getType(typeName);
					if (type != null) { 
						CompoundTag namespaces = connections.getCompound(typeName);
						namespaces.getAllKeys().forEach(namespace -> {
							ListTag blocks = namespaces.getList(namespace, Tag.TAG_STRING);
							if (blocks != null) for (int i = 0; i < blocks.size(); ++i) {
								connectionTypes.put(new ResourceLocation(namespace, blocks.getString(i)), type);
							}
						});
					} else {
						onError.accept(new MessageTree(new TranslatableComponent("msg.additionalplacements.stairs.connections_type_not_found", typeName)));
					}
				});
				Map<StairConnections, List<ResourceLocation>> mismatched = new HashMap<>();
				this.forEachCreated((id, block) -> {
					StairConnections targetType = connectionTypes.getOrDefault(id, StairConnections.ALL);
					StairConnections actualType = ((IStairBlock<?>) block).allowedConnections();
					if (targetType != actualType) mismatched.computeIfAbsent(targetType, u -> new ArrayList<>()).add(id);
				});
				if (!mismatched.isEmpty()) {
					MessageTree errorListRoot = new MessageTree(new TranslatableComponent("msg.additionalplacements.stairs.mismatched.header"));
					mismatched.forEach((type, blocks) -> {
						MessageTree errorListChild = new MessageTree(new TranslatableComponent("additionalplacements.stairs.connections_type." + type.name).append(":"));
						blocks.forEach(block -> errorListChild.children.add(new MessageTree(new TextComponent(block.toString()))));
						errorListRoot.children.add(errorListChild);
					});
					onError.accept(errorListRoot);
					onError.accept(new MessageTree(new TranslatableComponent("msg.additionalplacements.stairs.mismatched.footer")));
				}
			} else {
				onError.accept(new MessageTree(new TranslatableComponent("msg.additionalplacements.stairs.data_not_found")));
			}
		}
	}
	
	public void onBootupConfigLoaded() {
		super.onBootupConfigLoaded();
		vertcialConnectionsBlacklist.loadListsFromConfig();
		mixedConnectionsBlacklist.loadListsFromConfig();
	}

	@Override
	public U construct(T block, ResourceLocation blockId) {
		return constructor.apply(block, 
				!vertcialConnectionsBlacklist.test(blockId) ? StairConnections.NO_VERTICAL : 
					!mixedConnectionsBlacklist.test(blockId) ? StairConnections.NO_MIXED : 
						StairConnections.ALL);
	}
	
	@FunctionalInterface
	public static interface Constructor<T extends StairBlock, U extends AdditionalPlacementBlock<T> & ISimpleRotationBlock> {
		public U apply(T block, StairConnections allowedConnections);
	}
}
