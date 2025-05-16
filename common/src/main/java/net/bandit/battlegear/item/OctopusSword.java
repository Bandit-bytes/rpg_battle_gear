package net.bandit.battlegear.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class OctopusSword extends SwordItem {
    public OctopusSword(Tier tier, Properties properties) {
        super(tier, properties);
    }
    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.translatable("item.rpg_battle_gear.octopus_sword.tooltip").withStyle(ChatFormatting.GRAY));
    }

}
