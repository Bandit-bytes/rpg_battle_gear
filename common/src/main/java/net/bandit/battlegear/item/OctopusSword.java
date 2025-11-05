package net.bandit.battlegear.item;

import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.Level;

import java.util.List;

public class OctopusSword extends SwordItem {
    public OctopusSword(Tier tier, Properties properties) {
        super(tier, properties);
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        Level level = attacker.level();

        CustomData data = stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY);
        CompoundTag tag = data.copyTag();
        int ink = tag.getInt("InkPressure") + 1;
        tag.putInt("InkPressure", ink);
        stack.set(DataComponents.CUSTOM_DATA, CustomData.of(tag));

        int baseTime = attacker.isInWaterRainOrBubble() ? 80 : 40;
        target.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, baseTime, 0));
        target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, baseTime, 0));

        if (attacker.isInWaterRainOrBubble() && target.isInWaterRainOrBubble()) {
            target.hurt(attacker.damageSources().magic(), 1.0F);
        }

        if (level.isClientSide) {
            for (int i = 0; i < 10; i++) {
                double x = target.getX() + (level.random.nextDouble() - 0.5) * 1.2;
                double y = target.getY() + 0.4 + level.random.nextDouble() * 1.0;
                double z = target.getZ() + (level.random.nextDouble() - 0.5) * 1.2;
                level.addParticle(ParticleTypes.SQUID_INK, x, y, z, 0, 0.02, 0);
                if (target.isInWaterRainOrBubble()) {
                    level.addParticle(ParticleTypes.BUBBLE, x, y, z, 0, 0.01, 0);
                }
            }
        }

        if (ink >= 3) {
            tag.putInt("InkPressure", 0);
            stack.set(DataComponents.CUSTOM_DATA, CustomData.of(tag));

            if (!level.isClientSide) {
                target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 60, 2));
                target.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 60, 0));

                double dx = attacker.getX() - target.getX();
                double dz = attacker.getZ() - target.getZ();
                double len = Math.max(0.001, Math.hypot(dx, dz));
                target.push(dx / len * 0.45, 0.25, dz / len * 0.45);
            }

            if (level.isClientSide) {
                for (int i = 0; i < 24; i++) {
                    double angle = (Math.PI * 2 * i) / 24.0;
                    double r = 2.0;
                    double x = target.getX() + Math.cos(angle) * r;
                    double y = target.getY() + 0.4;
                    double z = target.getZ() + Math.sin(angle) * r;
                    level.addParticle(ParticleTypes.SQUID_INK, x, y, z, 0, 0.03, 0);
                }
            }
        }

        return super.hurtEnemy(stack, target, attacker);
    }

    @Override public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.translatable("item.rpg_battle_gear.octopus_sword.tooltip").withStyle(ChatFormatting.AQUA));
    }
}
