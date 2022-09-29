package dev.cryotron.attributes.setup.deferredregistries;


import dev.cryotron.attributes.CTAttributes;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class RegisteredAttributes {
    public static final DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister.create(ForgeRegistries.ATTRIBUTES, CTAttributes.ID);
    
    // Character Profile
	public static final RegistryObject<Attribute> SKYSPACE_LEVEL = ATTRIBUTES.register("skyspace_level", () -> new RangedAttribute("skyspace_level", 0, 0, 60).setSyncable(true));
	public static final RegistryObject<Attribute> SKYSPACE_EXPERIENCE = ATTRIBUTES.register("skyspace_experience", () -> new RangedAttribute("skyspace_experience", 0.0D, 0.0D, Double.MAX_VALUE).setSyncable(true));
    
    // Attributes
	public static final RegistryObject<Attribute> STRENGTH = ATTRIBUTES.register("char_strength", () -> new RangedAttribute("char_strength", 0.0D, 0.0D, 1048576.0D).setSyncable(true));
	public static final RegistryObject<Attribute> DEXTERITY = ATTRIBUTES.register("char_dexterity", () -> new RangedAttribute("char_dexterity", 0.0D, 0.0D, 1048576.0D).setSyncable(true));
	public static final RegistryObject<Attribute> INTELLIGENCE = ATTRIBUTES.register("char_intelligence", () -> new RangedAttribute("char_intelligence", 0.0D, 0.0D, 1048576.0D).setSyncable(true));    
    
	
	// Energy Shield
	public static final RegistryObject<Attribute> MAX_ENERGY_SHIELD = ATTRIBUTES.register("max_energy_shield", () -> new RangedAttribute("max_energy_shield", 0.0D, 0.0D, 1024.0D).setSyncable(true));
	public static final RegistryObject<Attribute> ENERGY_SHIELD_RECHARGE = ATTRIBUTES.register("energy_shield_recharge", () -> new RangedAttribute("energy_shield_recharge", 4.0D, 0.0D, 1024.0D).setSyncable(true));
	
	public static final RegistryObject<Attribute> ES_FASTER_RECHARGE = ATTRIBUTES.register("es_faster_recharge", () -> new RangedAttribute("es_faster_recharge", 0.0D, 0.0D, 1024.0D).setSyncable(true));
	public static final RegistryObject<Attribute> ES_RECHARGE_RATE = ATTRIBUTES.register("es_recharge_rate", () -> new RangedAttribute("es_recharge_rate", 0.0D, 0.0D, 1024.0D).setSyncable(true));
	public static final RegistryObject<Attribute> ES_REGEN = ATTRIBUTES.register("es_regen", () -> new RangedAttribute("es_regen", 0.0D, 0.0D, 1024.0D).setSyncable(true));
	
	// Evasion
	public static final RegistryObject<Attribute> EVASION = ATTRIBUTES.register("evasion", () -> new RangedAttribute("evasion", 1.0D, 0.0D, 1024.0D).setSyncable(true));	
	public static final RegistryObject<Attribute> ENTROPY = ATTRIBUTES.register("entropy", () -> new RangedAttribute("entropy", 1.0D, 0.0D, 1024.0D).setSyncable(true));	
	
	public static final RegistryObject<Attribute> EV_CHANCE = ATTRIBUTES.register("ev_chance", () -> new RangedAttribute("ev_chance", 1.0D, 0.0D, 1024.0D).setSyncable(true));	
	public static final RegistryObject<Attribute> EV_ENTROPY_RESET = ATTRIBUTES.register("ev_entropy_reset", () -> new RangedAttribute("ev_entropy_reset", 1.0D, 0.0D, 1024.0D).setSyncable(true));	
	
	// I'm not even sure some of these attributes are capabilities.

}
