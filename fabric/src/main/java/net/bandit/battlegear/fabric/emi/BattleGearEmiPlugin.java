package net.bandit.battlegear.fabric.emi;

import dev.emi.emi.api.EmiEntrypoint;
import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import net.bandit.battlegear.recipe.InfusionAltarRecipe;
import net.bandit.battlegear.registry.RecipeRegistries;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;

@EmiEntrypoint
public class BattleGearEmiPlugin implements EmiPlugin {

    @Override
    public void register(EmiRegistry registry) {
        registry.addCategory(InfusionAltarEmiCategory.CATEGORY);
        registry.addWorkstation(InfusionAltarEmiCategory.CATEGORY, InfusionAltarEmiCategory.WORKSTATION);

        if (Minecraft.getInstance().level == null) return;

        var recipes = Minecraft.getInstance().level.getRecipeManager()
                .getAllRecipesFor(RecipeRegistries.INFUSION_TYPE.get());

        for (var holder : recipes) {
            InfusionAltarRecipe recipe = holder.value();
            ResourceLocation id = recipe.getId();

            registry.addRecipe(new InfusionAltarEmiRecipe(id, recipe));
        }
    }
}