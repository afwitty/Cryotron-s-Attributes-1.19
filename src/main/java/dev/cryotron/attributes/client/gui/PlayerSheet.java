package dev.cryotron.attributes.client.gui;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.bridge.game.Language;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class PlayerSheet extends Screen {
	
	public static Language currentLanguage = Minecraft.getInstance().getLanguageManager().getSelected();
	Minecraft mc = Minecraft.getInstance();
	
	protected static PlayerSheet instance;
	protected Player player;
	
	protected static final int GUI_WIDTH = 1024;
	protected static final int GUI_HEIGHT = 512;
	protected static final int BACKGROUND_TEXTURE_WIDTH = 976;
	protected static final int BACKGROUND_TEXTURE_HEIGHT = 480;
	protected static final float SCALE = 0.45f;
	
	protected static int scaledRootX;
	protected static int scaledRootY;
	protected static int scaledTabRootX;
	protected static int scaledTabRootY;
	
	private static Screen tabScreen;

	public PlayerSheet(Player player) {
		super(Component.translatable("gui.ctattributes.charactersheet.title"));
		
		this.player = player;
		instance = this;
		tabScreen = null;
		currentLanguage = Minecraft.getInstance().getLanguageManager().getSelected();
	}
	
	@Override
	public void render(PoseStack matrix, int mouseX, int mouseY, float partialTicks) {
		renderBackground(matrix);
		//renderDirtBackground(0);

		//RenderSystem.setShader(GameRenderer::getPositionTexColorShader);
		
		matrix.pushPose();
		//RenderSystem.setShaderColor(1, 1, 1, 1);
		matrix.scale(SCALE, SCALE, SCALE);
		
		
		
		//RenderSystem.setShaderColor(1, 1, 1, 1);
		matrix.popPose();
	}
	
	@Override
	   public boolean isPauseScreen() {
	      return false;
	   }
	

}
