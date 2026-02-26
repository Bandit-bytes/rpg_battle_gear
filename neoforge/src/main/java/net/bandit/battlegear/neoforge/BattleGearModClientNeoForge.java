package net.bandit.battlegear.neoforge;

import net.bandit.battlegear.BattleGearMod;
import net.bandit.battlegear.client.screen.InfusionAltarScreen;
import net.bandit.battlegear.registry.MenuRegistries;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;

@EventBusSubscriber(modid = BattleGearMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public final class BattleGearModClientNeoForge {

    @SubscribeEvent
    public static void onClientSetup(final FMLClientSetupEvent event) {
        event.enqueueWork(BattleGearMod::initClient);
    }

    @SubscribeEvent
    public static void onRegisterScreens(final RegisterMenuScreensEvent event) {
        event.register(MenuRegistries.INFUSION_ALTAR_MENU.get(), InfusionAltarScreen::new);
    }
}