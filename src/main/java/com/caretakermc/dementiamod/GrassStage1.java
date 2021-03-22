package com.caretakermc.dementiamod;

import net.minecraft.block.BlockGrass;
import net.minecraft.block.SoundType;

public class GrassStage1 extends BlockGrass {	
	public GrassStage1(String name) {
		this.setHardness(0.6F);
		this.setSoundType(SoundType.PLANT);
		this.setRegistryName(DementiaMod.MODID, name);
	}
}
