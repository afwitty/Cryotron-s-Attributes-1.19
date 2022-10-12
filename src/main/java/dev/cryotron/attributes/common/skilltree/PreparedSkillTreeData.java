package dev.cryotron.attributes.common.skilltree;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;

import net.minecraft.util.Tuple;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class PreparedSkillTreeData {
	
	  private final Map<AbstractSkill, Collection<AbstractSkill>> doubleConnections = new HashMap<>();
	
	  private final List<Tuple<AbstractSkill, AbstractSkill>> connections = new LinkedList<>();
	  
	  public Collection<AbstractSkill> getConnectedSkills(AbstractSkill skill) {
		    return this.doubleConnections.getOrDefault(skill, Lists.newArrayList());
		  }
	
	  @OnlyIn(Dist.CLIENT)
	  public Collection<Tuple<AbstractSkill, AbstractSkill>> getConnections() {
	    return Collections.unmodifiableList(this.connections);
	  }
}
