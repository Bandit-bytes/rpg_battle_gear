package net.bandit.battlegear.item;

import net.bandit.battlegear.BattleGearMod;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;

public final class BattleGearArmorMaterials {

    public static final Holder<ArmorMaterial> CRUSADER;
    public static final Holder<ArmorMaterial> MYSTIC;
    public static final Holder<ArmorMaterial> TEMPLAR;
    public static final Holder<ArmorMaterial> GUARDIAN;

    private BattleGearArmorMaterials() {
    }

    static {
        CRUSADER = register("crusader", Util.make(new EnumMap<>(ArmorItem.Type.class), (map) -> {
            map.put(ArmorItem.Type.BOOTS, 3);
            map.put(ArmorItem.Type.LEGGINGS, 6);
            map.put(ArmorItem.Type.CHESTPLATE, 8);
            map.put(ArmorItem.Type.HELMET, 3);
        }), 10, 2.0F, 0.0F, Items.IRON_INGOT);

        MYSTIC = register("mystic", Util.make(new EnumMap<>(ArmorItem.Type.class), (map) -> {
            map.put(ArmorItem.Type.BOOTS, 4);
            map.put(ArmorItem.Type.LEGGINGS, 8);
            map.put(ArmorItem.Type.CHESTPLATE, 11);
            map.put(ArmorItem.Type.HELMET, 5);
        }), 15, 2.5F, 0.1F, Items.DIAMOND);

        TEMPLAR = register("templar", Util.make(new EnumMap<>(ArmorItem.Type.class), (map) -> {
            map.put(ArmorItem.Type.BOOTS, 5);
            map.put(ArmorItem.Type.LEGGINGS, 9);
            map.put(ArmorItem.Type.CHESTPLATE, 12);
            map.put(ArmorItem.Type.HELMET, 6);
        }), 20, 3.0F, 0.1F, Items.NETHERITE_INGOT);

        GUARDIAN = register("guardian", Util.make(new EnumMap<>(ArmorItem.Type.class), (map) -> {
            map.put(ArmorItem.Type.BOOTS, 3);
            map.put(ArmorItem.Type.LEGGINGS, 6);
            map.put(ArmorItem.Type.CHESTPLATE, 8);
            map.put(ArmorItem.Type.HELMET, 3);
        }), 25, 3.5F, 0.2F, Items.GOLD_INGOT);
    }

    /**
     * Registers an armor material with specified attributes.
     *
     * @param name                  The name of the armor material.
     * @param typeProtections       The protection values for each armor slot.
     * @param enchantability        The enchantability of the armor.
     * @param toughness             The toughness of the armor.
     * @param knockbackResistance   The knockback resistance of the armor.
     * @param ingredientItem        The item used to repair the armor.
     * @return A Holder<ArmorMaterial> representing the registered armor material.
     */
    private static Holder<ArmorMaterial> register(String name, EnumMap<ArmorItem.Type, Integer> typeProtections, int enchantability, float toughness, float knockbackResistance, Item ingredientItem) {
        ResourceLocation loc = ResourceLocation.fromNamespaceAndPath(BattleGearMod.MOD_ID, name);
        Holder<SoundEvent> equipSound = SoundEvents.ARMOR_EQUIP_NETHERITE;
        Supplier<Ingredient> ingredient = () -> Ingredient.of(ingredientItem);
        List<ArmorMaterial.Layer> layers = List.of(new ArmorMaterial.Layer(loc));

        EnumMap<ArmorItem.Type, Integer> typeMap = new EnumMap<>(ArmorItem.Type.class);
        for (ArmorItem.Type type : ArmorItem.Type.values())
        {
            typeMap.put(type, typeProtections.get(type));
        }
        return Registry.registerForHolder(BuiltInRegistries.ARMOR_MATERIAL, loc, new ArmorMaterial(typeProtections, enchantability, equipSound, ingredient, layers, toughness, knockbackResistance));
    }
}
