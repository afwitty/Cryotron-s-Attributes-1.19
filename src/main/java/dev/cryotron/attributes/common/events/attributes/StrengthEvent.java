package dev.cryotron.attributes.common.events.attributes;

import java.util.UUID;

import dev.cryotron.attributes.CTAttributes;
import dev.cryotron.attributes.setup.deferredregistries.RegisteredAttributes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.living.LivingEvent.LivingTickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = CTAttributes.ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class StrengthEvent {

	@SubscribeEvent
	public static void StrengthTickEvent( LivingTickEvent event ) {
			
		// Get Living Entity's Strength Value.
		double StrengthValue = event.getEntity().getAttributeValue(RegisteredAttributes.STRENGTH.get());
		
		// Every 2 strength gives +1 added Maximum Health
		int LifeGainBonus = (int) (StrengthValue / 2);
		// Every 5 strength gives +1% Increased Melee Physical Damage. Stick to Increased Melee Damage for now.
		int MeleeDamageBonus = (int) (StrengthValue / 5);
		
		LivingEntity le = event.getEntity();
		UUID uuid = UUID.fromString("4ea53d6a-0a46-4169-9606-76d3a5fd72e0");
		AttributeModifier LifeGain = new AttributeModifier(uuid, "life_strength_modifier", LifeGainBonus, Operation.ADDITION);
		//AttributeModifier MeleeGain = new AttributeModifier(uuid, "melee_strength_modifier", (int) StrengthValue / 5, Operation.ADDITION);		
		
		// If the living entity doesn't have a strength modifier, add it.
		if ( !( le.getAttribute(Attributes.MAX_HEALTH).hasModifier(LifeGain)) ) {
			le.getAttribute(Attributes.MAX_HEALTH).addPermanentModifier(LifeGain);
		}
		
		// If the living entity has gained or lost strength permanently, adjust the modifier.
		
		
		
		// If the living entity has gained or lost strength temporarily, adjust the modifier.
		

	}
	
	
}
