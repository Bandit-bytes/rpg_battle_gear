package net.bandit.battlegear.neoforge;

import net.bandit.battlegear.BattleGearMod;
import net.bandit.battlegear.registry.MenuRegistry;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLEnvironment;

@Mod(BattleGearMod.MOD_ID)
public final class BattleGearModNeoForge {

    public BattleGearModNeoForge() {
        // Run our common setup.
        BattleGearMod.init();
    }

    private void initClient() {
        BattleGearMod.initClient();
    }
}
