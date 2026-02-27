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

public class DreadlordArmorItem extends ArmorItem {

    public DreadlordArmorItem(Holder<ArmorMaterial> material, Type type, Properties properties) {
        super(material, type, properties);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level world, net.minecraft.world.entity.Entity entity, int slot, boolean isSelected) {
        if (!world.isClientSide && entity instanceof Player player) {
            if (BattleGearConfig.ENABLE_DREADLORD_SET_BONUS && hasFullSet(player)) {
                // Equal-tier passive (not Hellforged fire-res + huge resist)
                player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 220, 0, true, false, false)); // Res I
                player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 220, 0, true, false, false));      // Str I
            }
        }
        super.inventoryTick(stack, world, entity, slot, isSelected);
    }

    public static boolean hasFullSet(Player player) {
        ItemStack head = player.getItemBySlot(EquipmentSlot.HEAD);
        ItemStack chest = player.getItemBySlot(EquipmentSlot.CHEST);
        ItemStack legs = player.getItemBySlot(EquipmentSlot.LEGS);
        ItemStack feet = player.getItemBySlot(EquipmentSlot.FEET);

        return !head.isEmpty() && head.getItem() instanceof DreadlordArmorItem &&
                !chest.isEmpty() && chest.getItem() instanceof DreadlordArmorItem &&
                !legs.isEmpty() && legs.getItem() instanceof DreadlordArmorItem &&
                !feet.isEmpty() && feet.getItem() instanceof DreadlordArmorItem;
    }

    @Override
    public void appendHoverText(ItemStack itemStack, TooltipContext tooltipContext, List<Component> list, TooltipFlag tooltipFlag) {
        list.add(Component.translatable("item.battlegear.dreadlord_armor.tooltip").withStyle(ChatFormatting.DARK_RED));
        list.add(Component.translatable("tooltip.battlegear.dreadlord_armor.bonus_1").withStyle(ChatFormatting.GRAY));
        list.add(Component.translatable("tooltip.battlegear.dreadlord_armor.bonus_2").withStyle(ChatFormatting.GRAY));
    }
}