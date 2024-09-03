package net.bandit.battlegear.fabric.client;

import net.bandit.battlegear.BattleGearMod;
import net.fabricmc.api.ClientModInitializer;

public final class BattleGearModFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BattleGearMod.initClient();
        // This entrypoint is suitable for setting up client-specific logic, such as rendering.
    }
}
