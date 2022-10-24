package dev.cryotron.attributes.common.skilltree;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.google.gson.JsonObject;

import net.minecraft.resources.ResourceLocation;

public class LoadedSkillData {
    private final AbstractSkill skill;
    private final JsonObject skillDataObject;
    private final Set<ResourceLocation> connections = new HashSet<>();

    public LoadedSkillData(AbstractSkill skill, JsonObject skillDataObject) {
        this.skill = skill;
        this.skillDataObject = skillDataObject;
    }

    void addConnection(ResourceLocation to) {
        this.connections.add(to);
    }

    public Set<ResourceLocation> getConnections() {
        return Collections.unmodifiableSet(connections);
    }

    public AbstractSkill getSkill() {
        return skill;
    }

    public JsonObject getSkillDataObject() {
        return skillDataObject;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoadedSkillData that = (LoadedSkillData) o;
        return Objects.equals(skill.getRegistryName(), that.skill.getRegistryName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(skill.getRegistryName());
    }

}
