package com.caretakermc.dementiamod;

import net.minecraftforge.event.world.WorldEvent;

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
}