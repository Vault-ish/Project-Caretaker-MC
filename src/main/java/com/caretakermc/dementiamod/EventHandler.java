package com.caretakermc.dementiamod;

import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings.Options;
import net.minecraftforge.event.world.WorldEvent;

@EventBusSubscriber
public class EventHandler {	
    @SubscribeEvent
    public static void worldLoad(WorldEvent.Load event) {
        System.out.println("Hello world");
    }
}