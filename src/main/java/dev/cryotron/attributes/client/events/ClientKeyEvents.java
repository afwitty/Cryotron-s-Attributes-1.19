package dev.cryotron.attributes.client.events;

import dev.cryotron.attributes.CTAttributes;
import dev.cryotron.attributes.client.KeySetup;
import dev.cryotron.attributes.client.SoundPlayer;
import dev.cryotron.attributes.client.gui.PlayerSheet;
import dev.cryotron.attributes.client.gui.PlayerSkillTree;
import dev.cryotron.attributes.setup.deferredregistries.RegisteredSounds;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundStopSoundPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class ClientKeyEvents {
	@Mod.EventBusSubscriber( modid = CTAttributes.ID, value = Dist.CLIENT )
	public static class ClientForgeEvents {
		
		@SubscribeEvent
		public static void onKeyInput( InputEvent.Key event) {
			
			Minecraft mc = Minecraft.getInstance();

			if (KeySetup.CHAR_SHEET.consumeClick() && mc.player != null) {
				
				Minecraft.getInstance().player.sendSystemMessage(Component.literal("Character Sheet key pressed!"));
		
				// I assume this closes Player Sheet
				if (mc.screen instanceof PlayerSheet) {
					mc.setScreen(null);
				}
				// This opens Player Sheet IF THERE IS ONE!
				else if (mc.screen == null) {
					mc.setScreen(new PlayerSheet(Minecraft.getInstance().player));
				}
			}
			
			if (KeySetup.SKILL_TREE.consumeClick() && mc.player != null) {
				
				Minecraft.getInstance().player.sendSystemMessage(Component.literal("Skill Tree key pressed!"));
				
				// I assume this closes Player Sheet
				if (mc.screen instanceof PlayerSkillTree) {
					mc.setScreen(null);
				}
				// This opens Player Sheet IF THERE IS ONE!
				else if (mc.screen == null) {
					mc.setScreen(new PlayerSkillTree(Minecraft.getInstance().player));
				}
			}

		}
		
	}
	
	@Mod.EventBusSubscriber( modid = CTAttributes.ID, value = Dist.CLIENT , bus = Mod.EventBusSubscriber.Bus.MOD)
	public static class ClientModBusEvents {
		
		@SubscribeEvent
		public static void onKeyRegister( RegisterKeyMappingsEvent event ) {
			event.register(KeySetup.CHAR_SHEET);
			event.register(KeySetup.SKILL_TREE);
			
		}
		
	}

}
