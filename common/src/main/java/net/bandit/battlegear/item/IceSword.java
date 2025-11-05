package net.bandit.battlegear.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.Level;
import net.minecraft.core.particles.ParticleTypes;

import java.util.List;

public class IceSword extends SwordItem {
    public IceSword(Tier tier, Properties properties) {
        super(tier, properties);
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        Level level = attacker.level();

        target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 60, 1));
        if (target.hasEffect(MobEffects.MOVEMENT_SLOWDOWN)) {
            target.hurt(attacker.damageSources().magic(), 2.0F);
        }

        // Frost particles around the hit
        if (level.isClientSide) {
            for (int i = 0; i < 10; i++) {
                double x = target.getX() + (level.random.nextDouble() - 0.5) * 1.4;
                double y = target.getY() + level.random.nextDouble() * 1.6;
                double z = target.getZ() + (level.random.nextDouble() - 0.5) * 1.4;
                level.addParticle(ParticleTypes.SNOWFLAKE, x, y, z, 0, 0.01, 0);
            }
        }

        return super.hurtEnemy(stack, target, attacker);
    }

    @Override public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.translatable("item.rpg_battle_gear.ice_sword.tooltip").withStyle(ChatFormatting.AQUA));
    }
}
