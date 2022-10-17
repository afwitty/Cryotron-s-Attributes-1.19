package dev.cryotron.attributes.client.gui;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.awt.*;
import java.util.Collection;

public interface SkillRender {
    @OnlyIn(Dist.CLIENT)
    public void addGroups(Collection<SkillRenderGroup> groups);

//    // Rendered with pos_tex_color
//    @Nullable
//    @OnlyIn(Dist.CLIENT)
//    public Rectangle.Float renderPerkAtBatch(BatchSkillContext drawCtx, PoseStack renderStack,
//                                             AllocationStatus status, long spriteOffsetTick, float pTicks,
//                                             float x, float y, float zLevel, float scale);
}
