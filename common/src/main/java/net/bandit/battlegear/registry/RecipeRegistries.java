package net.bandit.battlegear.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.bandit.battlegear.BattleGearMod;
import net.bandit.battlegear.recipe.InfusionAltarRecipe;
import net.bandit.battlegear.recipe.InfusionAltarRecipeSerializer;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;

public class RecipeRegistries {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(BattleGearMod.MOD_ID, Registries.RECIPE_SERIALIZER);

    public static final DeferredRegister<RecipeType<?>> TYPES =
            DeferredRegister.create(BattleGearMod.MOD_ID, Registries.RECIPE_TYPE);

    public static final RegistrySupplier<RecipeSerializer<InfusionAltarRecipe>> INFUSION_SERIALIZER =
            SERIALIZERS.register("infusion", InfusionAltarRecipeSerializer::new);

    public static final RegistrySupplier<RecipeType<InfusionAltarRecipe>> INFUSION_TYPE =
            TYPES.register("infusion", () -> new RecipeType<>() {});

    public static final ResourceLocation INFUSION_ID =
            ResourceLocation.fromNamespaceAndPath(BattleGearMod.MOD_ID, "infusion");

    public static void init() {
        SERIALIZERS.register();
        TYPES.register();
    }
}