package dev.cryotron.attributes.common.skilltree.source;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.fml.LogicalSide;

public interface ModifierSource {
    boolean canApplySource(Player player, LogicalSide dist);

    void onRemove(Player player, LogicalSide dist);

    void onApply(Player player, LogicalSide dist);

    boolean isEqual(ModifierSource other);

    ResourceLocation getProviderName();

}
