package net.bandit.battlegear.jei;

import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.IModPlugin;
import net.minecraft.resources.ResourceLocation;
import net.bandit.battlegear.BattleGearMod;
import net.bandit.battlegear.container.InfusionAltarScreenHandler;


@JeiPlugin
public class JEIPlugin implements IModPlugin {

    private static final ResourceLocation UID = ResourceLocation.fromNamespaceAndPath(BattleGearMod.MOD_ID, "jei_plugin");

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
        registry.addRecipes(InfusionAltarRecipeCategory.RECIPE_TYPE, InfusionAltarScreenHandler.getInfusionRecipes());
    }
}
