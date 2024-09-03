package net.bandit.battlegear.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.bandit.battlegear.BattleGearMod;
import net.bandit.battlegear.container.InfusionAltarScreenHandler;
import net.minecraft.client.gui.screens.inventory.AnvilScreen;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;


public class MenuRegistry {
    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(BattleGearMod.MOD_ID, Registries.MENU);

    public static final RegistrySupplier<MenuType<InfusionAltarScreenHandler>> INFUSION_ALTAR_MENU = MENUS.register(
            "infusion_altar_menu",
            () -> new MenuType<>(InfusionAltarScreenHandler::new, FeatureFlags.VANILLA_SET));

    public static void init() {
        MENUS.register();
    }

    public static void registerScreens() {
        dev.architectury.registry.menu.MenuRegistry.registerScreenFactory(
                INFUSION_ALTAR_MENU.get(),
                AnvilScreen::new
        );
    }
}
