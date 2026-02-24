package net.bandit.battlegear.emi;

import dev.emi.emi.api.EmiEntrypoint;
import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import net.bandit.battlegear.container.InfusionAltarScreenHandler;
import net.bandit.battlegear.container.SimpleInfusionRecipe;

@EmiEntrypoint
public class BattleGearEmiPlugin implements EmiPlugin {

    @Override
    public void register(EmiRegistry registry) {
        // 1) Category tab
        registry.addCategory(InfusionAltarEmiCategory.CATEGORY);

        // 2) Workstation / catalyst (the altar block)
        registry.addWorkstation(InfusionAltarEmiCategory.CATEGORY, InfusionAltarEmiCategory.WORKSTATION);

        // 3) Recipes
        int i = 0;
        for (SimpleInfusionRecipe recipe : InfusionAltarScreenHandler.getInfusionRecipes()) {
            registry.addRecipe(new InfusionAltarEmiRecipe(recipe, i++));
        }
    }
}