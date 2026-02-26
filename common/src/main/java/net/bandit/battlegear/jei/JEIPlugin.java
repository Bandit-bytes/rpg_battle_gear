package net.bandit.battlegear.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.bandit.battlegear.BattleGearMod;
import net.bandit.battlegear.recipe.InfusionAltarRecipe;
import net.bandit.battlegear.registry.RecipeRegistries;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;

import java.util.List;

@JeiPlugin
public class JEIPlugin implements IModPlugin {

    private static final ResourceLocation UID =
            ResourceLocation.fromNamespaceAndPath(BattleGearMod.MOD_ID, "jei_plugin");

    @Override
    public ResourceLocation getPluginUid() {
        return UID;
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registry) {
        IGuiHelper guiHelper = registry.getJeiHelpers().getGuiHelper();
        registry.addRecipeCategories(new InfusionAltarRecipeCategory(guiHelper));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registry) {
        if (Minecraft.getInstance().level == null) return;

        var holders = Minecraft.getInstance().level.getRecipeManager()
                .getAllRecipesFor(RecipeRegistries.INFUSION_TYPE.get());

        List<InfusionAltarRecipe> recipes = holders.stream()
                .map(h -> h.value())
                .toList();

        registry.addRecipes(InfusionAltarRecipeCategory.RECIPE_TYPE, recipes);
    }
}