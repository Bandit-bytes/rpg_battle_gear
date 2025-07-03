package net.bandit.battlegear.registry;

import dev.architectury.registry.menu.MenuRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.bandit.battlegear.BattleGearMod;
import net.bandit.battlegear.container.InfusionAltarMenu;
import net.bandit.battlegear.client.screen.InfusionAltarScreen;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;


public class MenuRegistries {
    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(BattleGearMod.MOD_ID, Registries.MENU);

    public static final RegistrySupplier<MenuType<InfusionAltarMenu>> INFUSION_ALTAR_MENU = MENUS.register(
            "infusion_altar_menu",
            () -> new MenuType<>(InfusionAltarMenu::new, FeatureFlags.VANILLA_SET));

    public static void init() {
        MENUS.register();
    }

    public static void registerScreens() {
        MenuRegistry.registerScreenFactory(
                INFUSION_ALTAR_MENU.get(),
                InfusionAltarScreen::new
        );
    }
}
