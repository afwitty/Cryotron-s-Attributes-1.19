package dev.cryotron.attributes.common.skilltree;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.Collection;
import java.util.Objects;

import javax.annotation.Nullable;

import com.mojang.blaze3d.vertex.BufferBuilder;

import dev.cryotron.attributes.client.gui.SkillPointRenderGroup;
import dev.cryotron.attributes.client.gui.SkillRender;
import dev.cryotron.attributes.client.gui.SkillRenderGroup;
import net.minecraft.util.Tuple;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class SkillTreePoint<T extends AbstractSkill> implements SkillRender {
    private final Point.Float offset;
    private final T skill;
    private int renderSize;

    private static final int spriteSize = 11;

    public SkillTreePoint(T skill, Point.Float offset) {
        this.offset = offset;
        this.skill = skill;
        this.renderSize = spriteSize;
    }
    
    @Override
    @OnlyIn(Dist.CLIENT)
    public void addGroups(Collection<SkillRenderGroup> groups) {
        groups.add(SkillPointRenderGroup.INSTANCE);
    }

    public void setRenderSize(int renderSize) {
        this.renderSize = renderSize;
    }

    public int getRenderSize() {
        return renderSize;
    }

    public T getSkill() {
        return skill;
    }

    public Point.Float getOffset() {
        return offset;
    }

//    @Nullable
//    @Override
//    @OnlyIn(Dist.CLIENT)
//    public Rectangle.Float renderPerkAtBatch(BatchPerkContext drawCtx, MatrixStack renderStack,
//                                             AllocationStatus status, long spriteOffsetTick, float pTicks,
//                                             float x, float y, float zLevel, float scale) {
//        SpriteSheetResource tex = status.getPerkTreeSprite();
//        BatchPerkContext.TextureObjectGroup grp = PerkPointRenderGroup.INSTANCE.getGroup(tex);
//        if (grp == null) {
//            return new Rectangle.Float();
//        }
//        BufferBuilder buf = drawCtx.getContext(grp);
//
//        float size = renderSize * scale;
//        Tuple<Float, Float> frameUV = tex.getUVOffset(spriteOffsetTick);
//
//        RenderingGuiUtils.rect(buf, renderStack, x - size, y - size, zLevel, size * 2F, size * 2F)
//                .tex(frameUV.getA(), frameUV.getB(), tex.getULength(), tex.getVLength())
//                .draw();
//        return new Rectangle.Float(-size, -size, size * 2, size * 2);
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SkillTreePoint that = (SkillTreePoint) o;
        return Objects.equals(offset, that.offset);
    }

    @Override
    public int hashCode() {
        return Objects.hash(offset);
    }

}
