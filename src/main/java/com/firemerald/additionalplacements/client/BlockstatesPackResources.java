package com.firemerald.additionalplacements.client;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.function.Predicate;

import com.firemerald.additionalplacements.AdditionalPlacementsMod;
import com.firemerald.additionalplacements.block.*;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackResources;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.metadata.MetadataSectionSerializer;
import net.minecraft.world.level.block.Block;

@Environment(EnvType.CLIENT)
public class BlockstatesPackResources implements PackResources
{
	@Override
	public InputStream getRootResource(String p_10294_) throws IOException
	{
		return null;
	}

	@Override
	public InputStream getResource(PackType packType, ResourceLocation resource) throws IOException
	{
		if (packType != PackType.CLIENT_RESOURCES) throw new FileNotFoundException("Cannot provide " + resource + ": invalid pack type " + packType);
		else if (!resource.getNamespace().equals(AdditionalPlacementsMod.MOD_ID)) throw new FileNotFoundException("Cannot provide " + resource + ": invalid namespace " + resource.getNamespace());
		else if (!resource.getPath().endsWith(".json")) throw new FileNotFoundException("Cannot provide " + resource + ": invalid file " + resource.getPath());
		else if (resource.getPath().startsWith("blockstates/")) //blockstate json
		{
			String blockName = resource.getPath().substring(12, resource.getPath().length() - 5);
			Block block = Registry.BLOCK.get(new ResourceLocation(AdditionalPlacementsMod.MOD_ID, blockName));
			ResourceLocation blockStateJson = getBlockstateJson(block);
			if (blockStateJson != null) return Minecraft.getInstance().getResourceManager().getResource(blockStateJson).get().open();
			else throw new FileNotFoundException("Cannot provide " + resource + ": invalid block additionalplacements:" + blockName);
		}
		else throw new FileNotFoundException("Cannot provide " + resource + ": invalid file " + resource.getPath());
	}

	@Override
	public Collection<ResourceLocation> getResources(PackType packType, String domain, String path, Predicate<ResourceLocation> filter)
	{
		if (packType == PackType.CLIENT_RESOURCES && AdditionalPlacementsMod.MOD_ID.equals(domain) && path.length() >= 11 && (path.length() == 11 ? path.equals("blockstates") : path.startsWith("blockstates/")))
		{
			List<ResourceLocation> found = new LinkedList<>();
			Registry.BLOCK.entrySet().forEach(entry -> {
				ResourceLocation id = entry.getKey().location();
				if (id.getNamespace().equals(AdditionalPlacementsMod.MOD_ID))
				{
					ResourceLocation blockStateJson = getBlockstateJson(entry.getValue());
					if (blockStateJson != null)
					{
						String startingFolder = path + "/";
						String file = "blockstates/" + id.getPath() + ".json";
						if (file.startsWith(startingFolder))
						{
							ResourceLocation loc = new ResourceLocation(AdditionalPlacementsMod.MOD_ID, file);
							if (filter.test(loc)) found.add(loc);
						}
					}
				}
			});
			return found;
		}
		else return Collections.emptyList();
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
			return getBlockstateJson(block) != null;
		}
		else return false;
	}

	public static ResourceLocation getBlockstateJson(Block block)
	{
		if (block instanceof AdditionalPlacementBlock) return ((AdditionalPlacementBlock<?>) block).getDynamicBlockstateJson();
		else return null;
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
