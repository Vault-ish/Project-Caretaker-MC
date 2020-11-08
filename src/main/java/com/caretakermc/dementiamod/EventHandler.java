package com.caretakermc.dementiamod;

import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraft.client.Minecraft;
import net.minecraftforge.event.world.WorldEvent;

@EventBusSubscriber
public class EventHandler {
	public static long totalWorldTime = 0;
	public static int currentStage = 0;
	
	@SubscribeEvent
	public static void worldLoad(WorldEvent.Load event) {
		System.out.println("Hello world");
	}
    
    @SubscribeEvent
    public static void clientTick(TickEvent.ClientTickEvent event) {
		if (Minecraft.getMinecraft().world == null) {
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
		}
    }
}