package dev.cryotron.attributes.common.skilltree.builder;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.List;
import java.util.function.Consumer;

import com.google.gson.*;

import dev.cryotron.attributes.CTAttributes;
import dev.cryotron.attributes.common.skilltree.AbstractSkill;
import net.minecraft.data.*;
import net.minecraft.resources.ResourceLocation;

public abstract class SkillDataProvider implements DataProvider {

	   private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
	   
	   protected final DataGenerator generator;

	   public SkillDataProvider(DataGenerator generator) {
	        this.generator = generator;
	   }

	   public abstract void registerSkills(Consumer<FinishedSkill> registrar);
	  	   
	   @Override
	   public void run(CachedOutput cache) {
	        Path path = this.generator.getOutputFolder();

	        List<FinishedSkill> builtSkills = new ArrayList<>();
	        
	        this.registerSkills(finishedSkill -> {
	            ResourceLocation SkillName = finishedSkill.skill.getRegistryName();
	            Point.Float offset = finishedSkill.skill.getOffset();
	            if (builtSkills.stream().anyMatch(knownSkill -> knownSkill.skill.getOffset().equals(offset))) {
	                throw new IllegalArgumentException("Duplicate Skill registration at " + offset + " for " + SkillName);
	            }
	            if (builtSkills.contains(finishedSkill)) {
	                throw new IllegalArgumentException("Duplicate Skill registry name: " + SkillName);
	            }
	            builtSkills.add(finishedSkill);
	            this.saveSkillFile(cache, finishedSkill.serialize(), path.resolve(String.format("data/%s/skilltree/%s.json", SkillName.getNamespace(), SkillName.getPath())));
	        });

	        JsonObject allSkills = new JsonObject();
	        builtSkills.sort(Comparator.naturalOrder());
	        builtSkills.forEach(Skill -> allSkills.add(Skill.skill.getRegistryName().toString(), Skill.serialize()));
	        this.saveSkillFile(cache, allSkills, path.resolve("data/cryoattributes/skilltree/_beginner_skill_tree.json"));
	   }

	   private void saveSkillFile(CachedOutput cache, JsonElement Skill, Path filePath) {
	        try {
//	            String skillJson = GSON.toJson(Skill);
//
//                Files.createDirectories(filePath.getParent());
//                try (BufferedWriter bufferedwriter = Files.newBufferedWriter(filePath)) {
//                    bufferedwriter.write(skillJson);
//                }
                
                DataProvider.saveStable(cache, Skill, filePath);
	         
//	            String SkillHash = HASH_FUNCTION.hashUnencodedChars(SkillJson).toString();
//	            if (!Objects.equals(cache.getPreviousHash(filePath), SkillHash) || !Files.exists(filePath)) {

//	            }
//
//	            cache.recordHash(filePath, SkillHash);
	        } catch (IOException exc) {
	            CTAttributes.LOGGER.error("Couldn't save skill {}", filePath, exc);
	        }
	   }
	   
	   @Override
	   public String getName() {
	        return "skilltree";
	   }

	   public static class FinishedSkill implements Comparable<FinishedSkill> {

	        public final AbstractSkill skill;
	        private final List<ResourceLocation> connections;

	        public FinishedSkill(AbstractSkill skill, List<ResourceLocation> connections) {
	            this.skill = skill;
	            this.connections = connections;
	        }

	        public JsonObject serialize() {
	            JsonObject object = this.skill.serializeSkill();
	            JsonArray array = new JsonArray();
	            for (ResourceLocation connection : this.connections) {
	                array.add(connection.toString());
	            }
	            object.add("connection", array);
	            return object;
	        }

	        @Override
	        public int compareTo(FinishedSkill that) {
	            return this.skill.getRegistryName().compareTo(that.skill.getRegistryName());
	        }

	        @Override
	        public boolean equals(Object o) {
	            if (this == o) return true;
	            if (o == null || getClass() != o.getClass()) return false;
	            FinishedSkill that = (FinishedSkill) o;
	            return Objects.equals(skill.getRegistryName(), that.skill.getRegistryName());
	        }

	        @Override
	        public int hashCode() {
	            return Objects.hash(skill.getRegistryName());
	        }
	   }
	
}
