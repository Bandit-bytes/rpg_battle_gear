package net.bandit.battlegear.recipe;

import net.bandit.battlegear.registry.RecipeRegistries;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

public class InfusionAltarRecipe implements Recipe<InfusionInput> {

    public enum Match { NONE, NORMAL, SWAPPED }

    private final Ingredient ingredient1;
    private final Ingredient ingredient2;
    private final int count1;
    private final int count2;
    private final ItemStack result;
    private final ResourceLocation id;

    public InfusionAltarRecipe(ResourceLocation id, Ingredient ingredient1, int count1, Ingredient ingredient2, int count2, ItemStack result) {
        this.id = id;
        this.ingredient1 = ingredient1;
        this.ingredient2 = ingredient2;
        this.count1 = Math.max(1, count1);
        this.count2 = Math.max(1, count2);
        this.result = result;
    }

    public ResourceLocation getId() {
        return id;
    }

    public Ingredient ingredient1() { return ingredient1; }
    public Ingredient ingredient2() { return ingredient2; }
    public int count1() { return count1; }
    public int count2() { return count2; }
    public ItemStack resultStack() { return result.copy(); }


    public Match match(InfusionInput input) {
        ItemStack a = input.left();
        ItemStack b = input.right();

        if (ingredient1.test(a) && a.getCount() >= count1 && ingredient2.test(b) && b.getCount() >= count2) {
            return Match.NORMAL;
        }
        if (ingredient2.test(a) && a.getCount() >= count2 && ingredient1.test(b) && b.getCount() >= count1) {
            return Match.SWAPPED;
        }
        return Match.NONE;
    }

    @Override
    public boolean matches(InfusionInput input, Level level) {
        return match(input) != Match.NONE;
    }

    @Override
    public ItemStack assemble(InfusionInput input, HolderLookup.Provider registries) {
        return result.copy();
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider registries) {
        return result.copy();
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return RecipeRegistries.INFUSION_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return RecipeRegistries.INFUSION_TYPE.get();
    }
}