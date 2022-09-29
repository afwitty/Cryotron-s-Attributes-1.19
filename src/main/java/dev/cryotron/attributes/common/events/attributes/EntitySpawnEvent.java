package dev.cryotron.attributes.common.events.attributes;

import java.util.UUID;

import dev.cryotron.attributes.CTAttributes;
import dev.cryotron.attributes.setup.deferredregistries.RegisteredAttributes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = CTAttributes.ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class EntitySpawnEvent {
	
	/*
	 * Entity Spawn Event - When a new entity spawn, implement the attributes. May be deprecated later.
	 */
	  
		@SubscribeEvent
		public static void EntitySpawnEvent(EntityJoinLevelEvent event)  {
					
			Entity en = event.getEntity();
			
			if (en instanceof LivingEntity) {
				LivingEntity len = (LivingEntity) en;
				UUID uuid = UUID.fromString("b732c0fb-f356-4563-a1b8-d9192ba36b36");
				AttributeModifier am = new AttributeModifier(uuid, "attribute_modifier", 0, Operation.ADDITION);
				
				if (  !(len.getAttribute(RegisteredAttributes.SKYSPACE_LEVEL.get()).hasModifier(am)) ) {
					len.getAttribute(RegisteredAttributes.SKYSPACE_LEVEL.get()).addPermanentModifier(am);
					len.getAttribute(RegisteredAttributes.SKYSPACE_LEVEL.get()).removeModifier(uuid);
				}
				
				if (  !(len.getAttribute(RegisteredAttributes.SKYSPACE_EXPERIENCE.get()).hasModifier(am)) ) {
					len.getAttribute(RegisteredAttributes.SKYSPACE_EXPERIENCE.get()).addPermanentModifier(am);
					len.getAttribute(RegisteredAttributes.SKYSPACE_EXPERIENCE.get()).removeModifier(uuid);
				}
				
				if (  !(len.getAttribute(RegisteredAttributes.STRENGTH.get()).hasModifier(am)) ) {
					len.getAttribute(RegisteredAttributes.STRENGTH.get()).addPermanentModifier(am);
					len.getAttribute(RegisteredAttributes.STRENGTH.get()).removeModifier(uuid);
				}
				
				if (  !(len.getAttribute(RegisteredAttributes.DEXTERITY.get()).hasModifier(am)) ) {
					len.getAttribute(RegisteredAttributes.DEXTERITY.get()).addPermanentModifier(am);
					len.getAttribute(RegisteredAttributes.DEXTERITY.get()).removeModifier(uuid);
				}
				
				if (  !(len.getAttribute(RegisteredAttributes.INTELLIGENCE.get()).hasModifier(am)) ) {
					len.getAttribute(RegisteredAttributes.INTELLIGENCE.get()).addPermanentModifier(am);
					len.getAttribute(RegisteredAttributes.INTELLIGENCE.get()).removeModifier(uuid);
				}
				
				
			}			
		}	
}
