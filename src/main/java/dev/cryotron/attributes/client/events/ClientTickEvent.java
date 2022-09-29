package dev.cryotron.attributes.client.events;


import dev.cryotron.attributes.CTAttributes;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ClientTickEvent {
	
	public static void init() {
	    MinecraftForge.EVENT_BUS.addListener(ClientTickEvent::playerTick);
	    MinecraftForge.EVENT_BUS.addListener(TotemOfInvulnerabilityClientEvent::totemEvent);
	}
	
	
	@SubscribeEvent
    public static void playerTick(PlayerTickEvent event) {
        if (!event.player.level.isClientSide) {
          return;
        }

        //CTDefenses.LOGGER.info("Player Client: " + event.player + " is ticking.");
        
      }
}
