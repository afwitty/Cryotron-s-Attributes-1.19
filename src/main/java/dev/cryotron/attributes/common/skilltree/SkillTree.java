package dev.cryotron.attributes.common.skilltree;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.function.Predicate;

import javax.annotation.Nullable;

import com.google.gson.JsonObject;

import dev.cryotron.attributes.CTAttributes;
import dev.cryotron.attributes.util.SidedReference;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Tuple;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.LogicalSide;

public class SkillTree {

    public static final SkillTree SKILL_TREE = new SkillTree();
    
    private SkillTreeData loadedSkillTree = null;
    
    private final SidedReference<PreparedSkillTreeData> treeData = new SidedReference();
	
    public Optional<PreparedSkillTreeData> getData(LogicalSide side) {
        return this.treeData.getData(side);
      }
      
//      public Optional<AbstractSkill> getSkill(LogicalSide side, ResourceLocation key) {
//        return getSkill(side, Skill -> key.equals(Skill.getRegistryName()));
//      }
      
//      public Optional<AbstractSkill> getSkill(LogicalSide side, Predicate<AbstractSkill> test) {
//        return getData(side).flatMap(data -> data.getSkill(test));
//      }
//      
//      public Optional<? extends AbstractSkill> getSkill(LogicalSide side, float x, float y) {
//        return getData(side).flatMap(data -> data.getSkill(x, y));
//      }
      
//      @Nullable
//      public RootSkill getRootSkill(LogicalSide side, IConstellation constellation) {
//        return getData(side).<RootSkill>map(data -> data.getRootSkill(constellation)).orElse(null);
//      }
      
      public Collection<AbstractSkill> getConnectedSkills(LogicalSide side, AbstractSkill Skill) {
        return getData(side).<Collection<AbstractSkill>>map(data -> data.getConnectedSkills(Skill)).orElse(Collections.emptyList());
      }
      
//      public Collection<SkillTreePoint<?>> getSkillPoints(LogicalSide side) {
//        return getData(side).<Collection<SkillTreePoint<?>>>map(PreparedSkillTreeData::getSkillPoints).orElse(Collections.emptyList());
//      }
      
      @OnlyIn(Dist.CLIENT)
      public Collection<Tuple<AbstractSkill, AbstractSkill>> getConnections() {
        return getData(LogicalSide.CLIENT).<Collection<Tuple<AbstractSkill, AbstractSkill>>>map(PreparedSkillTreeData::getConnections).orElse(Collections.emptyList());
      }
      
//      public Optional<Long> getVersion(LogicalSide side) {
//        return getData(side).map(PreparedSkillTreeData::getVersion);
//      }
      
      public void updateOriginSkillTree(SkillTreeData SkillTree) {
        this.loadedSkillTree = SkillTree;
      }
      
      public Optional<Collection<JsonObject>> getLoginSkillData() {
        return Optional.<SkillTreeData>ofNullable(this.loadedSkillTree).map(SkillTreeData::getAsDataTree);
      }
      
      @OnlyIn(Dist.CLIENT)
      public void receiveSkillTree(PreparedSkillTreeData serverTreeData) {
        updateTreeData(LogicalSide.CLIENT, serverTreeData);
      }
      
      public void clearCache(LogicalSide side) {
       //getData(side).ifPresent(data -> data.clearSkillCache(side));
        updateTreeData(side, null);
      }
      
      public void setupServerSkillTree() {
        if (this.loadedSkillTree != null) {
          //updateTreeData(LogicalSide.SERVER, this.loadedSkillTree.prepare());
          CTAttributes.LOGGER.info("Loaded SkillTree!");
        } else {
          CTAttributes.LOGGER.info("No SkillTree data found!");
        } 
      }
      
      private void updateTreeData(LogicalSide side, @Nullable PreparedSkillTreeData newData) {
          this.treeData.setData(side, newData); 
      }
}
