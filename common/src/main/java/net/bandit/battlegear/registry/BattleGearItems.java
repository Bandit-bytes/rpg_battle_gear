package net.bandit.battlegear.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.bandit.battlegear.item.BattleGearArmorMaterials;
import net.bandit.battlegear.item.armor.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.bandit.battlegear.BattleGearMod;
import net.minecraft.world.item.Rarity;

public class BattleGearItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(BattleGearMod.MOD_ID, Registries.ITEM);

    // ARMOR SETS
    public static final RegistrySupplier<Item> CRUSADER_HELMET = ITEMS.register("crusader_helmet",
            () -> new CrusaderArmorItem(BattleGearArmorMaterials.CRUSADER, ArmorItem.Type.HELMET, new Item.Properties().stacksTo(1).arch$tab(TabRegistry.RPG_BATTLEGEAR_TAB).rarity(Rarity.RARE)));
    public static final RegistrySupplier<Item> CRUSADER_CHESTPLATE = ITEMS.register("crusader_chestplate",
            () -> new CrusaderArmorItem(BattleGearArmorMaterials.CRUSADER, ArmorItem.Type.CHESTPLATE, new Item.Properties().stacksTo(1).arch$tab(TabRegistry.RPG_BATTLEGEAR_TAB).rarity(Rarity.RARE)));
    public static final RegistrySupplier<Item> CRUSADER_LEGGINGS = ITEMS.register("crusader_leggings",
            () -> new CrusaderArmorItem(BattleGearArmorMaterials.CRUSADER, ArmorItem.Type.LEGGINGS, new Item.Properties().stacksTo(1).arch$tab(TabRegistry.RPG_BATTLEGEAR_TAB).rarity(Rarity.RARE)));
    public static final RegistrySupplier<Item> CRUSADER_BOOTS = ITEMS.register("crusader_boots",
            () -> new CrusaderArmorItem(BattleGearArmorMaterials.CRUSADER, ArmorItem.Type.BOOTS, new Item.Properties().stacksTo(1).arch$tab(TabRegistry.RPG_BATTLEGEAR_TAB).rarity(Rarity.RARE)));

    public static final RegistrySupplier<Item> MYSTIC_HELMET = ITEMS.register("mystic_helmet",
            () -> new MysticArmorItem(BattleGearArmorMaterials.MYSTIC, ArmorItem.Type.HELMET, new Item.Properties().stacksTo(1).arch$tab(TabRegistry.RPG_BATTLEGEAR_TAB).rarity(Rarity.RARE)));
    public static final RegistrySupplier<Item> MYSTIC_CHESTPLATE = ITEMS.register("mystic_chestplate",
            () -> new MysticArmorItem(BattleGearArmorMaterials.MYSTIC, ArmorItem.Type.CHESTPLATE, new Item.Properties().stacksTo(1).arch$tab(TabRegistry.RPG_BATTLEGEAR_TAB).rarity(Rarity.RARE)));
    public static final RegistrySupplier<Item> MYSTIC_LEGGINGS = ITEMS.register("mystic_leggings",
            () -> new MysticArmorItem(BattleGearArmorMaterials.MYSTIC, ArmorItem.Type.LEGGINGS, new Item.Properties().stacksTo(1).arch$tab(TabRegistry.RPG_BATTLEGEAR_TAB).rarity(Rarity.RARE)));
    public static final RegistrySupplier<Item> MYSTIC_BOOTS = ITEMS.register("mystic_boots",
            () -> new MysticArmorItem(BattleGearArmorMaterials.MYSTIC, ArmorItem.Type.BOOTS, new Item.Properties().stacksTo(1).arch$tab(TabRegistry.RPG_BATTLEGEAR_TAB).rarity(Rarity.RARE)));

    public static final RegistrySupplier<Item> TEMPLAR_HELMET = ITEMS.register("templar_helmet",
            () -> new TemplarArmorItem(BattleGearArmorMaterials.TEMPLAR, ArmorItem.Type.HELMET, new Item.Properties().stacksTo(1).arch$tab(TabRegistry.RPG_BATTLEGEAR_TAB).rarity(Rarity.RARE)));
    public static final RegistrySupplier<Item> TEMPLAR_CHESTPLATE = ITEMS.register("templar_chestplate",
            () -> new TemplarArmorItem(BattleGearArmorMaterials.TEMPLAR, ArmorItem.Type.CHESTPLATE, new Item.Properties().stacksTo(1).arch$tab(TabRegistry.RPG_BATTLEGEAR_TAB).rarity(Rarity.RARE)));
    public static final RegistrySupplier<Item> TEMPLAR_LEGGINGS = ITEMS.register("templar_leggings",
            () -> new TemplarArmorItem(BattleGearArmorMaterials.TEMPLAR, ArmorItem.Type.LEGGINGS, new Item.Properties().stacksTo(1).arch$tab(TabRegistry.RPG_BATTLEGEAR_TAB).rarity(Rarity.RARE)));
    public static final RegistrySupplier<Item> TEMPLAR_BOOTS = ITEMS.register("templar_boots",
            () -> new TemplarArmorItem(BattleGearArmorMaterials.TEMPLAR, ArmorItem.Type.BOOTS, new Item.Properties().stacksTo(1).arch$tab(TabRegistry.RPG_BATTLEGEAR_TAB).rarity(Rarity.RARE)));

    public static final RegistrySupplier<Item> GUARDIAN_HELMET = ITEMS.register("guardian_helmet",
            () -> new GuardianArmorItem(BattleGearArmorMaterials.GUARDIAN, ArmorItem.Type.HELMET, new Item.Properties().stacksTo(1).arch$tab(TabRegistry.RPG_BATTLEGEAR_TAB).rarity(Rarity.RARE)));
    public static final RegistrySupplier<Item> GUARDIAN_CHESTPLATE = ITEMS.register("guardian_chestplate",
            () -> new GuardianArmorItem(BattleGearArmorMaterials.GUARDIAN, ArmorItem.Type.CHESTPLATE, new Item.Properties().stacksTo(1).arch$tab(TabRegistry.RPG_BATTLEGEAR_TAB).rarity(Rarity.RARE)));
    public static final RegistrySupplier<Item> GUARDIAN_LEGGINGS = ITEMS.register("guardian_leggings",
            () -> new GuardianArmorItem(BattleGearArmorMaterials.GUARDIAN, ArmorItem.Type.LEGGINGS, new Item.Properties().stacksTo(1).arch$tab(TabRegistry.RPG_BATTLEGEAR_TAB).rarity(Rarity.RARE)));
    public static final RegistrySupplier<Item> GUARDIAN_BOOTS = ITEMS.register("guardian_boots",
            () -> new GuardianArmorItem(BattleGearArmorMaterials.GUARDIAN, ArmorItem.Type.BOOTS, new Item.Properties().stacksTo(1).arch$tab(TabRegistry.RPG_BATTLEGEAR_TAB).rarity(Rarity.RARE)));

    public static final RegistrySupplier<Item> SERAPHIM_HELMET = ITEMS.register("seraphim_helmet",
            () -> new SeraphimArmorItem(BattleGearArmorMaterials.SERAPHIM, ArmorItem.Type.HELMET, new Item.Properties().stacksTo(1).arch$tab(TabRegistry.RPG_BATTLEGEAR_TAB).rarity(Rarity.RARE)));
    public static final RegistrySupplier<Item> SERAPHIM_CHESTPLATE = ITEMS.register("seraphim_chestplate",
            () -> new SeraphimArmorItem(BattleGearArmorMaterials.SERAPHIM, ArmorItem.Type.CHESTPLATE, new Item.Properties().stacksTo(1).arch$tab(TabRegistry.RPG_BATTLEGEAR_TAB).rarity(Rarity.RARE)));
    public static final RegistrySupplier<Item> SERAPHIM_LEGGINGS = ITEMS.register("seraphim_leggings",
            () -> new SeraphimArmorItem(BattleGearArmorMaterials.SERAPHIM, ArmorItem.Type.LEGGINGS, new Item.Properties().stacksTo(1).arch$tab(TabRegistry.RPG_BATTLEGEAR_TAB).rarity(Rarity.RARE)));
    public static final RegistrySupplier<Item> SERAPHIM_BOOTS = ITEMS.register("seraphim_boots",
            () -> new SeraphimArmorItem(BattleGearArmorMaterials.SERAPHIM, ArmorItem.Type.BOOTS, new Item.Properties().stacksTo(1).arch$tab(TabRegistry.RPG_BATTLEGEAR_TAB).rarity(Rarity.RARE)));

    // TODO WEAPONS
//    public static final RegistrySupplier<Item> VALOR_SWORD = ITEMS.register("valor_sword", () -> new SwordItem(Tiers.IRON, 3, -2.4F, new Item.Properties().rarity(Rarity.UNCOMMON).arch$tab(TabRegistry.RPG_BATTLEGEAR_TAB)));
//    public static final RegistrySupplier<Item> MYSTIC_SHIELD = ITEMS.register("mystic_shield", () -> new Item(new Item.Properties().stacksTo(1).arch$tab(TabRegistry.RPG_BATTLEGEAR_TAB)));
//    public static final RegistrySupplier<Item> templar_AXE = ITEMS.register("templar_axe", () -> new SwordItem(Tiers.IRON, 3, -2.4F, new Item.Properties().rarity(Rarity.UNCOMMON).arch$tab(TabRegistry.RPG_BATTLEGEAR_TAB)));
//    public static final RegistrySupplier<Item> GUARDIAN_HAMMER = ITEMS.register("guardian_hammer", () -> new SwordItem(Tiers.IRON, 5, -2.8F, new Item.Properties().rarity(Rarity.RARE).arch$tab(TabRegistry.RPG_BATTLEGEAR_TAB)));


    // MISCELLANEOUS
    public static final RegistrySupplier<Item> SACRED_GEM = ITEMS.register("sacred_gem", () -> new Item(new Item.Properties().stacksTo(64).rarity(Rarity.RARE).arch$tab(TabRegistry.RPG_BATTLEGEAR_TAB)));
    public static final RegistrySupplier<Item> ANCIENT_CORE = ITEMS.register("ancient_core", () -> new Item(new Item.Properties().stacksTo(64).rarity(Rarity.EPIC).arch$tab(TabRegistry.RPG_BATTLEGEAR_TAB)));
    public static final RegistrySupplier<Item> MYSTIC_ESSENCE = ITEMS.register("mystic_essence", () -> new Item(new Item.Properties().stacksTo(64).rarity(Rarity.UNCOMMON).arch$tab(TabRegistry.RPG_BATTLEGEAR_TAB)));
    public static final RegistrySupplier<Item> TEMPLAR_EMBLEM = ITEMS.register("templar_emblem", () -> new Item(new Item.Properties().stacksTo(64).rarity(Rarity.RARE).arch$tab(TabRegistry.RPG_BATTLEGEAR_TAB)));
    public static final RegistrySupplier<Item> GUARDIAN_HEART = ITEMS.register("guardian_heart", () -> new Item(new Item.Properties().stacksTo(64).rarity(Rarity.EPIC).arch$tab(TabRegistry.RPG_BATTLEGEAR_TAB)));

    public static void init() {
        ITEMS.register();
    }
}
