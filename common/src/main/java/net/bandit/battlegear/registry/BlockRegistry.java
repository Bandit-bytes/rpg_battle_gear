package net.bandit.battlegear.registry;

import net.bandit.battlegear.BattleGearMod;
import net.bandit.battlegear.block.InfusionAltarBlock;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;

public class BlockRegistry {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(BattleGearMod.MOD_ID, Registries.BLOCK);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(BattleGearMod.MOD_ID, Registries.ITEM);

    public static final RegistrySupplier<Block> INFUSION_ALTAR = BLOCKS.register("infusion_altar",
            () -> new InfusionAltarBlock(BlockBehaviour.Properties.of().strength(4.0f).noOcclusion()));

    public static final RegistrySupplier<Item> INFUSION_ALTAR_ITEM = ITEMS.register("infusion_altar",
            () -> new BlockItem(INFUSION_ALTAR.get(), new Item.Properties().arch$tab(TabRegistry.RPG_BATTLEGEAR_TAB).stacksTo(2).rarity(Rarity.EPIC)));

    public static void init() {
        BLOCKS.register();
        ITEMS.register();
    }
}
