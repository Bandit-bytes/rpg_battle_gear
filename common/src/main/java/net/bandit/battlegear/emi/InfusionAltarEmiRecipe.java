package net.bandit.battlegear.emi;

import dev.emi.emi.api.recipe.BasicEmiRecipe;
import dev.emi.emi.api.render.EmiTexture;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.WidgetHolder;
import net.bandit.battlegear.BattleGearMod;
import net.bandit.battlegear.container.SimpleInfusionRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class InfusionAltarEmiRecipe extends BasicEmiRecipe {

    private static final EmiTexture BACKGROUND =
            new EmiTexture(InfusionAltarEmiCategory.ANVIL_TEX, 25, 39, 140, 35);

    public InfusionAltarEmiRecipe(SimpleInfusionRecipe recipe, int syntheticIndex) {
        super(
                InfusionAltarEmiCategory.CATEGORY,
                ResourceLocation.fromNamespaceAndPath(BattleGearMod.MOD_ID, "infusion_altar/" + syntheticIndex),
                140,
                35
        );


        this.inputs.add(EmiIngredient.of(List.of(EmiStack.of(recipe.getInput1()))));
        this.inputs.add(EmiIngredient.of(List.of(EmiStack.of(recipe.getInput2()))));

        this.outputs.add(EmiStack.of(recipe.getResult()));
    }

    @Override
    public void addWidgets(WidgetHolder widgets) {
        widgets.addTexture(BACKGROUND, 0, 0);

        widgets.addSlot(inputs.get(0), 2, 8);
        widgets.addSlot(inputs.get(1), 51, 8);

        widgets.addSlot(outputs.get(0), 108, 8).recipeContext(this);
    }
}