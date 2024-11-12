package com.firemerald.additionalplacements.client.resources;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.function.BiConsumer;

import com.firemerald.additionalplacements.AdditionalPlacementsMod;
import com.firemerald.additionalplacements.block.*;
import com.firemerald.additionalplacements.generation.CreatedBlockEntry;
import com.firemerald.additionalplacements.generation.GenerationType;
import com.firemerald.additionalplacements.generation.Registration;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackResources;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.metadata.MetadataSectionSerializer;
import net.minecraft.server.packs.resources.IoSupplier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.ForgeRegistries;

@OnlyIn(Dist.CLIENT)
public class APDynamicResources implements PackResources
{
	@Override
	public IoSupplier<InputStream> getRootResource(String... p_10294_)
	{
		return null;
	}

	@Override
	public IoSupplier<InputStream> getResource(PackType packType, ResourceLocation resource)
	{
		if (packType != PackType.CLIENT_RESOURCES) return null;
		else if (!resource.getNamespace().equals(AdditionalPlacementsMod.MOD_ID)) return null;
		else if (!resource.getPath().endsWith(".json")) return null;
		else if (resource.getPath().startsWith("blockstates/")) //blockstate json
		{
			String blockName = resource.getPath().substring(12, resource.getPath().length() - 5);
			Block block = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(AdditionalPlacementsMod.MOD_ID, blockName));
			if (block instanceof AdditionalPlacementBlock<?> placement) return new BlockStateJsonSupplier(placement, blockName);
			else return null;
		}
		else if (resource.getPath().startsWith("models/block/") && resource.getPath().endsWith("/model.json")) {
			String key = resource.getPath().substring(13, resource.getPath().length() - 11);
			Optional<?> match = Registration.types().flatMap(GenerationType::created).filter(entry -> key.startsWith(entry.newId().getPath())).findFirst();
			if (match.isPresent()) {
				CreatedBlockEntry<?, ?> entry = (CreatedBlockEntry<?, ?>) match.get();
				String[] stateVals = key.substring(entry.newId().getPath().length() + 1).split("/");
				BlockState state = entry.newBlock().defaultBlockState();
				Collection<Property<?>> props = state.getProperties();
				if (stateVals.length != props.size()) return null;
				else {
					Iterator<Property<?>> it = props.iterator();
					for (String stateVal : stateVals) {
						Property<?> prop = it.next();
						@SuppressWarnings("unchecked")
						Optional<Comparable<?>> opt = (Optional<Comparable<?>>) prop.getValue(stateVal);
						if (!opt.isPresent()) return null;
						else state = set(state, prop, opt.get());
					}
					return new BlockModelJsonSupplier(entry.newBlock(), entry.newId(), state);
				}
			}
			else return null;
		}
		else return null;
	}
	
	@SuppressWarnings("unchecked")
	private <T extends Comparable<T>> BlockState set(BlockState state, Property<?> prop, Comparable<?> val) {
		return state.setValue((Property<T>) prop, (T) val);
	}
	
	@Override
	public void listResources(PackType packType, String domain, String path, ResourceOutput filter)
	{
		if (packType == PackType.CLIENT_RESOURCES && AdditionalPlacementsMod.MOD_ID.equals(domain)) {
			if ("blockstates".equals(path))
			{
				Registration.forEach(type -> {
					type.forEachCreated(entry -> {
						ResourceLocation id = entry.newId();
						filter.accept(
								new ResourceLocation(AdditionalPlacementsMod.MOD_ID, "blockstates/" + id.getPath() + ".json"), 
								new BlockStateJsonSupplier(entry.newBlock(), id.getPath()));
					});
				});
			}
			else if ("models".equals(path)) {
				Registration.types().flatMap(GenerationType::created).forEach(entry -> {
					AdditionalPlacementBlock<?> block = entry.newBlock();
					BlockState state = block.defaultBlockState();
					parseBlockstates(state, new ArrayList<>(state.getProperties()), 0, "models/block/" + entry.newId().getPath() + "/", (modelPath, newState) -> {
						filter.accept(
								new ResourceLocation(AdditionalPlacementsMod.MOD_ID, modelPath + ".json"), 
								new BlockModelJsonSupplier(block, entry.newId(), newState));
					});
				});
			}
		}
	}
	
	private <T extends Comparable<T>> void parseBlockstates(BlockState state, List<Property<?>> props, int index, String currentStateDir, BiConsumer<String, BlockState> action) {
		if (index >= props.size())
			action.accept(currentStateDir + "model", state);
		else {
			@SuppressWarnings("unchecked")
			Property<T> prop = (Property<T>) props.get(index);
			prop.getAllValues().forEach(val -> parseBlockstates(set(state, prop, val.value()), props, index + 1, currentStateDir + prop.getName(val.value()) + "/", action));
		}
	}

	@Override
	public Set<String> getNamespaces(PackType p_10283_)
	{
		return Collections.singleton(AdditionalPlacementsMod.MOD_ID);
	}

	@Override
	public <T> T getMetadataSection(MetadataSectionSerializer<T> p_10291_) throws IOException
	{
		return null;
	}

	@Override
	public String packId()
	{
		return "Additional Placements Dynamic Resources";
	}

	@Override
	public void close() {}

	@Override
	public boolean isBuiltin()
	{
		return true;
	}

	@Override
	public boolean isHidden()
	{
		return true;
	}
}
