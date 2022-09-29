package dev.cryotron.attributes.setup.deferredregistries;

import dev.cryotron.attributes.CTAttributes;
import dev.cryotron.attributes.setup.CTASetup;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class RegisteredItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, CTAttributes.ID);
    public static final Item.Properties ITEM_PROPERTIES = new Item.Properties().tab(CTASetup.ITEM_GROUP);
    
    public static final RegistryObject<Item> TOTEM_OF_INVULNERABILITY = ITEMS.register("totem_of_invulnerability", () -> new Item(new Item.Properties().stacksTo(1).tab(CTASetup.ITEM_GROUP).rarity(Rarity.EPIC)));
}
