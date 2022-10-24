package dev.cryotron.attributes;

import java.util.Locale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dev.cryotron.attributes.common.CTAttributesConfig;
import dev.cryotron.attributes.setup.CTASetup;
import dev.cryotron.attributes.setup.deferredregistries.CTARegistration;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.data.loading.DatagenModLoader;
import net.minecraftforge.fml.common.Mod;

@Mod(CTAttributes.ID)
public class CTAttributes {

	public static final String ID = "cryoattributes";
	
	public static final Logger LOGGER = LogManager.getLogger(ID);
	
	private static CTAttributes instance;
	private final CTASetup setup = new CTASetup();
	
	public CTAttributes() {
    	LOGGER.info("Cryotron's Attributes Online!");
    	
    	// Registry
    	CTARegistration.init();
    	
    	// Config
		CTAttributesConfig.init();	 	

		setup.preInit();
		
		setup.postInit();
		
		instance = this;
		
		this.setup.attachEventHandlers(MinecraftForge.EVENT_BUS);
		
    	// PreInit
//    	CTASetup.preInit();	    	
//    	
//    	setup.init();
//    	
//    	
//    	// PostInit
//    	CTASetup.postInit();
	}
	
	public static ResourceLocation id(String name) {
		return new ResourceLocation(ID, name.toLowerCase(Locale.ROOT));
	}
	
	  public static boolean isDoingDataGeneration() {
		    return true;
		    		
		    		//DatagenModLoader.isRunningDataGen();
		  }
}



