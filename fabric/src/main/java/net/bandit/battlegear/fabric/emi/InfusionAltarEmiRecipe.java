package net.bandit.battlegear.fabric.emi;

import dev.emi.emi.api.recipe.BasicEmiRecipe;
import dev.emi.emi.api.render.EmiTexture;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.WidgetHolder;
import net.bandit.battlegear.recipe.InfusionAltarRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.ArrayList;
import java.util.List;

public class InfusionAltarEmiRecipe extends BasicEmiRecipe {

    private static final EmiTexture BACKGROUND =
            new EmiTexture(InfusionAltarEmiCategory.ANVIL_TEX, 25, 39, 140, 35);

    public InfusionAltarEmiRecipe(ResourceLocation id, InfusionAltarRecipe recipe) {
        super(InfusionAltarEmiCategory.CATEGORY, id, 140, 35);

        this.inputs.add(toEmiIngredient(recipe.ingredient1(), recipe.count1()));
        this.inputs.add(toEmiIngredient(recipe.ingredient2(), recipe.count2()));
        this.outputs.add(EmiStack.of(recipe.resultStack()));
    }

    private static EmiIngredient toEmiIngredient(Ingredient ing, int count) {
        ItemStack[] items = ing.getItems();
        List<EmiStack> stacks = new ArrayList<>(Math.max(1, items.length));

        if (items.length == 0) {
            return EmiIngredient.of(List.of());
        }

        for (ItemStack is : items) {
            ItemStack copy = is.copy();
            copy.setCount(count);
            stacks.add(EmiStack.of(copy));
        }
        return EmiIngredient.of(stacks);
    }

    @Override
    public void addWidgets(WidgetHolder widgets) {
        widgets.addTexture(BACKGROUND, 0, 0);
        widgets.addSlot(inputs.get(0), 2, 8);
        widgets.addSlot(inputs.get(1), 51, 8);
        widgets.addSlot(outputs.get(0), 108, 8).recipeContext(this);
    }
}