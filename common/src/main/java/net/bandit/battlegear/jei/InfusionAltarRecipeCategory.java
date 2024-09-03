package net.bandit.battlegear.jei;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import net.bandit.battlegear.container.SimpleInfusionRecipe;
import net.bandit.battlegear.registry.BlockRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;

import net.bandit.battlegear.BattleGearMod;

import java.util.List;

public class InfusionAltarRecipeCategory implements IRecipeCategory<SimpleInfusionRecipe> {

    public static final RecipeType<SimpleInfusionRecipe> RECIPE_TYPE = new RecipeType<>(ResourceLocation.fromNamespaceAndPath(BattleGearMod.MOD_ID, "infusion_altar"), SimpleInfusionRecipe.class);


    private final IDrawable background;
    private final Component title;
    private final IDrawable icon;

    public InfusionAltarRecipeCategory(IGuiHelper guiHelper) {
        this.background = guiHelper.createDrawable(ResourceLocation.fromNamespaceAndPath("minecraft", "textures/gui/container/anvil.png"), 25, 39, 140, 35);
        this.title = Component.translatable("jei.category.infusion_altar");
        this.icon = guiHelper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(BlockRegistry.INFUSION_ALTAR.get()));
    }

    @Override
    public RecipeType<SimpleInfusionRecipe> getRecipeType() {
        return RECIPE_TYPE;
    }

    @Override
    public Component getTitle() {
        return title;
    }

    @Override
    public IDrawable getBackground() {
        return background;
    }

    @Override
    public IDrawable getIcon() {
        return icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, SimpleInfusionRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 2, 8).addItemStack(recipe.getInput1());
        builder.addSlot(RecipeIngredientRole.INPUT, 51, 8).addItemStack(recipe.getInput2());
        builder.addSlot(RecipeIngredientRole.OUTPUT, 108, 8).addItemStack(recipe.getResult());
    }
//    @Override
    public List<ItemStack> getRecipeCatalysts() {
        return List.of(new ItemStack(BlockRegistry.INFUSION_ALTAR.get()));
    }
}
