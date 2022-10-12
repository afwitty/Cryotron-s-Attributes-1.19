package dev.cryotron.attributes.common.skilltree.builder;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import com.google.common.collect.ImmutableList;

import dev.cryotron.attributes.common.skilltree.AbstractSkill;
import dev.cryotron.attributes.common.skilltree.SkillHandler;
import net.minecraft.resources.ResourceLocation;

public class SkillDataBuilder<T extends AbstractSkill> {
	  private final T skill;
	  
	  private final List<ResourceLocation> connections = new ArrayList<>();
	  
	  public SkillDataBuilder(T skill) {
	    this.skill = skill;
	  }
	  
	  public static SkillBuilder<AbstractSkill> builder() {
	    return new SkillBuilder<>(SkillHandler.DEFAULT);
	  }
	  
	  public static <T extends AbstractSkill> SkillBuilder<T> ofType(SkillHandler.Type<T> skillType) {
	    return new SkillBuilder<>(skillType);
	  }
	  
	  public SkillDataBuilder<T> setName(String skillDisplayName) {
	    this.skill.setName(skillDisplayName);
	    return this;
	  }
	  
//	  public SkillDataBuilder<T> addModifier(float modifier, ModifierType mode, SkillAttributeType type) {
//	    if (!(this.skill instanceof AttributeModifierSkill))
//	      throw new IllegalArgumentException("Cannot add modifiers to non-modifier skills!"); 
//	    ((AttributeModifierSkill)this.skill).addModifier(modifier, mode, type);
//	    return this;
//	  }
//	  
//	  public SkillDataBuilder<T> addModifier(SkillAttributeModifier modifier) {
//	    if (!(this.skill instanceof AttributeModifierSkill))
//	      throw new IllegalArgumentException("Cannot add modifiers to non-modifier skills!"); 
//	    ((AttributeModifierSkill)this.skill).addModifier(modifier);
//	    return this;
//	  }
//	  
//	  public SkillDataBuilder<T> addConverter(SkillConverter converter) {
//	    if (!(this.skill instanceof AttributeConverterSkill))
//	      throw new IllegalArgumentException("Cannot add converter to non-converter skills!"); 
//	    ((AttributeConverterSkill)this.skill).addConverter(converter);
//	    return this;
//	  }
	  
	  public SkillDataBuilder<T> modify(Consumer<T> recipeFn) {
	    recipeFn.accept(this.skill);
	    return this;
	  }
	  
	  public SkillDataBuilder<?> chain(SkillDataBuilder<?> other) {
	    connect(other.skill.getRegistryName());
	    return other;
	  }
	  
	  public SkillDataBuilder<T> connect(SkillDataBuilder<?> other) {
	    return connect(other.skill.getRegistryName());
	  }
	  
	  public SkillDataBuilder<T> connect(ResourceLocation key) {
	    this.connections.add(key);
	    return this;
	  }
	  
	  public SkillDataBuilder<T> build(Consumer<SkillDataProvider.FinishedSkill> consumerIn) {
	    consumerIn.accept(new SkillDataProvider.FinishedSkill((AbstractSkill)this.skill, (List<ResourceLocation>)ImmutableList.copyOf(this.connections)));
	    return this;
	  }
	  
	  public static class SkillBuilder<T extends AbstractSkill> {
	    private final SkillHandler.Type<T> skillType;
	    
	    private SkillBuilder(SkillHandler.Type<T> skillType) {
	      this.skillType = skillType;
	    }
	    
	    public SkillDataBuilder<T> create(ResourceLocation skillKey, float x, float y) {
	      AbstractSkill abstractSkill = this.skillType.convert(skillKey, x, y);
	      if (!this.skillType.getKey().equals(SkillHandler.DEFAULT.getKey()))
	        abstractSkill.setCustomSkillType(this.skillType.getKey()); 
	      return new SkillDataBuilder<>((T)abstractSkill);
	    }
	  }
}
