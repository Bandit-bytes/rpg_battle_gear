package net.bandit.battlegear.registry;

import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.bandit.battlegear.BattleGearMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class TabRegistry {
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(BattleGearMod.MOD_ID, Registries.CREATIVE_MODE_TAB);
    public static final RegistrySupplier<CreativeModeTab> RPG_BATTLEGEAR_TAB = TABS.register(
            "rpg_battlegear_tab",
            () -> CreativeTabRegistry.create(
                    Component.translatable("category.rpg_battlegear"),
                    () -> new ItemStack(BattleGearItems.GUARDIAN_HELMET.get())
            )
    );

    public static void init() {
        TABS.register();
    }
}
