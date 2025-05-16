package net.bandit.battlegear;


import net.bandit.battlegear.config.BattleGearConfig;
import net.bandit.battlegear.registry.*;

public final class BattleGearMod {
    public static final String MOD_ID = "rpg_battle_gear";

    public static void init() {
        BattleGearItems.init();
        TabRegistry.init();
        MenuRegistry.init();
        BlockRegistry.init();
        BattleGearConfig.loadConfig();
    }
    public static void initClient() {
        MenuRegistry.registerScreens();
    }

}
