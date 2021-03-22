package com.caretakermc.dementiamod;

import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.world.World;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.world.WorldEvent;

@EventBusSubscriber
public class EventHandler {
	public static long totalWorldTime = 0;
	public static int currentStage = 0;
	public static Block GRASS_STAGE_1 = new GrassStage1("grass_stage_1");
	public static Item GRASS_STAGE_1_ITEM = new ItemBlock(GRASS_STAGE_1).setRegistryName(DementiaMod.MODID, "grass_stage_1");

	@SubscribeEvent
	public static void worldLoad(WorldEvent.Load event) {
		System.out.println("Hello world");
	}

	@SubscribeEvent
	public static void clientTick(TickEvent.ClientTickEvent event) {
		World world = Minecraft.getMinecraft().world;
		if (world == null) {
			return;
		}
		if (!world.isRemote) {
			return;
		}
		long tickCheckWorldTime = Minecraft.getMinecraft().world.getTotalWorldTime();
		if (totalWorldTime == tickCheckWorldTime) {
			return;
		}
		totalWorldTime = tickCheckWorldTime;
		int currentStageCheck = Utils.getStageByTimestamp(totalWorldTime);
		if (currentStage != currentStageCheck) {
			currentStage = currentStageCheck;
			// ToDo delete after 2-define-stages tested
			Minecraft.getMinecraft().ingameGUI.setOverlayMessage("STAGE " + currentStage + " STARTED", false);
			Utils.updateBlocks(1); // WIP currentStage
		}
	}

	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		event.getRegistry().registerAll(GRASS_STAGE_1);
	}

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		event.getRegistry().registerAll(GRASS_STAGE_1_ITEM);
	}
}
