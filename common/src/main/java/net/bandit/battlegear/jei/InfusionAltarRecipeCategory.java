package net.bandit.battlegear.jei;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import net.bandit.battlegear.BattleGearMod;
import net.bandit.battlegear.recipe.InfusionAltarRecipe;
import net.bandit.battlegear.registry.BlockRegistry;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class InfusionAltarRecipeCategory implements IRecipeCategory<InfusionAltarRecipe> {

    public static final RecipeType<InfusionAltarRecipe> RECIPE_TYPE =
            new RecipeType<>(ResourceLocation.fromNamespaceAndPath(BattleGearMod.MOD_ID, "infusion"), InfusionAltarRecipe.class);

    private final IDrawable background;
    private final Component title;
    private final IDrawable icon;

    public InfusionAltarRecipeCategory(IGuiHelper guiHelper) {
        this.background = guiHelper.createDrawable(
                ResourceLocation.fromNamespaceAndPath("minecraft", "textures/gui/container/anvil.png"),
                25, 39, 140, 35
        );
        this.title = Component.translatable("jei.category.infusion_altar");
        this.icon = guiHelper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(BlockRegistry.INFUSION_ALTAR.get()));
    }

    @Override
    public RecipeType<InfusionAltarRecipe> getRecipeType() {
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

    private static List<ItemStack> stacksWithCount(net.minecraft.world.item.crafting.Ingredient ing, int count) {
        ItemStack[] items = ing.getItems();
        List<ItemStack> out = new ArrayList<>(items.length);
        for (ItemStack is : items) {
            ItemStack copy = is.copy();
            copy.setCount(Math.max(1, count));
            out.add(copy);
        }
        return out;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, InfusionAltarRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 2, 8)
                .addItemStacks(stacksWithCount(recipe.ingredient1(), recipe.count1()))
                .setSlotName("input1");

        builder.addSlot(RecipeIngredientRole.INPUT, 51, 8)
                .addItemStacks(stacksWithCount(recipe.ingredient2(), recipe.count2()))
                .setSlotName("input2");

        builder.addSlot(RecipeIngredientRole.OUTPUT, 108, 8)
                .addItemStack(recipe.resultStack());
    }
}