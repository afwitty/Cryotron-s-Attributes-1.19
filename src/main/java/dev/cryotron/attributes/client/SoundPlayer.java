package dev.cryotron.attributes.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.Level;

import dev.cryotron.attributes.CTAttributes;

import java.util.HashMap;

public final class SoundPlayer {
	private static final HashMap<ResourceLocation, SoundInstance> playingSounds = new HashMap<ResourceLocation, SoundInstance>();

	public static void playSound(ResourceLocation id) {
		if (playingSounds.containsKey(id)) {
			if (!Minecraft.getInstance().getSoundManager().isActive(playingSounds.get(id))) {
				playingSounds.remove(id);
			}
			else {
				return;
			}
		}

		SoundEvent soundEvent = ForgeRegistries.SOUND_EVENTS.getValue(id);

		if (soundEvent == null) {
			CTAttributes.LOGGER.info("Unable to find sound event with ID: " + id.toString());

			return;
		}

		SoundInstance sound = SimpleSoundInstance.forMusic(soundEvent);
		Minecraft.getInstance().getSoundManager().play(sound);
		playingSounds.put(id, sound);
	}

	public static void stopSound(ResourceLocation id) {
		SoundInstance sound = playingSounds.get(id);

		if (sound != null) {
			SoundManager soundHandler = Minecraft.getInstance().getSoundManager();

			if (soundHandler.isActive(sound))
				soundHandler.stop(sound);

			playingSounds.remove(id);
		}
	}
}
