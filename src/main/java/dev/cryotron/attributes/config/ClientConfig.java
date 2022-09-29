package dev.cryotron.attributes.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class ClientConfig {

	public final ForgeConfigSpec.BooleanValue disableHudPotionOffset;
	
	public ClientConfig(ForgeConfigSpec.Builder specBuilder) {
		specBuilder.comment("CTUtilities client-side configuration options").push("General Settings");
		
		disableHudPotionOffset = specBuilder
				.comment("Set this to true to stop the skills and resources HUD elements shifting down when players have potion effects.")
				.translation("config.ctdefenses.client.disableHudPotionOffset")
				.define("disableHudPotionOffset", false);

	}
}
