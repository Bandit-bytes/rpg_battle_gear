package net.bandit.battlegear.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class ExplosionSword extends SwordItem {
    public ExplosionSword(Tier tier, Properties properties) {
        super(tier, properties);
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        Level level = attacker.level();

        if (!level.isClientSide) {
            level.explode(
                    attacker,
                    target.getX(), target.getY(), target.getZ(),
                    2.0F,
                    Level.ExplosionInteraction.NONE
            );
        }

        return super.hurtEnemy(stack, target, attacker);
    }

    @Override public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.translatable("item.rpg_battle_gear.explosion_sword.tooltip").withStyle(ChatFormatting.YELLOW));
    }
}
