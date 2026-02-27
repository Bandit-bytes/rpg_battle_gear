package net.bandit.battlegear.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.bandit.battlegear.item.*;
import net.bandit.battlegear.item.armor.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.*;
import net.bandit.battlegear.BattleGearMod;
import net.minecraft.world.item.component.ItemAttributeModifiers;

public class ItemRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(BattleGearMod.MOD_ID, Registries.ITEM);

    // ARMOR SETS
    public static final RegistrySupplier<Item> CRUSADER_HELMET = ITEMS.register("crusader_helmet",
            () -> new CrusaderArmorItem(BattleGearArmorMaterials.CRUSADER, ArmorItem.Type.HELMET, new Item.Properties().durability(390).stacksTo(1).arch$tab(TabRegistry.RPG_BATTLEGEAR_TAB).rarity(Rarity.RARE)));
    public static final RegistrySupplier<Item> CRUSADER_CHESTPLATE = ITEMS.register("crusader_chestplate",
            () -> new CrusaderArmorItem(BattleGearArmorMaterials.CRUSADER, ArmorItem.Type.CHESTPLATE, new Item.Properties().durability(390).stacksTo(1).arch$tab(TabRegistry.RPG_BATTLEGEAR_TAB).rarity(Rarity.RARE)));
    public static final RegistrySupplier<Item> CRUSADER_LEGGINGS = ITEMS.register("crusader_leggings",
            () -> new CrusaderArmorItem(BattleGearArmorMaterials.CRUSADER, ArmorItem.Type.LEGGINGS, new Item.Properties().durability(390).stacksTo(1).arch$tab(TabRegistry.RPG_BATTLEGEAR_TAB).rarity(Rarity.RARE)));
    public static final RegistrySupplier<Item> CRUSADER_BOOTS = ITEMS.register("crusader_boots",
            () -> new CrusaderArmorItem(BattleGearArmorMaterials.CRUSADER, ArmorItem.Type.BOOTS, new Item.Properties().durability(390).stacksTo(1).arch$tab(TabRegistry.RPG_BATTLEGEAR_TAB).rarity(Rarity.RARE)));

    public static final RegistrySupplier<Item> MYSTIC_HELMET = ITEMS.register("mystic_helmet",
            () -> new MysticArmorItem(BattleGearArmorMaterials.MYSTIC, ArmorItem.Type.HELMET, new Item.Properties().durability(410).stacksTo(1).arch$tab(TabRegistry.RPG_BATTLEGEAR_TAB).rarity(Rarity.RARE)));
    public static final RegistrySupplier<Item> MYSTIC_CHESTPLATE = ITEMS.register("mystic_chestplate",
            () -> new MysticArmorItem(BattleGearArmorMaterials.MYSTIC, ArmorItem.Type.CHESTPLATE, new Item.Properties().durability(410).stacksTo(1).arch$tab(TabRegistry.RPG_BATTLEGEAR_TAB).rarity(Rarity.RARE)));
    public static final RegistrySupplier<Item> MYSTIC_LEGGINGS = ITEMS.register("mystic_leggings",
            () -> new MysticArmorItem(BattleGearArmorMaterials.MYSTIC, ArmorItem.Type.LEGGINGS, new Item.Properties().durability(410).stacksTo(1).arch$tab(TabRegistry.RPG_BATTLEGEAR_TAB).rarity(Rarity.RARE)));
    public static final RegistrySupplier<Item> MYSTIC_BOOTS = ITEMS.register("mystic_boots",
            () -> new MysticArmorItem(BattleGearArmorMaterials.MYSTIC, ArmorItem.Type.BOOTS, new Item.Properties().durability(410).stacksTo(1).arch$tab(TabRegistry.RPG_BATTLEGEAR_TAB).rarity(Rarity.RARE)));

    public static final RegistrySupplier<Item> TEMPLAR_HELMET = ITEMS.register("templar_helmet",
            () -> new TemplarArmorItem(BattleGearArmorMaterials.TEMPLAR, ArmorItem.Type.HELMET, new Item.Properties().durability(410).stacksTo(1).arch$tab(TabRegistry.RPG_BATTLEGEAR_TAB).rarity(Rarity.RARE)));
    public static final RegistrySupplier<Item> TEMPLAR_CHESTPLATE = ITEMS.register("templar_chestplate",
            () -> new TemplarArmorItem(BattleGearArmorMaterials.TEMPLAR, ArmorItem.Type.CHESTPLATE, new Item.Properties().durability(410).stacksTo(1).arch$tab(TabRegistry.RPG_BATTLEGEAR_TAB).rarity(Rarity.RARE)));
    public static final RegistrySupplier<Item> TEMPLAR_LEGGINGS = ITEMS.register("templar_leggings",
            () -> new TemplarArmorItem(BattleGearArmorMaterials.TEMPLAR, ArmorItem.Type.LEGGINGS, new Item.Properties().durability(410).stacksTo(1).arch$tab(TabRegistry.RPG_BATTLEGEAR_TAB).rarity(Rarity.RARE)));
    public static final RegistrySupplier<Item> TEMPLAR_BOOTS = ITEMS.register("templar_boots",
            () -> new TemplarArmorItem(BattleGearArmorMaterials.TEMPLAR, ArmorItem.Type.BOOTS, new Item.Properties().durability(410).stacksTo(1).arch$tab(TabRegistry.RPG_BATTLEGEAR_TAB).rarity(Rarity.RARE)));

    public static final RegistrySupplier<Item> GUARDIAN_HELMET = ITEMS.register("guardian_helmet",
            () -> new GuardianArmorItem(BattleGearArmorMaterials.GUARDIAN, ArmorItem.Type.HELMET, new Item.Properties().durability(410).stacksTo(1).arch$tab(TabRegistry.RPG_BATTLEGEAR_TAB).rarity(Rarity.RARE)));
    public static final RegistrySupplier<Item> GUARDIAN_CHESTPLATE = ITEMS.register("guardian_chestplate",
            () -> new GuardianArmorItem(BattleGearArmorMaterials.GUARDIAN, ArmorItem.Type.CHESTPLATE, new Item.Properties().durability(410).stacksTo(1).arch$tab(TabRegistry.RPG_BATTLEGEAR_TAB).rarity(Rarity.RARE)));
    public static final RegistrySupplier<Item> GUARDIAN_LEGGINGS = ITEMS.register("guardian_leggings",
            () -> new GuardianArmorItem(BattleGearArmorMaterials.GUARDIAN, ArmorItem.Type.LEGGINGS, new Item.Properties().durability(410).stacksTo(1).arch$tab(TabRegistry.RPG_BATTLEGEAR_TAB).rarity(Rarity.RARE)));
    public static final RegistrySupplier<Item> GUARDIAN_BOOTS = ITEMS.register("guardian_boots",
            () -> new GuardianArmorItem(BattleGearArmorMaterials.GUARDIAN, ArmorItem.Type.BOOTS, new Item.Properties().durability(410).stacksTo(1).arch$tab(TabRegistry.RPG_BATTLEGEAR_TAB).rarity(Rarity.RARE)));

    public static final RegistrySupplier<Item> SERAPHIM_HELMET = ITEMS.register("seraphim_helmet",
            () -> new SeraphimArmorItem(BattleGearArmorMaterials.SERAPHIM, ArmorItem.Type.HELMET, new Item.Properties().durability(490).stacksTo(1).arch$tab(TabRegistry.RPG_BATTLEGEAR_TAB).rarity(Rarity.RARE)));
    public static final RegistrySupplier<Item> SERAPHIM_CHESTPLATE = ITEMS.register("seraphim_chestplate",
            () -> new SeraphimArmorItem(BattleGearArmorMaterials.SERAPHIM, ArmorItem.Type.CHESTPLATE, new Item.Properties().durability(490).stacksTo(1).arch$tab(TabRegistry.RPG_BATTLEGEAR_TAB).rarity(Rarity.RARE)));
    public static final RegistrySupplier<Item> SERAPHIM_LEGGINGS = ITEMS.register("seraphim_leggings",
            () -> new SeraphimArmorItem(BattleGearArmorMaterials.SERAPHIM, ArmorItem.Type.LEGGINGS, new Item.Properties().durability(490).stacksTo(1).arch$tab(TabRegistry.RPG_BATTLEGEAR_TAB).rarity(Rarity.RARE)));
    public static final RegistrySupplier<Item> SERAPHIM_BOOTS = ITEMS.register("seraphim_boots",
            () -> new SeraphimArmorItem(BattleGearArmorMaterials.SERAPHIM, ArmorItem.Type.BOOTS, new Item.Properties().durability(490).stacksTo(1).arch$tab(TabRegistry.RPG_BATTLEGEAR_TAB).rarity(Rarity.RARE)));

    public static final RegistrySupplier<Item> SOVEREIGN_HELMET = ITEMS.register("sovereign_helmet",
            () -> new SovereignArmorItem(BattleGearArmorMaterials.SOVEREIGN, ArmorItem.Type.HELMET, new Item.Properties().durability(560).stacksTo(1).arch$tab(TabRegistry.RPG_BATTLEGEAR_TAB).rarity(Rarity.EPIC)));
    public static final RegistrySupplier<Item> SOVEREIGN_CHESTPLATE = ITEMS.register("sovereign_chestplate",
            () -> new SovereignArmorItem(BattleGearArmorMaterials.SOVEREIGN, ArmorItem.Type.CHESTPLATE, new Item.Properties().durability(560).stacksTo(1).arch$tab(TabRegistry.RPG_BATTLEGEAR_TAB).rarity(Rarity.EPIC)));
    public static final RegistrySupplier<Item> SOVEREIGN_LEGGINGS = ITEMS.register("sovereign_leggings",
            () -> new SovereignArmorItem(BattleGearArmorMaterials.SOVEREIGN, ArmorItem.Type.LEGGINGS, new Item.Properties().durability(560).stacksTo(1).arch$tab(TabRegistry.RPG_BATTLEGEAR_TAB).rarity(Rarity.EPIC)));
    public static final RegistrySupplier<Item> SOVEREIGN_BOOTS = ITEMS.register("sovereign_boots",
            () -> new SovereignArmorItem(BattleGearArmorMaterials.SOVEREIGN, ArmorItem.Type.BOOTS, new Item.Properties().durability(560).stacksTo(1).arch$tab(TabRegistry.RPG_BATTLEGEAR_TAB).rarity(Rarity.EPIC)));

    public static final RegistrySupplier<Item> DREADLORD_HELMET = ITEMS.register("dreadlord_helmet",
            () -> new DreadlordArmorItem(BattleGearArmorMaterials.DREADLORD, ArmorItem.Type.HELMET, new Item.Properties().durability(610).stacksTo(1).arch$tab(TabRegistry.RPG_BATTLEGEAR_TAB).rarity(Rarity.EPIC)));
    public static final RegistrySupplier<Item> DREADLORD_CHESTPLATE = ITEMS.register("dreadlord_chestplate",
            () -> new DreadlordArmorItem(BattleGearArmorMaterials.DREADLORD, ArmorItem.Type.CHESTPLATE, new Item.Properties().durability(610).stacksTo(1).arch$tab(TabRegistry.RPG_BATTLEGEAR_TAB).rarity(Rarity.EPIC)));
    public static final RegistrySupplier<Item> DREADLORD_LEGGINGS = ITEMS.register("dreadlord_leggings",
            () -> new DreadlordArmorItem(BattleGearArmorMaterials.DREADLORD, ArmorItem.Type.LEGGINGS, new Item.Properties().durability(610).stacksTo(1).arch$tab(TabRegistry.RPG_BATTLEGEAR_TAB).rarity(Rarity.EPIC)));
    public static final RegistrySupplier<Item> DREADLORD_BOOTS = ITEMS.register("dreadlord_boots",
            () -> new DreadlordArmorItem(BattleGearArmorMaterials.DREADLORD, ArmorItem.Type.BOOTS, new Item.Properties().durability(610).stacksTo(1).arch$tab(TabRegistry.RPG_BATTLEGEAR_TAB).rarity(Rarity.EPIC)));

    public static final RegistrySupplier<Item> HELLFORGED_HELMET = ITEMS.register("hellforged_helmet",
            () -> new HellforgedArmorItem(BattleGearArmorMaterials.HELLFORGED, ArmorItem.Type.HELMET, new Item.Properties().durability(610).stacksTo(1).arch$tab(TabRegistry.RPG_BATTLEGEAR_TAB).rarity(Rarity.EPIC)));
    public static final RegistrySupplier<Item> HELLFORGED_CHESTPLATE = ITEMS.register("hellforged_chestplate",
            () -> new HellforgedArmorItem(BattleGearArmorMaterials.HELLFORGED, ArmorItem.Type.CHESTPLATE, new Item.Properties().durability(610).stacksTo(1).arch$tab(TabRegistry.RPG_BATTLEGEAR_TAB).rarity(Rarity.EPIC)));
    public static final RegistrySupplier<Item> HELLFORGED_LEGGINGS = ITEMS.register("hellforged_leggings",
            () -> new HellforgedArmorItem(BattleGearArmorMaterials.HELLFORGED, ArmorItem.Type.LEGGINGS, new Item.Properties().durability(610).stacksTo(1).arch$tab(TabRegistry.RPG_BATTLEGEAR_TAB).rarity(Rarity.EPIC)));
    public static final RegistrySupplier<Item> HELLFORGED_BOOTS = ITEMS.register("hellforged_boots",
            () -> new HellforgedArmorItem(BattleGearArmorMaterials.HELLFORGED, ArmorItem.Type.BOOTS, new Item.Properties().durability(610).stacksTo(1).arch$tab(TabRegistry.RPG_BATTLEGEAR_TAB).rarity(Rarity.EPIC)));
    //WEAPONS
    public static final RegistrySupplier<Item> VALOR_SWORD = ITEMS.register("valor_sword", () ->
            new SwordItem(Tiers.IRON, new Item.Properties()
                    .attributes(SwordItem.createAttributes(Tiers.IRON, 7, -2.0F)).rarity(Rarity.UNCOMMON)
                    .arch$tab(TabRegistry.RPG_BATTLEGEAR_TAB)));
    public static final RegistrySupplier<Item> TEMPLAR_AXE = ITEMS.register("templar_axe", () ->
            new SwordItem(Tiers.NETHERITE, new Item.Properties()
                    .attributes(SwordItem.createAttributes(Tiers.NETHERITE, 9, -2.0F)).rarity(Rarity.RARE)
                    .arch$tab(TabRegistry.RPG_BATTLEGEAR_TAB)));
    public static final RegistrySupplier<Item> GUARDIAN_HAMMER = ITEMS.register("guardian_hammer", () ->
            new MaceItem(
                    new Item.Properties().stacksTo(1).rarity(Rarity.EPIC).durability(2031).arch$tab(TabRegistry.RPG_BATTLEGEAR_TAB).attributes(ItemAttributeModifiers.builder().add(Attributes.ATTACK_DAMAGE,
                            new AttributeModifier(Item.BASE_ATTACK_DAMAGE_ID, 12.0D, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.bySlot(EquipmentSlot.MAINHAND)).add(Attributes.ATTACK_SPEED,
                            new AttributeModifier(Item.BASE_ATTACK_SPEED_ID, -3.2D, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.bySlot(EquipmentSlot.MAINHAND)).build())));
    public static final RegistrySupplier<Item> SOUL_BLADE = ITEMS.register("soul_blade", () ->
            new SoulBladeSwordItem(Tiers.NETHERITE, new Item.Properties()
                    .attributes(SwordItem.createAttributes(Tiers.NETHERITE, 10, -2.0F)).rarity(Rarity.EPIC)
                    .arch$tab(TabRegistry.RPG_BATTLEGEAR_TAB)));
    public static final RegistrySupplier<Item> STORMREAVER = ITEMS.register("stormreaver",
            () -> new StormreaverSwordItem(
                    Tiers.NETHERITE,
                    new Item.Properties()
                            .stacksTo(1)
                            .durability(2031)
                            .rarity(Rarity.EPIC)
                            .arch$tab(TabRegistry.RPG_BATTLEGEAR_TAB)
                            .attributes(SwordItem.createAttributes(Tiers.NETHERITE, 10, -2.4F))
            ));
    public static final RegistrySupplier<Item> LAVA_SWORD = ITEMS.register("lava_sword", () ->
            new LavaSword(Tiers.IRON, new Item.Properties()
                    .attributes(SwordItem.createAttributes(Tiers.IRON, 7, -2.2F))
                    .rarity(Rarity.UNCOMMON)
                    .arch$tab(TabRegistry.RPG_BATTLEGEAR_TAB)));
    public static final RegistrySupplier<Item> OCEAN_SWORD = ITEMS.register("ocean_sword", () ->
            new OceanSword(Tiers.IRON, new Item.Properties()
                    .attributes(SwordItem.createAttributes(Tiers.IRON, 6, -1.8F))
                    .rarity(Rarity.UNCOMMON)
                    .arch$tab(TabRegistry.RPG_BATTLEGEAR_TAB)));
    public static final RegistrySupplier<Item> OCTOPUS_SWORD = ITEMS.register("octopus_sword", () ->
            new OctopusSword(Tiers.IRON, new Item.Properties()
                    .attributes(SwordItem.createAttributes(Tiers.IRON, 8, -2.4F))
                    .rarity(Rarity.RARE)
                    .arch$tab(TabRegistry.RPG_BATTLEGEAR_TAB)));
    public static final RegistrySupplier<Item> EXPLOSION_SWORD = ITEMS.register("explosion_sword", () ->
            new ExplosionSword(Tiers.IRON, new Item.Properties()
                    .attributes(SwordItem.createAttributes(Tiers.IRON, 10, -2.8F))
                    .rarity(Rarity.EPIC)
                    .arch$tab(TabRegistry.RPG_BATTLEGEAR_TAB)));
    public static final RegistrySupplier<Item> FIRE_SWORD = ITEMS.register("fire_sword", () ->
            new FireSword(Tiers.IRON, new Item.Properties()
                    .attributes(SwordItem.createAttributes(Tiers.IRON, 9, -2.2F))
                    .rarity(Rarity.RARE)
                    .arch$tab(TabRegistry.RPG_BATTLEGEAR_TAB)));
    public static final RegistrySupplier<Item> FLOWER_SWORD = ITEMS.register("flower_sword", () ->
            new FlowerSword(Tiers.IRON, new Item.Properties()
                    .attributes(SwordItem.createAttributes(Tiers.IRON, 6, -2.0F))
                    .rarity(Rarity.UNCOMMON)
                    .arch$tab(TabRegistry.RPG_BATTLEGEAR_TAB)));
    public static final RegistrySupplier<Item> ICE_SWORD = ITEMS.register("ice_sword", () ->
            new IceSword(Tiers.IRON, new Item.Properties()
                    .attributes(SwordItem.createAttributes(Tiers.IRON, 7, -2.0F))
                    .rarity(Rarity.UNCOMMON)
                    .arch$tab(TabRegistry.RPG_BATTLEGEAR_TAB)));
    public static final RegistrySupplier<Item> JEWEL_SWORD = ITEMS.register("jewel_sword", () ->
            new JewelSword(Tiers.DIAMOND, new Item.Properties()
                    .attributes(SwordItem.createAttributes(Tiers.DIAMOND, 8, -1.6F))
                    .rarity(Rarity.EPIC)
                    .arch$tab(TabRegistry.RPG_BATTLEGEAR_TAB)));

    // MISCELLANEOUS
    public static final RegistrySupplier<Item> ARCANE_EMBER = ITEMS.register("arcane_ember", () -> new ArcaneEmberItem(new Item.Properties().stacksTo(64).rarity(Rarity.UNCOMMON).arch$tab(TabRegistry.RPG_BATTLEGEAR_TAB)));
    public static final RegistrySupplier<Item> SACRED_GEM = ITEMS.register("sacred_gem", () -> new Item(new Item.Properties().stacksTo(64).rarity(Rarity.RARE).arch$tab(TabRegistry.RPG_BATTLEGEAR_TAB)));
    public static final RegistrySupplier<Item> ANCIENT_CORE = ITEMS.register("ancient_core", () -> new Item(new Item.Properties().stacksTo(64).rarity(Rarity.EPIC).arch$tab(TabRegistry.RPG_BATTLEGEAR_TAB)));
    public static final RegistrySupplier<Item> STORM_CORE = ITEMS.register("storm_core", () -> new Item(new Item.Properties().stacksTo(64).rarity(Rarity.EPIC).arch$tab(TabRegistry.RPG_BATTLEGEAR_TAB)));
    public static final RegistrySupplier<Item> SOUL_CORE = ITEMS.register("soul_core", () -> new Item(new Item.Properties().stacksTo(64).rarity(Rarity.EPIC).arch$tab(TabRegistry.RPG_BATTLEGEAR_TAB)));
    public static final RegistrySupplier<Item> MYSTIC_ESSENCE = ITEMS.register("mystic_essence", () -> new Item(new Item.Properties().stacksTo(64).rarity(Rarity.UNCOMMON).arch$tab(TabRegistry.RPG_BATTLEGEAR_TAB)));
    public static final RegistrySupplier<Item> TEMPLAR_EMBLEM = ITEMS.register("templar_emblem", () -> new Item(new Item.Properties().stacksTo(64).rarity(Rarity.RARE).arch$tab(TabRegistry.RPG_BATTLEGEAR_TAB)));
    public static final RegistrySupplier<Item> GUARDIAN_HEART = ITEMS.register("guardian_heart", () -> new Item(new Item.Properties().stacksTo(64).rarity(Rarity.EPIC).arch$tab(TabRegistry.RPG_BATTLEGEAR_TAB)));
    public static final RegistrySupplier<Item> SOVEREIGN_GEM = ITEMS.register("sovereign_gem", () -> new SovereignGemItem(new Item.Properties().stacksTo(64).rarity(Rarity.EPIC).arch$tab(TabRegistry.RPG_BATTLEGEAR_TAB)));

    public static void init() {
        ITEMS.register();
    }
}
