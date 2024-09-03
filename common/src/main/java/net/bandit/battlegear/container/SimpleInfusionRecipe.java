package net.bandit.battlegear.container;

import net.minecraft.world.item.ItemStack;

public class SimpleInfusionRecipe {
    private final ItemStack input1;
    private final ItemStack input2;
    private final ItemStack result;

    public SimpleInfusionRecipe(ItemStack input1, ItemStack input2, ItemStack result) {
        this.input1 = input1;
        this.input2 = input2;
        this.result = result;
    }

    public ItemStack getInput1() {
        return input1;
    }

    public ItemStack getInput2() {
        return input2;
    }

    public ItemStack getResult() {
        return result;
    }
}

