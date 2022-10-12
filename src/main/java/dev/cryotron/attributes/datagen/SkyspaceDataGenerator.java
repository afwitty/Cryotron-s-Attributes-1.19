package dev.cryotron.attributes.datagen;

import dev.cryotron.attributes.CTAttributes;
import dev.cryotron.attributes.datagen.data.skilltree.SkillTreeProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = CTAttributes.ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SkyspaceDataGenerator {
    @SubscribeEvent
    public static void gather(GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();
        ExistingFileHelper fileHelper = event.getExistingFileHelper();
        
        gen.addProvider(true, new SkillTreeProvider(gen));
        
        CTAttributes.LOGGER.info("Skyspace Datagen Loaded!");
    }
}
