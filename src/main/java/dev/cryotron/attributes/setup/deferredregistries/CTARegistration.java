package dev.cryotron.attributes.setup.deferredregistries;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class CTARegistration {

    public static void init() {
    	final IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
    	RegisteredSounds.SFX.register(bus);
    	RegisteredAttributes.ATTRIBUTES.register(bus);
    	RegisteredItems.ITEMS.register(bus);
    }
}
