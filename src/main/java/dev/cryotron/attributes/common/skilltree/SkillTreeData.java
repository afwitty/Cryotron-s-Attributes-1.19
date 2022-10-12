package dev.cryotron.attributes.common.skilltree;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.gson.JsonObject;

public class SkillTreeData {

	private final Set<LoadedSkillData> skills = new HashSet<>();
	
	LoadedSkillData addSkill(AbstractSkill skill, JsonObject skillDataObject) {
		LoadedSkillData data = new LoadedSkillData(skill, skillDataObject);
		this.skills.add(data);
		return data;
	}
//	
//	public PreparedSkillTreeData prepare() {
//		return PreparedSkillTreeData.create(this.skills);
//	}
//	
  public Collection<JsonObject> getAsDataTree() {
	    return (Collection<JsonObject>)this.skills.stream().map(LoadedSkillData::getSkillDataObject).collect(Collectors.toList());
  }
	
}
