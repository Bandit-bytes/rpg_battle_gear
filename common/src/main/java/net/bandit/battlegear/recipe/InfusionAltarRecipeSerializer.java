package net.bandit.battlegear.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;

public class InfusionAltarRecipeSerializer implements RecipeSerializer<InfusionAltarRecipe> {

    public static final MapCodec<InfusionAltarRecipe> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            ResourceLocation.CODEC.fieldOf("id").forGetter(InfusionAltarRecipe::getId),
            Ingredient.CODEC.fieldOf("ingredient1").forGetter(InfusionAltarRecipe::ingredient1),
            com.mojang.serialization.Codec.INT.optionalFieldOf("count1", 1).forGetter(InfusionAltarRecipe::count1),
            Ingredient.CODEC.fieldOf("ingredient2").forGetter(InfusionAltarRecipe::ingredient2),
            com.mojang.serialization.Codec.INT.optionalFieldOf("count2", 1).forGetter(InfusionAltarRecipe::count2),
            ItemStack.CODEC.fieldOf("result").forGetter(r -> r.resultStack())
    ).apply(instance, (id, ing1, c1, ing2, c2, result) -> new InfusionAltarRecipe(id, ing1, c1, ing2, c2, result)));

    public static final StreamCodec<RegistryFriendlyByteBuf, InfusionAltarRecipe> STREAM_CODEC = StreamCodec.of(
            (buf, recipe) -> {
                buf.writeResourceLocation(recipe.getId());
                Ingredient.CONTENTS_STREAM_CODEC.encode(buf, recipe.ingredient1());
                buf.writeVarInt(recipe.count1());
                Ingredient.CONTENTS_STREAM_CODEC.encode(buf, recipe.ingredient2());
                buf.writeVarInt(recipe.count2());
                ItemStack.STREAM_CODEC.encode(buf, recipe.resultStack());
            },
            buf -> {
                ResourceLocation id = buf.readResourceLocation();
                Ingredient ing1 = Ingredient.CONTENTS_STREAM_CODEC.decode(buf);
                int c1 = buf.readVarInt();
                Ingredient ing2 = Ingredient.CONTENTS_STREAM_CODEC.decode(buf);
                int c2 = buf.readVarInt();
                ItemStack result = ItemStack.STREAM_CODEC.decode(buf);
                return new InfusionAltarRecipe(id, ing1, c1, ing2, c2, result);
            }
    );

    @Override
    public MapCodec<InfusionAltarRecipe> codec() {
        return CODEC;
    }

    @Override
    public StreamCodec<RegistryFriendlyByteBuf, InfusionAltarRecipe> streamCodec() {
        return STREAM_CODEC;
    }
}