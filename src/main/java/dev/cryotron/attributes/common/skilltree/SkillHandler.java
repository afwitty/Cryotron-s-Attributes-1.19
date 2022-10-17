package dev.cryotron.attributes.common.skilltree;

import java.util.HashMap;
import java.util.Map;

import dev.cryotron.attributes.CTAttributes;
import dev.cryotron.attributes.util.TriFunction;
import net.minecraft.resources.ResourceLocation;


public class SkillHandler {
	
	  private static final Map<ResourceLocation, Type<? extends AbstractSkill>> CONVERTER_MAP = new HashMap<>();
	  
	  public static final Type<AbstractSkill> DEFAULT = new Type<>(CTAttributes.id("default"), AbstractSkill::new);
	
	  public static <T extends AbstractSkill> Type<T> registerConverter(ResourceLocation name, TriFunction<ResourceLocation, Float, Float, T> converter) {
		    Type<T> type = new Type<>(name, converter);
		    CONVERTER_MAP.put(name, type);
		    return type;
		  }

	  public static <T extends AbstractSkill> T convert(ResourceLocation perkKey, float x, float y, ResourceLocation alternativeBase) {
		    return ((Type<T>)CONVERTER_MAP.getOrDefault(alternativeBase, DEFAULT)).convert(perkKey, x, y);
		  }
		  
		public static void init() {
			registerConverter(DEFAULT.getKey(), AbstractSkill::new);
		}
	
	  public static class Type<T extends AbstractSkill> {
		    private final ResourceLocation key;
		    
		    private final TriFunction<ResourceLocation, Float, Float, T> converter;
		    
		    private Type(ResourceLocation key, TriFunction<ResourceLocation, Float, Float, T> converter) {
		      this.key = key;
		      this.converter = converter;
		    }
		    
		    public T convert(ResourceLocation perkKey, float x, float y) {
		      return (T)this.converter.apply(perkKey, Float.valueOf(x), Float.valueOf(y));
		    }
		    
		    public final ResourceLocation getKey() {
		      return this.key;
		    }
		  }
	
}
