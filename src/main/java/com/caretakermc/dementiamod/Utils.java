package com.caretakermc.dementiamod;

import java.util.Arrays;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class Utils {
	private Utils() {
		// restrict instantiation
	}

	public static int getStageByTimestamp(long t) {
		if (t > Constants.TIMESTAMP_STAGE_1 && t <= Constants.TIMESTAMP_STAGE_2) {
			return 1;
		}
		if (t > Constants.TIMESTAMP_STAGE_2 && t <= Constants.TIMESTAMP_STAGE_3) {
			return 2;
		}
		if (t > Constants.TIMESTAMP_STAGE_3 && t <= Constants.TIMESTAMP_STAGE_4) {
			return 3;
		}
		if (t > Constants.TIMESTAMP_STAGE_4 && t <= Constants.TIMESTAMP_STAGE_5) {
			return 4;
		}
		if (t > Constants.TIMESTAMP_STAGE_5 && t <= Constants.TIMESTAMP_STAGE_6) {
			return 5;
		}
		if (t > Constants.TIMESTAMP_STAGE_6) {
			return 6;
		}
		return 0;
	}
	
	public static void updateBlocks(int stage) {
		if (stage == 0) {
			return;
		}
		EntityPlayer player = Minecraft.getMinecraft().world.playerEntities.get(0);
		Chunk theChunk = Minecraft.getMinecraft().world.getChunkFromBlockCoords(new BlockPos(player.posX, player.posY, player.posZ));
		Utils.updateChunkBlocks(theChunk, stage);
	}
	
	public static void updateChunkBlocks(Chunk theChunk, int stage) {
		int[] hm = theChunk.getHeightMap(); 
		Arrays.sort(hm);
		for (int x = 0; x < 16; x++) {
			for (int y = hm[0] - 1; y < hm[hm.length - 1]; y++) {
				for (int z = 0; z < 16; z++) {
					Utils.updateBlock(theChunk, new BlockPos(x, y, z), stage);
				}
			}
		}
		theChunk.markDirty();
	}
	
	public static void updateBlock(Chunk theChunk, BlockPos pos, int stage) {
		String blockName = theChunk.getBlockState(pos).getBlock().getRegistryName().toString().replace("minecraft:", "");
		System.out.println("("+pos.getX()+","+pos.getY()+","+pos.getZ()+"):"+blockName);
		if (!blockName.equals("grass")) {
			return;
		}
		String replacerName = "dementiamod:" + blockName + "_stage_" + stage;
		Block replacer = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(replacerName));
		if (replacer.getLocalizedName().equals("Air")) {
			return;
		}
		theChunk.setBlockState(pos, replacer.getDefaultState());
		Minecraft.getMinecraft().world.markBlockRangeForRenderUpdate(
			theChunk.x * 16 + pos.getX(),
			pos.getY(),
			theChunk.z * 16 + pos.getZ(),
			theChunk.x * 16 + pos.getX(),
			pos.getY(),
			theChunk.z * 16 + pos.getZ()
		);
	}
}
