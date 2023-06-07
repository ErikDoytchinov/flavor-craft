package net.eDoytchinov.flavorcraft.block;

import net.eDoytchinov.flavorcraft.FlavorCraft;
import net.eDoytchinov.flavorcraft.block.entity.MillingStationEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockEntity {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, FlavorCraft.MOD_ID);

    public static final RegistryObject<BlockEntityType<MillingStationEntity>> MILLINGSTATION = BLOCK_ENTITIES.register("milling_station",
                () -> BlockEntityType.Builder.of(MillingStationEntity::new, ModBlocks.MILLINGSTATION.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
