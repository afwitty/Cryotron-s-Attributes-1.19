package dev.cryotron.attributes.common.skilltree;

import java.awt.List;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.Random;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.google.common.collect.Lists;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import dev.cryotron.attributes.common.skilltree.source.ModifierSource;
import dev.cryotron.attributes.util.CacheEventBus;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.ModContainer;
import net.minecraftforge.fml.ModList;

public class AbstractSkill implements ModifierSource {
	
	  protected static final Random rand = new Random();
	  
//	  public static final SkillCategory CATEGORY_BASE = new SkillCategory("base", TextFormatting.WHITE);
//	  
//	  public static final SkillCategory CATEGORY_ROOT = new SkillCategory("root", TextFormatting.WHITE);
//	  
//	  public static final SkillCategory CATEGORY_MAJOR = new SkillCategory("major", TextFormatting.WHITE);
//	  
//	  public static final SkillCategory CATEGORY_KEY = new SkillCategory("key", TextFormatting.GOLD);
//	  
//	  public static final SkillCategory CATEGORY_EPIPHANY = new SkillCategory("epiphany", TextFormatting.GOLD);
//	  
//	  public static final SkillCategory CATEGORY_FOCUS = new SkillCategory("focus", TextFormatting.GOLD);
	  	  
	  private final ResourceLocation registryName;
	  
	  private final CacheEventBus busWrapper;
	  
	  protected final Point2D.Float offset;
	  
	  private String unlocalizedKey;
	  
	  //private SkillCategory category = CATEGORY_BASE;
	  
	  //private boolean hiddenUnlessAllocated = false;
	  
	  private SkillTreePoint<? extends AbstractSkill> treePoint = null;
	  
	  private ResourceLocation customSkillType = null;
	  
	  //private List<IFormattableTextComponent> tooltipCache = null;
	  
	  private boolean cacheTooltip = true;
	  
	  public AbstractSkill(ResourceLocation name, float x, float y) {
	    this.registryName = name;
	    this.busWrapper = CacheEventBus.of(MinecraftForge.EVENT_BUS);
	    this.offset = new Point2D.Float(x, y);
	    //this.unlocalizedKey = String.format("Skill.%s.%s", new Object[] { name.func_110624_b(), name.func_110623_a() });
	  }
	  
	  protected SkillTreePoint<? extends AbstractSkill> initSkillTreePoint() {
	    return new SkillTreePoint(this, getOffset());
	  }
	  
//	  protected void invalidate(LogicalSide side) {
//	    this.busWrapper.unregisterAll();
//	    SkillCooldownHelper.removeSkillCooldowns(side, this);
//	  }
	  
	  protected void validate(LogicalSide side) {
	    attachListeners(side, (IEventBus)this.busWrapper);
	  }
	  
	  protected void attachListeners(LogicalSide side, IEventBus bus) {}
	  
	  @Nonnull
	  public Point2D.Float getOffset() {
	    return this.offset;
	  }
	  
	  public final SkillTreePoint<? extends AbstractSkill> getPoint() {
	    if (this.treePoint == null)
	      this.treePoint = initSkillTreePoint(); 
	    return this.treePoint;
	  }
	  
	  public ResourceLocation getRegistryName() {
	    return this.registryName;
	  }
	  
//	  public <T> T setCategory(SkillCategory category) {
//	    this.category = category;
//	    return (T)this;
//	  }
	  
//	  public <T> T setHiddenUnlessAllocated(boolean hiddenUnlessAllocated) {
//	    this.hiddenUnlessAllocated = hiddenUnlessAllocated;
//	    return (T)this;
//	  }
	  
//	  public boolean canApplySource(Player player, LogicalSide dist) {
//	    return !ResearchHelper.getProgress(player, dist).getSkillData().isSkillSealed(this);
//	  }
	  
	  public final void onApply(Player player, LogicalSide dist) {
	    applySkillLogic(player, dist);
	  }
	  
	  public final void onRemove(Player player, LogicalSide dist) {
	    removeSkillLogic(player, dist);
	  }
	  
	  protected void applySkillLogic(Player player, LogicalSide dist) {}
	  
	  protected void removeSkillLogic(Player player, LogicalSide dist) {}
	  
//	  protected LogicalSide getSide(Entity entity) {
//	    return entity.func_130014_f_().func_201670_d() ? LogicalSide.CLIENT : LogicalSide.SERVER;
//	  }
	  
//	  @Nullable
//	  public CompoundNBT getSkillData(Player player, LogicalSide dist) {
//	    return ResearchHelper.getProgress(player, dist).getSkillData().getData(this);
//	  }
	  
//	  public void onUnlockSkillServer(@Nullable Player player, SkillAllocationType allocationType, PlayerProgress progress, CompoundNBT dataStorage) {}
//	  
//	  public void onRemoveSkillServer(Player player, SkillAllocationType allocationType, PlayerProgress progress, CompoundNBT dataStorage) {}
	  
	  public <T extends AbstractSkill> T setName(String name) {
	    this.unlocalizedKey = name;
	    return (T)this;
	  }
	  
//	  @Nonnull
//	  public SkillCategory getCategory() {
//	    return this.category;
//	  }
	  
//	  public AllocationStatus getSkillStatus(@Nullable Player player, LogicalSide side) {
//	    if (player == null)
//	      return AllocationStatus.UNALLOCATED; 
//	    PlayerProgress progress = ResearchHelper.getProgress(player, side);
//	    if (!progress.isValid())
//	      return AllocationStatus.UNALLOCATED; 
//	    PlayerSkillData SkillData = progress.getSkillData();
//	    if (SkillData.hasSkillAllocation(this, SkillAllocationType.UNLOCKED))
//	      return AllocationStatus.ALLOCATED; 
//	    if (SkillData.hasSkillAllocation(this))
//	      return AllocationStatus.GRANTED; 
//	    return mayUnlockSkill(progress, player) ? AllocationStatus.UNLOCKABLE : AllocationStatus.UNALLOCATED;
//	  }
	  
//	  public boolean mayUnlockSkill(PlayerProgress progress, Player player) {
//	    PlayerSkillData SkillData = progress.getSkillData();
//	    if (!SkillData.hasFreeAllocationPoint(player, getSide((Entity)player)))
//	      return false; 
//	    for (AbstractSkill otherSkills : SkillTree.Skill_TREE.getConnectedSkills(getSide((Entity)player), this)) {
//	      if (SkillData.hasSkillAllocation(otherSkills, SkillAllocationType.UNLOCKED))
//	        return true; 
//	    } 
//	    return false;
//	  }
	  
//	  @OnlyIn(Dist.CLIENT)
//	  public boolean isVisible(PlayerProgress progress, Player player) {
//	    return (!this.hiddenUnlessAllocated || progress.getSkillData().hasSkillAllocation(this));
//	  }
//	  
//	  public IFormattableTextComponent getName() {
//	    return (new TranslationTextComponent(this.unlocalizedKey + ".name"))
//	      .func_240699_a_(getCategory().getTextFormatting());
//	  }
//	  
//	  @Nonnull
//	  @OnlyIn(Dist.CLIENT)
//	  public Collection<IFormattableTextComponent> getDescription() {
//	    List<IFormattableTextComponent> toolTip = new ArrayList<>();
//	    if (I18n.func_188566_a(this.unlocalizedKey + ".desc.1")) {
//	      int count = 1;
//	      while (I18n.func_188566_a(this.unlocalizedKey + ".desc." + count)) {
//	        toolTip.add(new TranslationTextComponent(this.unlocalizedKey + ".desc." + count));
//	        count++;
//	      } 
//	      toolTip.add(new StringTextComponent(""));
//	    } else if (I18n.func_188566_a(this.unlocalizedKey + ".desc")) {
//	      toolTip.add(new TranslationTextComponent(this.unlocalizedKey + ".desc"));
//	      toolTip.add(new StringTextComponent(""));
//	    } 
//	    return toolTip;
//	  }
//	  
//	  protected void disableTooltipCaching() {
//	    this.cacheTooltip = false;
//	    this.tooltipCache = null;
//	  }
//	  
//	  @OnlyIn(Dist.CLIENT)
//	  public final Collection<IFormattableTextComponent> getLocalizedTooltip() {
//	    if (this.cacheTooltip && this.tooltipCache != null)
//	      return this.tooltipCache; 
//	    this.tooltipCache = Lists.newArrayList();
//	    if (!(this instanceof ProgressGatedSkill) || ((ProgressGatedSkill)this).canSeeClient()) {
//	      this.tooltipCache.add(getName());
//	      int prevLength = this.tooltipCache.size();
//	      boolean shouldAdd = addLocalizedTooltip(this.tooltipCache);
//	      if (shouldAdd && prevLength != this.tooltipCache.size())
//	        this.tooltipCache.add(new StringTextComponent("")); 
//	      this.tooltipCache.addAll(getDescription());
//	    } else {
//	      this.tooltipCache.add((new TranslationTextComponent("Skill.info.astralsorcery.missing_progress"))
//	          .func_240699_a_(TextFormatting.RED));
//	    } 
//	    return this.tooltipCache;
//	  }
//	  
//	  @OnlyIn(Dist.CLIENT)
//	  public boolean addLocalizedTooltip(Collection<IFormattableTextComponent> tooltip) {
//	    return false;
//	  }
//	  
//	  @Nullable
//	  @OnlyIn(Dist.CLIENT)
//	  public Collection<IFormattableTextComponent> getSource() {
//	    String modid = getRegistryName().func_110624_b();
//	    ModContainer mod = ModList.get().getModContainerById(modid).orElse(null);
//	    if (mod != null)
//	      return Lists.newArrayList((Object[])new IFormattableTextComponent[] { (IFormattableTextComponent)new StringTextComponent(mod.getModInfo().getDisplayName()) }); 
//	    return null;
//	  }
	  
	  public void clearCaches(LogicalSide side) {}
	  
//	  @OnlyIn(Dist.CLIENT)
//	  public void clearClientTextCaches() {
//	    this.tooltipCache = null;
//	  }
//	  
//	  public ResourceLocation getProviderName() {
//	    return ModifierManager.Skill_PROVIDER_KEY;
//	  }
	  
	  public boolean isEqual(ModifierSource other) {
	    return equals(other);
	  }
	  
	  public boolean equals(Object o) {
	    if (this == o)
	      return true; 
	    if (!(o instanceof AbstractSkill))
	      return false; 
	    AbstractSkill that = (AbstractSkill)o;
	    return Objects.equals(getRegistryName(), that.getRegistryName());
	  }
	  
	  public int hashCode() {
	    return Objects.hash(new Object[] { getRegistryName() });
	  }
	  
//	  @OnlyIn(Dist.CLIENT)
//	  public boolean handleMouseClick(ScreenJournalSkillTree gui, double mouseX, double mouseY) {
//	    return false;
//	  }
	  
	  public void deserializeData(JsonObject SkillData) {}
	  
	  public void serializeData(JsonObject SkillData) {}
	  
	  @Nullable
	  public final ResourceLocation getCustomSkillType() {
	    return this.customSkillType;
	  }
	  
	  public final void setCustomSkillType(ResourceLocation customSkillType) {
	    this.customSkillType = customSkillType;
	  }
	  
	  public final JsonObject serializeSkill() {
	    JsonObject data = new JsonObject();
	    data.addProperty("registry_name", getRegistryName().toString());
	    if (getCustomSkillType() != null)
	    data.addProperty("Skill_class", getCustomSkillType().toString()); 
	    data.addProperty("x", Float.valueOf((getOffset()).x));
	    data.addProperty("y", Float.valueOf((getOffset()).y));
	    data.addProperty("name", this.unlocalizedKey);
	    //data.addProperty("hiddenUnlessAllocated", Boolean.valueOf(this.hiddenUnlessAllocated));
	    JsonObject SkillData = new JsonObject();
	    serializeData(SkillData);
	    //data.add("data", (JsonElement)SkillData);
	    return data;
	  }
	  
	  public static class SkillCategory {
//	    private final IFormattableTextComponent name;
//	    
//	    private final TextFormatting color;
//	    
//	    public SkillCategory(@Nonnull String unlocName, @Nonnull TextFormatting color) {
//	      this.name = (IFormattableTextComponent)new TranslationTextComponent("Skill.category.astralsorcery." + unlocName + ".name");
//	      this.color = color;
//	    }
//	    
//	    public TextFormatting getTextFormatting() {
//	      return this.color;
//	    }
//	    
//	    public IFormattableTextComponent getName() {
//	      return this.name;
//	    }
//	    
//	    public boolean equals(Object o) {
//	      if (this == o)
//	        return true; 
//	      if (o == null || getClass() != o.getClass())
//	        return false; 
//	      SkillCategory that = (SkillCategory)o;
//	      return Objects.equals(this.name, that.name);
//	    }
//	    
//	    public int hashCode() {
//	      return Objects.hash(new Object[] { this.name });
//	    }
	  }

	@Override
	public boolean canApplySource(Player player, LogicalSide dist) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ResourceLocation getProviderName() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
