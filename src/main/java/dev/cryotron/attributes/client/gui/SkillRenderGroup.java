package dev.cryotron.attributes.client.gui;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SkillRenderGroup {

    private static int counter = 0;
    private final int id;

//    private final List<BatchSkillContext.TextureObjectGroup> addedGroups = Lists.newArrayList();
//    private final Map<AbstractRenderableTexture, Integer> underlyingTextures = Maps.newHashMap();

    public SkillRenderGroup() {
        this.id = counter++;
    }

//    public void add(AbstractRenderableTexture texture, Integer priority) {
//        this.underlyingTextures.put(texture, priority);
//    }
//
//    public void batchRegister(BatchSkillContext ctx) {
//        for (AbstractRenderableTexture tex : underlyingTextures.keySet()) {
//            addedGroups.add(ctx.addContext(tex, underlyingTextures.get(tex)));
//        }
//    }
//
//    @Nullable
//    public BatchSkillContext.TextureObjectGroup getGroup(AbstractRenderableTexture texture) {
//        return MiscUtils.iterativeSearch(addedGroups, grp -> grp.getResource().equals(texture));
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SkillRenderGroup that = (SkillRenderGroup) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
