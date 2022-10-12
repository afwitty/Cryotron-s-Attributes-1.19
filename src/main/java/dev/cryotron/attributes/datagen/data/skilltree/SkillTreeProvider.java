package dev.cryotron.attributes.datagen.data.skilltree;

import java.util.function.Consumer;

import dev.cryotron.attributes.CTAttributes;
import dev.cryotron.attributes.common.skilltree.SkillHandler;
import dev.cryotron.attributes.common.skilltree.builder.SkillDataBuilder;
import dev.cryotron.attributes.common.skilltree.builder.SkillDataProvider;

import net.minecraft.data.DataGenerator;

public class SkillTreeProvider extends SkillDataProvider {

	public SkillTreeProvider(DataGenerator generator) {
		super(generator);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void registerSkills(Consumer<FinishedSkill> registrar) {
		registerBeginnerTree(registrar);
	}
	
	private void registerBeginnerTree(Consumer<FinishedSkill> registrar) {
	    SkillDataBuilder.ofType(SkillHandler.DEFAULT)
	      .create(CTAttributes.id("beginner_node_1"), 0.0F, 8.0F)
	      .build(registrar);
	    SkillDataBuilder.ofType(SkillHandler.DEFAULT)
	      .create(CTAttributes.id("beginner_node_2"), 0.0F, 16.0F)
	      .connect(CTAttributes.id("beginner_node_1"))
	      .build(registrar);
	    SkillDataBuilder.ofType(SkillHandler.DEFAULT)
	      .create(CTAttributes.id("beginner_node_3"), 0.0F, 24.0F)
	      .connect(CTAttributes.id("beginner_node_2"))
	      .build(registrar);
	    SkillDataBuilder.ofType(SkillHandler.DEFAULT)
	      .create(CTAttributes.id("beginner_node_4"), 0.0F, 32.0F)
	      .connect(CTAttributes.id("beginner_node_3"))
	      .build(registrar);
	    SkillDataBuilder.ofType(SkillHandler.DEFAULT)
	      .create(CTAttributes.id("beginner_node_5"), 0.0F, 40.0F)
	      .connect(CTAttributes.id("beginner_node_4"))
	      .build(registrar);
	}


}
