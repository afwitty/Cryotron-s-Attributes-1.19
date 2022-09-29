package dev.cryotron.attributes.client;

import org.lwjgl.glfw.GLFW;

import com.mojang.blaze3d.platform.InputConstants;

import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;

public class KeySetup {

	public static final String KEY_CATEGORY = "key.category.ctattributes";
	public static final String KEY_CHAR_SHEET = "key.ctattributes.charsheet";
	public static final String KEY_SKILL_TREE = "key.ctattributes.skilltree";
	
	public static final KeyMapping CHAR_SHEET = new KeyMapping( KEY_CHAR_SHEET, KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_J, KEY_CATEGORY );
	public static final KeyMapping SKILL_TREE = new KeyMapping( KEY_SKILL_TREE, KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_K, KEY_CATEGORY );
}
