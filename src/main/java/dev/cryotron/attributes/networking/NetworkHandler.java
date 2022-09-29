package dev.cryotron.attributes.networking;

import dev.cryotron.attributes.CTAttributes;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class NetworkHandler {
	
	private static final String PROTOCOL_VERSION = Integer.toString(1);
	public final static SimpleChannel CHANNEL = NetworkRegistry.ChannelBuilder
		.named(new ResourceLocation(CTAttributes.ID, "sync2"))
		.clientAcceptedVersions(s -> true)
		.serverAcceptedVersions(s -> true)
		.networkProtocolVersion(() -> PROTOCOL_VERSION)
		.simpleChannel();
	
	public static void init()
	{
		CHANNEL.registerMessage(1, InvulnerabilitySync.class, InvulnerabilitySync::encode, InvulnerabilitySync::decode, InvulnerabilitySync::handle);
		
		MinecraftForge.EVENT_BUS.register(new NetworkHandler());
	}
}
