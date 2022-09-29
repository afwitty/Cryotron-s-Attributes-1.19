package dev.cryotron.attributes.setup.deferredregistries;

import dev.cryotron.attributes.CTAttributes;
import dev.cryotron.attributes.setup.CTASetup;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class RegisteredSounds {
    public static final DeferredRegister<SoundEvent> SFX = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, CTAttributes.ID);
    
	public static final RegistryObject<SoundEvent> INVULNERABILITY_USE = registerSound("invulnerability_use", "item.invulnerability_use");
	public static final RegistryObject<SoundEvent> SKILL_TREE = registerSound("skilltree", "gui.skilltree");
	
	private static RegistryObject<SoundEvent> registerSound(String registryName, String soundPath) {
		return SFX.register(registryName, () -> createSoundEvent(soundPath));
	}

	// Using AOA3 Sound methods for sound effects.
	private static SoundEvent createSoundEvent(String soundPath) {
//		if (HolidayUtil.isChristmas() && soundPath.endsWith(".fire") && !DatagenModLoader.isRunningDataGen())
//			soundPath = "misc.jingle_bells";
		return new SoundEvent(new ResourceLocation("cryoattributes", soundPath));
	}
	
}
