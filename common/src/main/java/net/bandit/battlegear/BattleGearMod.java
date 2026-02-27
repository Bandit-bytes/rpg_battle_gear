package net.bandit.battlegear;


import net.bandit.battlegear.config.BattleGearConfig;
import net.bandit.battlegear.event.DreadlordSetEvents;
import net.bandit.battlegear.event.SovereignSetEvents;
import net.bandit.battlegear.registry.*;

public final class BattleGearMod {
    public static final String MOD_ID = "rpg_battle_gear";

    public static void init() {
        ItemRegistry.init();
        TabRegistry.init();
        MenuRegistries.init();
        BlockRegistry.init();
        RecipeRegistries.init();
        BattleGearConfig.loadConfig();

        SovereignSetEvents.init();
        DreadlordSetEvents.init();
    }
    public static void initClient() {
        MenuRegistries.registerScreens();
    }

}
