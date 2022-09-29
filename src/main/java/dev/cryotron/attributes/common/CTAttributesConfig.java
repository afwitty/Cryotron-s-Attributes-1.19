package dev.cryotron.attributes.common;

import java.io.File;

import org.apache.commons.lang3.tuple.Pair;

import dev.cryotron.attributes.CTAttributes;
import dev.cryotron.attributes.config.ClientConfig;
import dev.cryotron.attributes.config.CommonConfig;
import dev.cryotron.attributes.config.IntegrationsConfig;
import dev.cryotron.attributes.config.ServerConfig;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.fml.loading.FileUtils;

public class CTAttributesConfig {
	
	public static final ClientConfig CLIENT;
	public static final ForgeConfigSpec CLIENT_CONFIG_SPEC;

	public static final CommonConfig COMMON;
	public static final ForgeConfigSpec COMMON_CONFIG_SPEC;

	public static final ServerConfig SERVER;
	public static final ForgeConfigSpec SERVER_CONFIG_SPEC;

	public static final IntegrationsConfig INTEGRATIONS;
	public static final ForgeConfigSpec INTEGRATIONS_CONFIG_SPEC;
	
	public static void init() {
		FileUtils.getOrCreateDirectory(FMLPaths.CONFIGDIR.get().resolve(CTAttributes.ID), CTAttributes.ID);
		ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, CTAttributesConfig.SERVER_CONFIG_SPEC, CTAttributes.ID + "_server_config.toml");
		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, CTAttributesConfig.COMMON_CONFIG_SPEC, CTAttributes.ID + File.separator + "common_config.toml");
		ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, CTAttributesConfig.CLIENT_CONFIG_SPEC, CTAttributes.ID + File.separator + "client_config.toml");
		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, CTAttributesConfig.INTEGRATIONS_CONFIG_SPEC, CTAttributes.ID + File.separator + "integrations_config.toml");
	}
	
	static {

		final Pair<ClientConfig, 				ForgeConfigSpec> clientSpecPair 				= new ForgeConfigSpec.Builder().configure(ClientConfig::new);
		final Pair<CommonConfig, 		ForgeConfigSpec> commonSpecPair 		= new ForgeConfigSpec.Builder().configure(CommonConfig::new);
		final Pair<ServerConfig, 			ForgeConfigSpec> serverSpecPair 			= new ForgeConfigSpec.Builder().configure(ServerConfig::new);
		final Pair<IntegrationsConfig, 	ForgeConfigSpec> integrationSpecPair 	= new ForgeConfigSpec.Builder().configure(IntegrationsConfig::new);

		CLIENT_CONFIG_SPEC = clientSpecPair.getRight();
		CLIENT = clientSpecPair.getLeft();
		COMMON_CONFIG_SPEC = commonSpecPair.getRight();
		COMMON = commonSpecPair.getLeft();
		SERVER_CONFIG_SPEC = serverSpecPair.getRight();
		SERVER = serverSpecPair.getLeft();
		INTEGRATIONS_CONFIG_SPEC = integrationSpecPair.getRight();
		INTEGRATIONS = integrationSpecPair.getLeft();
	}
}
