package dev.cryotron.attributes.setup;

import java.util.List;

import dev.cryotron.attributes.CTAttributes;
import dev.cryotron.attributes.client.events.ClientTickEvent;
import dev.cryotron.attributes.networking.NetworkHandler;
import dev.cryotron.attributes.setup.deferredregistries.RegisteredAttributes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityAttributeModificationEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod.EventBusSubscriber(modid = CTAttributes.ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CTASetup {
	
	public static final 	IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
	public static final String TAB_NAME = "ctattributes_group";
    public static final CreativeModeTab ITEM_GROUP = new CreativeModeTab(TAB_NAME) {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(Items.DEEPSLATE_LAPIS_ORE);
        }
    };

	public static void preInit() {
		
		modEventBus.addListener(CTASetup::commonSetup);
        modEventBus.addListener(EventPriority.NORMAL, false, FMLClientSetupEvent.class, CTASetup::clientSetup);        
	}
	
	public static void postInit() {
        IEventBus forgeBus = MinecraftForge.EVENT_BUS;
	}
	
    public static void commonSetup(final FMLCommonSetupEvent event) {
    	
    	NetworkHandler.init();
	
    }
    
    public static void clientSetup(FMLClientSetupEvent event) {	

    	ClientTickEvent.init();
    	
    }
    
	  @SubscribeEvent
	  public static void adjustSkyspaceAttribute( EntityAttributeModificationEvent event ) {	  
		  List<EntityType<? extends LivingEntity>> leList = event.getTypes();
		  for ( EntityType<? extends LivingEntity> le : leList ) {
			  //CTAttributes.LOGGER.info("Entity Attribute Modification Event Triggered: " + le.toString());
			    event.add(le, RegisteredAttributes.SKYSPACE_LEVEL.get());
			    event.add(le, RegisteredAttributes.SKYSPACE_EXPERIENCE.get());
			  
				event.add(le, RegisteredAttributes.STRENGTH.get());
			  	event.add(le, RegisteredAttributes.DEXTERITY.get());
			  	event.add(le, RegisteredAttributes.INTELLIGENCE.get());
		  }

//		  event.add(EntityType.PLAYER, RegisteredAttributes.ENERGY_SHIELD_RECHARGE.get());
//		  event.add(RegisteredEntities.KYROSIAN_ARCHON.get(), RegisteredAttributes.ENERGY_SHIELD_RECHARGE.get());
//		  event.add(EntityType.PLAYER, RegisteredAttributes.ES_FASTER_RECHARGE.get());
//		  event.add(EntityType.PLAYER, RegisteredAttributes.ES_RECHARGE_RATE.get());
//		  event.add(EntityType.PLAYER, RegisteredAttributes.ES_REGEN.get());
//		  
//		  event.add(EntityType.PLAYER, RegisteredAttributes.MAX_MANA.get());
		  
		  CTAttributes.LOGGER.info("Attributes modified to ALL Living Entities.");	  
		    
	  }
}
