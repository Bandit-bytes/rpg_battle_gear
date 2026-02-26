package net.bandit.battlegear.recipe;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;

public record InfusionInput(ItemStack left, ItemStack right) implements RecipeInput {
    @Override
    public int size() {
        return 2;
    }

    @Override
    public ItemStack getItem(int index) {
        return index == 0 ? left : right;
    }
}