package dev.cryotron.attributes.common.skilltree;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.realmsclient.util.JsonUtils;

import dev.cryotron.attributes.CTAttributes;
import dev.cryotron.attributes.util.MapStream;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.GsonHelper;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraftforge.fml.LogicalSide;

public class SkillTreeReader extends SimpleJsonResourceReloadListener {

	//public static final SkillTreeReader BEGINNER_SKILL_TREE = new SkillTreeReader();	
	
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
    
    public static final SkillTreeReader INST = new SkillTreeReader();
    
    private SkillTreeReader() {
    	super(GSON,"skilltree");
    }
	
	@Override
	protected void apply(Map<ResourceLocation, JsonElement> dataMap, ResourceManager resourceManager, ProfilerFiller profiler) {
		Collection<JsonObject> loadingNodes = MapStream.of(dataMap)
                .filterKey(key -> !key.getPath().startsWith("_"))			
                .filterValue(JsonElement::isJsonObject)
                .mapValue(JsonElement::getAsJsonObject)
                .valueStream()
                .collect(Collectors.toList());
//		CTAttributes.LOGGER.info("Reading: " + loadingNodes.toString());
//		Object[] arr = loadingNodes.toArray();
//		for (int i = 0; i < arr.length; i++) {
//			CTAttributes.LOGGER.info("Object: " + arr[i]);		
//		}
		SkillTree.SKILL_TREE.updateOriginSkillTree(loadSkillTree(loadingNodes));
		//CTAttributes.LOGGER.info("Contents of the New Tree: " +  SkillTree.SKILL_TREE.getLoginSkillData());
		
		// TODO: Maybe the order of the data loading to the game matters? Might need to get into sorting later. -CT
	}

	public static SkillTreeData loadSkillTree( Collection<JsonObject> nodeObjects ) {
		
		CTAttributes.LOGGER.info("******************");		
		CTAttributes.LOGGER.info("******************");
		
		SkillTreeData newTree = new SkillTreeData();
		int count = 0;
		
		for ( JsonObject serializedSkillData : nodeObjects ) {
		    ResourceLocation skillRegistryName = new ResourceLocation(GsonHelper.getAsString(serializedSkillData, "registry_name"));
		    ResourceLocation customClass = SkillHandler.DEFAULT.getKey();
			
			//String name = GsonHelper.getAsString(serializedSkillData, "registry_name");
			//JsonArray connectors = GsonHelper.getAsJsonArray(serializedSkillData, "connection");
			
			float posX = GsonHelper.getAsFloat(serializedSkillData, "x");
			float posY = GsonHelper.getAsFloat(serializedSkillData, "y");
		    AbstractSkill skill = SkillHandler.convert(skillRegistryName, posX, posY, customClass);
		    
            if (serializedSkillData.has("name")) { // NOTE: Which it currently doesn't
                String name = GsonHelper.getAsString(serializedSkillData, "name");
                skill.setName(name);
            }
            
            // TODO: Implement data later.
			
            LoadedSkillData connector = newTree.addSkill(skill, serializedSkillData);
//            if (serializedSkillData.has("connection")) {
//                JsonArray connectionArray = GsonHelper.getAsJsonArray(serializedSkillData, "connection");
//                for (int i = 0; i < connectionArray.size(); i++) {
//                    JsonElement connection = connectionArray.get(i);
//                    String connectionStr = connection.toString();
//                   // ResourceLocation skillConnect = new ResourceLocation();
////        			CTAttributes.LOGGER.info("Connection: " + connectedPerkKey);
////                    
//                    connector.addConnection(new ResourceLocation( connectionStr));
//                }
//            }
       			
			count++;
		}
		
		CTAttributes.LOGGER.info("Skill Nodes created!: {}", Integer.valueOf(count));
		
		CTAttributes.LOGGER.info("******************");
		CTAttributes.LOGGER.info("******************");
		

		return newTree;
	}
	
}
