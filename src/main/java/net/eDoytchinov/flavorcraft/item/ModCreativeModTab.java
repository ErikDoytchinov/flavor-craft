package net.eDoytchinov.flavorcraft.item;

import net.eDoytchinov.flavorcraft.FlavorCraft;
import net.eDoytchinov.flavorcraft.block.ModBlocks;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = FlavorCraft.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModCreativeModTab {
    public static CreativeModeTab Foods;
    public static CreativeModeTab FoodStation;

    @SubscribeEvent
    public static void registerCreativeModTabs(CreativeModeTabEvent.Register event){
        Foods = event.registerCreativeModeTab(new ResourceLocation(FlavorCraft.MOD_ID, "foods"),
                builder -> builder.icon(() -> new ItemStack(ModItems.RICE.get()))
                        .title(Component.translatable("creativemodetab.foods")));

        FoodStation = event.registerCreativeModeTab(new ResourceLocation(FlavorCraft.MOD_ID, "food_station"),
                builder -> builder.icon(() -> new ItemStack(ModBlocks.MILLINGSTATION.get()))
                        .title(Component.translatable("creativemodetab.food_station")));
    }

}
