package net.eDoytchinov.flavorcraft;

import com.mojang.logging.LogUtils;
import net.eDoytchinov.flavorcraft.block.ModBlockEntity;
import net.eDoytchinov.flavorcraft.block.ModBlocks;
import net.eDoytchinov.flavorcraft.item.ModCreativeModTab;
import net.eDoytchinov.flavorcraft.item.ModItems;
import net.eDoytchinov.flavorcraft.screen.ModMenuTypes;
import net.minecraft.client.Minecraft;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(FlavorCraft.MOD_ID)
public class FlavorCraft {
    public static final String MOD_ID = "flavorcraft";
    private static final Logger LOGGER = LogUtils.getLogger();

    public FlavorCraft() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModBlockEntity.register(modEventBus);
        ModMenuTypes.register(modEventBus);


        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        // Register the addCreative method for modloading
        modEventBus.addListener(this::addCreative);

    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        // Some common setup code
        LOGGER.info("HELLO FROM COMMON SETUP");
        LOGGER.info("DIRT BLOCK >> {}", ForgeRegistries.BLOCKS.getKey(Blocks.DIRT));
    }

    private void addCreative(CreativeModeTabEvent.BuildContents event) {
        if (event.getTab() == ModCreativeModTab.Foods) {
            event.accept(ModItems.RICE);
            event.accept(ModItems.TOMATO);
            event.accept(ModItems.LETTUCE);
            event.accept(ModItems.STRAWBERRY);
            event.accept(ModItems.SALAD);
            event.accept(ModItems.SUSHI);
            event.accept(ModItems.DOUGH);
            event.accept(ModItems.PIZZA);
            event.accept(ModItems.CHOCOLATE);
            event.accept(ModItems.SALAMI);
            event.accept(ModItems.CHEESE);
            event.accept(ModItems.PINEAPPLE);
            event.accept(ModItems.TACO_SHELL);
            event.accept(ModItems.BEEF_TACO);
        }
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            // Some client setup code
            LOGGER.info("HELLO FROM CLIENT SETUP");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
        }
    }
}
