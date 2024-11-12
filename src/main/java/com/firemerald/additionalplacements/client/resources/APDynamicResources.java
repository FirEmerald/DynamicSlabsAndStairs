package com.firemerald.additionalplacements.client.resources;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.firemerald.additionalplacements.AdditionalPlacementsMod;
import com.firemerald.additionalplacements.block.*;
import com.firemerald.additionalplacements.generation.CreatedBlockEntry;
import com.firemerald.additionalplacements.generation.GenerationType;
import com.firemerald.additionalplacements.generation.Registration;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackResources;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.metadata.MetadataSectionSerializer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;

@Environment(EnvType.CLIENT)
public class APDynamicResources implements PackResources
{
	@Override
	public InputStream getRootResource(String p_10294_) throws IOException
	{
		return null;
	}

	@Override
	public InputStream getResource(PackType packType, ResourceLocation resource) throws IOException
	{
		if (packType != PackType.CLIENT_RESOURCES) return invalidResource(resource, "invalid pack type " + packType);
		else if (!resource.getNamespace().equals(AdditionalPlacementsMod.MOD_ID)) return invalidResource(resource, "invalid namespace " + resource.getNamespace());
		else if (!resource.getPath().endsWith(".json")) return invalidFile(resource);
		else if (resource.getPath().startsWith("blockstates/")) //blockstate json
		{
			String blockName = resource.getPath().substring(12, resource.getPath().length() - 5);
			Block block = Registry.BLOCK.get(new ResourceLocation(AdditionalPlacementsMod.MOD_ID, blockName));
			if (block instanceof AdditionalPlacementBlock) return BlockStateJsonSupplier.of((AdditionalPlacementBlock<?>) block, blockName);
			else return invalidResource(resource, "invalid block additionalplacements:" + blockName);
		}
		else if (resource.getPath().startsWith("models/block/") && resource.getPath().endsWith("/model.json")) {
			String key = resource.getPath().substring(13, resource.getPath().length() - 11);
			Optional<InputStream> match = Registration.types().flatMap(GenerationType::created)
					.map(entry -> {
						if (key.startsWith(entry.newId().getPath())) {
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
								return BlockModelJsonSupplier.of(entry.newBlock(), entry.newId(), state);
							}
						}
						else return null;
					})
					.filter(Objects::nonNull)
					.findFirst();
			if (match.isPresent()) return match.get();
			else return invalidResource(resource, "Could not find a matching blockState for psuedo-blockState " + key);
		}
		else return invalidFile(resource);
	}
	
	private InputStream invalidFile(ResourceLocation resource) throws IOException {
		return invalidResource(resource, "invalid file " + resource.getPath());
	}
	
	private InputStream invalidResource(ResourceLocation resource, String reason) throws IOException {
		throw new FileNotFoundException("Cannot provide " + resource + ": " + reason);
	}

	@SuppressWarnings("unchecked")
	private <T extends Comparable<T>> BlockState set(BlockState state, Property<?> prop, Comparable<?> val) {
		return state.setValue((Property<T>) prop, (T) val);
	}

	@Override
	public Collection<ResourceLocation> getResources(PackType packType, String domain, String path, Predicate<ResourceLocation> filter)
	{
		String startingFolder = path + "/";
		return getResources(packType, domain, path)
				.filter(file -> file.startsWith(startingFolder))
				.map(file -> new ResourceLocation(AdditionalPlacementsMod.MOD_ID, file))
				.filter(filter)
				.collect(Collectors.toList());
	}
	
	public Stream<String> getResources(PackType packType, String domain, String path)
	{
		if (packType == PackType.CLIENT_RESOURCES && AdditionalPlacementsMod.MOD_ID.equals(domain)) {
			if (path.length() >= 11 && (path.length() == 11 ? path.equals("blockstates") : path.startsWith("blockstates/")))
				return Registration.types().flatMap(GenerationType::created)
						.map(entry -> "blockstates/" + entry.newId().getPath() + ".json");
			else if (path.length() >= 6 && (path.length() == 6 ? path.equals("models") : path.startsWith("models/")))
				return Registration.types().flatMap(GenerationType::created)
						.flatMap(entry -> parseBlockstates(new ArrayList<>(entry.newBlock().getStateDefinition().getProperties()), 0, "models/block/" + entry.newId().getPath() + "/"));
			else return Stream.empty();
		}
		else return Stream.empty();
	}
	
	private <T extends Comparable<T>> Stream<String> parseBlockstates(List<Property<?>> props, int index, String currentStateDir) {
		if (index >= props.size())
			return Stream.of(currentStateDir + "model.json");
		else {
			@SuppressWarnings("unchecked")
			Property<T> prop = (Property<T>) props.get(index);
			return prop.getAllValues().flatMap(val -> parseBlockstates(props, index + 1, currentStateDir + prop.getName(val.value()) + "/"));
		}
	}

	@Override
	public boolean hasResource(PackType packType, ResourceLocation resource)
	{
		if (packType != PackType.CLIENT_RESOURCES) return false;
		else if (!resource.getNamespace().equals(AdditionalPlacementsMod.MOD_ID)) return false;
		else if (!resource.getPath().endsWith(".json")) return false;
		else if (resource.getPath().startsWith("blockstates/")) //blockstate json
		{
			String blockName = resource.getPath().substring(12, resource.getPath().length() - 5);
			Block block = Registry.BLOCK.get(new ResourceLocation(AdditionalPlacementsMod.MOD_ID, blockName));
			if (block instanceof AdditionalPlacementBlock) return true;
			else return false;
		}
		else if (resource.getPath().startsWith("models/block/") && resource.getPath().endsWith("/model.json")) {
			String key = resource.getPath().substring(13, resource.getPath().length() - 11);
			Optional<?> match = Registration.types().flatMap(GenerationType::created).filter(entry -> key.startsWith(entry.newId().getPath())).findFirst();
			if (match.isPresent()) {
				CreatedBlockEntry<?, ?> entry = (CreatedBlockEntry<?, ?>) match.get();
				String[] stateVals = key.substring(entry.newId().getPath().length() + 1).split("/");
				Collection<Property<?>> props = entry.newBlock().getStateDefinition().getProperties();
				if (stateVals.length != props.size()) return false;
				else {
					Iterator<Property<?>> it = props.iterator();
					for (String stateVal : stateVals) {
						Property<?> prop = it.next();
						@SuppressWarnings("unchecked")
						Optional<Comparable<?>> opt = (Optional<Comparable<?>>) prop.getValue(stateVal);
						if (!opt.isPresent()) return false;
					}
					return true;
				}
			}
			else return false;
		}
		else return false;
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
	public String getName()
	{
		return "Additional Placements blockstate redirection pack";
	}

	@Override
	public void close() {}
}
