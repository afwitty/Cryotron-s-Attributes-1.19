package dev.cryotron.attributes.client.gui;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat;
import com.mojang.bridge.game.Language;

import dev.cryotron.attributes.CTAttributes;
import dev.cryotron.attributes.client.KeySetup;
import dev.cryotron.attributes.common.skilltree.SkillTree;
import dev.cryotron.attributes.common.skilltree.SkillTreeReader;
import dev.cryotron.attributes.setup.deferredregistries.RegisteredSounds;
import dev.cryotron.attributes.util.MapStream;
//import dev.cryotron.utilities.util.*;
import dev.cryotron.utilities.util.aoa.RenderUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Widget;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.LogicalSide;

public class PlayerSkillTree extends Screen {

	public static Language currentLanguage = Minecraft.getInstance().getLanguageManager().getSelected();
	public static final ResourceLocation SS_BACKGROUND_LOCATION = new ResourceLocation(CTAttributes.ID,"textures/gui/ss_options_background.png");
	public static final ResourceLocation SS_NODE = new ResourceLocation(CTAttributes.ID,"textures/gui/skillnode.png");

	static SoundInstance sound = SimpleSoundInstance.forUI(RegisteredSounds.SKILL_TREE.get(), 1.0f);
	private static int soundTick = 0;
	private float xMouse;
	private float yMouse;
	private double scrollX;
	private double scrollY;
	
	protected static PlayerSkillTree instance;
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

	public PlayerSkillTree(Player player) {
		super(Component.translatable("gui.ctattributes.characterskilltree.title"));
		
		this.player = player;
		instance = this;
		tabScreen = null;
		currentLanguage = Minecraft.getInstance().getLanguageManager().getSelected();

	}
	
	@Override
	public void render(PoseStack matrix, int mouseX, int mouseY, float partialTicks) {
		
		Component exit = Component.translatable("gui.ctattributes.characterskilltree.exit");
		
		xMouse = mouseX;
		yMouse = mouseY;
		
		renderDirtBackground(0);
		renderNodes(matrix);
		//RenderSystem.setShader(GameRenderer::getPositionTexColorShader);
		
		renderTooltip(matrix, title, 5, 0);
		//CTAttributes.LOGGER.info("This.width is: " + this.width);
	
		matrix.pushPose();
		//RenderSystem.setShaderColor(1, 1, 1, 1);
		matrix.scale(SCALE, SCALE, SCALE);
		
		RenderSystem.setShaderColor(0, 0, 1, 1);
		renderTooltip(matrix, exit, (int) (((float)this.width)*2.22), 5); // There is gotta be a better way to place the "Esc" tip in the Skill Tree. -CT
		

		
		//RenderSystem.setShaderColor(1, 1, 1, 1);
		matrix.popPose();
		
		


	}
	
	@Override
	 public void renderDirtBackground(int p_96627_) {
	      Tesselator tesselator = Tesselator.getInstance();
	      BufferBuilder bufferbuilder = tesselator.getBuilder();
	      RenderSystem.setShader(GameRenderer::getPositionTexColorShader);
	      RenderSystem.setShaderTexture(0, SS_BACKGROUND_LOCATION);
	      RenderSystem.setShaderColor(0.1F, 0.1F, 0.15F, 1.0F);
	      float f = 32.0F;
	      
	      double theight = this.height;
	      double twidth = this.width;
	      
	      bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX_COLOR);
	      bufferbuilder.vertex(-8.0D, theight, 0.0D).uv(0.0F, (float) theight / f + (float) p_96627_).color(64, 64, 64, 255).endVertex();
	      bufferbuilder.vertex(twidth, theight, 0.0D).uv((float) twidth / f, (float) theight / f + (float) p_96627_).color(64, 64, 64, 255).endVertex();
	      bufferbuilder.vertex(twidth, -8.0D, 0.0D).uv((float) twidth / f, (float) p_96627_).color(64, 64, 64, 255).endVertex();
	      bufferbuilder.vertex(-8.0D, -1.0D, 0.0D).uv(0.0F, (float) p_96627_).color(64, 64, 64, 255).endVertex();
	      
//	      bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX_COLOR);
//	      bufferbuilder.vertex(0.0D, (double)this.height, 0.0D).uv(0.0F, (float)this.height / 32.0F + (float)p_96627_).color(64, 64, 64, 255).endVertex();
//	      bufferbuilder.vertex((double)this.width, (double)this.height, 0.0D).uv((float)this.width / 32.0F, (float)this.height / 32.0F + (float)p_96627_).color(64, 64, 64, 255).endVertex();
//	      bufferbuilder.vertex((double)this.width, 0.0D, 0.0D).uv((float)this.width / 32.0F, (float)p_96627_).color(64, 64, 64, 255).endVertex();
//	      bufferbuilder.vertex(0.0D, 0.0D, 0.0D).uv(0.0F, (float)p_96627_).color(64, 64, 64, 255).endVertex();
	      
	      tesselator.end();
	      net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(new net.minecraftforge.client.event.ScreenEvent.BackgroundRendered(this, new PoseStack()));
	 }
	
	@Override
	public void onClose() {
		Minecraft.getInstance().getSoundManager().stop(sound);
		soundTick = 0;
		this.minecraft.popGuiLayer();
	}
	
	@Override
	public void tick() {
		if (!(Minecraft.getInstance().getSoundManager().isActive(sound)) || soundTick == 2258) {
			Minecraft.getInstance().getSoundManager().stop(sound);
			Minecraft.getInstance().getSoundManager().play(sound);
			soundTick = 0;
		}
		else {
			soundTick++;
		}
		
	}
	
	@Override
    public boolean isPauseScreen() {
       return false;
    }
	
   public boolean keyPressed(int p_97353_, int p_97354_, int p_97355_) {
      if (KeySetup.SKILL_TREE.matches(p_97353_, p_97354_)) {
         this.minecraft.setScreen((Screen)null);
         this.minecraft.mouseHandler.grabMouse();
		Minecraft.getInstance().getSoundManager().stop(sound);
         return true;
      } else {
         return super.keyPressed(p_97353_, p_97354_, p_97355_);
      }
   }
   
   public boolean mouseClicked(double p_97343_, double p_97344_, int p_97345_) {
	      if (p_97345_ == 0) {
	         int i = (this.width - 252) / 2;
	         int j = (this.height - 140) / 2;
	         
	         CTAttributes.LOGGER.info("A mouse has been clicked in " + xMouse + "," + yMouse);

//	         for(AdvancementTab advancementtab : this.tabs.values()) {
//	            if (advancementtab.getPage() == tabPage && advancementtab.isMouseOver(i, j, p_97343_, p_97344_)) {
//	               this.advancements.setSelectedTab(advancementtab.getAdvancement(), true);
//	               break;
//	            }
//	         }
	      }

	      return super.mouseClicked(p_97343_, p_97344_, p_97345_);
	   }
   
   public void renderNodes(PoseStack matrix) {
	   // TODO: Find a way to render a node.
	   
	   float NSCALE = 0.20f;
	   int NSCALEX = (int) ((1/NSCALE));
	   int NSCALEY = (int) ((1/NSCALE));
	   
	   //CTAttributes.LOGGER.info(SkillTree.SKILL_TREE.getData(LogicalSide.CLIENT));	  
	   
	   Optional<Collection<JsonObject>> skillTree = SkillTree.SKILL_TREE.getLoginSkillData();
	   Collection<JsonObject> skillTreeData = skillTree.get();

	   for (JsonObject serializedSkillData : skillTreeData) {
		   int posX = GsonHelper.getAsInt(serializedSkillData, "x");
		   int posY = GsonHelper.getAsInt(serializedSkillData, "y");
		   
		    matrix.pushPose();
		    matrix.scale(NSCALE,NSCALE,NSCALE);
			RenderSystem.setShaderColor(1, 1, 1, 1);
		    RenderSystem.setShader(GameRenderer::getPositionTexShader);
		    
			RenderSystem.setShaderTexture(0, SS_NODE);
			RenderUtil.renderCustomSizedTexture(matrix, (this.width/2 * NSCALEX) + (posX * NSCALEX), (this.height/2 * NSCALEY) + (posY * NSCALEY), 40,40,40,40,40, 40);

			matrix.popPose();
	   }
		   
   }
}
