package net.bandit.battlegear.item.armor;

import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

import java.util.List;

public class CrusaderArmorItem extends ArmorItem {

    public CrusaderArmorItem(Holder<ArmorMaterial> material, ArmorItem.Type type, Properties properties) {
        super(material, type, properties);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level world, net.minecraft.world.entity.Entity entity, int slot, boolean isSelected) {
        if (entity instanceof Player player && !world.isClientSide) {
            if (hasFullSet(player)) {
                player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 200, 1, true, false, false));
            }
        }
        super.inventoryTick(stack, world, entity, slot, isSelected);
    }
    private boolean hasFullSet(Player player) {
        ItemStack head = player.getItemBySlot(EquipmentSlot.HEAD);
        ItemStack chest = player.getItemBySlot(EquipmentSlot.CHEST);
        ItemStack legs = player.getItemBySlot(EquipmentSlot.LEGS);
        ItemStack feet = player.getItemBySlot(EquipmentSlot.FEET);
        return !head.isEmpty() && head.getItem() instanceof CrusaderArmorItem &&
                !chest.isEmpty() && chest.getItem() instanceof CrusaderArmorItem &&
                !legs.isEmpty() && legs.getItem() instanceof CrusaderArmorItem &&
                !feet.isEmpty() && feet.getItem() instanceof CrusaderArmorItem;
    }
    @Override
    public void appendHoverText(ItemStack itemStack, TooltipContext tooltipContext, List<Component> list, TooltipFlag tooltipFlag) {
        list.add(Component.translatable("item.battlegear.crusader_armor.tooltip"));
        list.add(Component.translatable("tooltip.battlegear.crusader_armor.effect").withStyle(ChatFormatting.GRAY));
    }
}
