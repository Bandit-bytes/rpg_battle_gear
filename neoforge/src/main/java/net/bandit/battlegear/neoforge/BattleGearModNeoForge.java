package net.bandit.battlegear.neoforge;

import net.bandit.battlegear.BattleGearMod;
import net.neoforged.fml.common.Mod;

@Mod(BattleGearMod.MOD_ID)
public final class BattleGearModNeoForge {

    public BattleGearModNeoForge() {
        BattleGearMod.init();
    }

    private void initClient() {
        BattleGearMod.initClient();
    }
}
