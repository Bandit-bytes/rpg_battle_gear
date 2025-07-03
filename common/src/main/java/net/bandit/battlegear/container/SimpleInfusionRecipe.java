package net.bandit.battlegear.container;

import net.minecraft.world.item.ItemStack;

public class SimpleInfusionRecipe {
    private final ItemStack input1;
    private final ItemStack input2;
    private final ItemStack result;
    private final int requiredCount1;
    private final int requiredCount2;

    public SimpleInfusionRecipe(ItemStack input1, ItemStack input2, ItemStack result, int requiredCount1, int requiredCount2) {
        this.input1 = input1.copy();
        this.input2 = input2.copy();
        this.result = result.copy();
        this.requiredCount1 = requiredCount1;
        this.requiredCount2 = requiredCount2;
    }

    public boolean matches(ItemStack stack1, ItemStack stack2) {
        return ItemStack.isSameItemSameComponents(stack1, input1) && stack1.getCount() >= requiredCount1
                && ItemStack.isSameItemSameComponents(stack2, input2) && stack2.getCount() >= requiredCount2;
    }

    public ItemStack craft(ItemStack stack1, ItemStack stack2) {
        if (!matches(stack1, stack2)) return ItemStack.EMPTY;

        // Calculate how many full sets of input are present
        int possibleCrafts = Math.min(stack1.getCount() / requiredCount1, stack2.getCount() / requiredCount2);

        ItemStack resultStack = result.copy();
        resultStack.setCount(result.getCount() * possibleCrafts);
        return resultStack;
    }

    public ItemStack getInput1() {
        return input1.copy();
    }

    public ItemStack getInput2() {
        return input2.copy();
    }

    public ItemStack getResult() {
        return result.copy();
    }

    public int getRequiredCount1() {
        return requiredCount1;
    }

    public int getRequiredCount2() {
        return requiredCount2;
    }
}
