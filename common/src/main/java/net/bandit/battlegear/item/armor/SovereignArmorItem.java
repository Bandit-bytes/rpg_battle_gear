package net.bandit.battlegear.item.armor;

import net.bandit.battlegear.config.BattleGearConfig;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class SovereignArmorItem extends ArmorItem {

    public SovereignArmorItem(Holder<ArmorMaterial> material, Type type, Properties properties) {
        super(material, type, properties);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level world, net.minecraft.world.entity.Entity entity, int slot, boolean isSelected) {
        if (entity instanceof Player player && !world.isClientSide) {
            if (BattleGearConfig.ENABLE_SOVEREIGN_SET_BONUS && hasFullSet(player)) {
                player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 200, 0, true, false, false));
                player.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 200, 1, true, false, false));
            }
        }
        super.inventoryTick(stack, world, entity, slot, isSelected);
    }

    public static boolean hasFullSet(Player player) {
        ItemStack head = player.getItemBySlot(EquipmentSlot.HEAD);
        ItemStack chest = player.getItemBySlot(EquipmentSlot.CHEST);
        ItemStack legs = player.getItemBySlot(EquipmentSlot.LEGS);
        ItemStack feet = player.getItemBySlot(EquipmentSlot.FEET);

        return !head.isEmpty() && head.getItem() instanceof SovereignArmorItem &&
                !chest.isEmpty() && chest.getItem() instanceof SovereignArmorItem &&
                !legs.isEmpty() && legs.getItem() instanceof SovereignArmorItem &&
                !feet.isEmpty() && feet.getItem() instanceof SovereignArmorItem;
    }

    @Override
    public void appendHoverText(ItemStack itemStack, TooltipContext tooltipContext, List<Component> list, TooltipFlag tooltipFlag) {
        list.add(Component.translatable("item.battlegear.sovereign_armor.tooltip").withStyle(ChatFormatting.AQUA));

        list.add(Component.translatable("tooltip.battlegear.sovereign_armor.bonus_1")
                .withStyle(ChatFormatting.GRAY));

        list.add(Component.translatable("tooltip.battlegear.sovereign_armor.bonus_2")
                .withStyle(ChatFormatting.GRAY));
    }
}