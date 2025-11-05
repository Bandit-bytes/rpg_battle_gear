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

public class OceanSword extends SwordItem {
    public OceanSword(Tier tier, Properties properties) {
        super(tier, properties);
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        Level level = attacker.level();

        CustomData data = stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY);
        CompoundTag tag = data.copyTag();
        int tide = tag.getInt("Tide") + 1;
        tag.putInt("Tide", tide);
        stack.set(DataComponents.CUSTOM_DATA, CustomData.of(tag));

        target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 40, 0));
        if (target.isOnFire()) target.clearFire();

        if (attacker.isInWaterRainOrBubble()) {
            target.hurt(attacker.damageSources().magic(), 1.5F);
        }

        if (level.isClientSide) {
            for (int i = 0; i < 10; i++) {
                double x = target.getX() + (level.random.nextDouble() - 0.5) * 1.4;
                double y = target.getY() + 0.3 + level.random.nextDouble() * 1.2;
                double z = target.getZ() + (level.random.nextDouble() - 0.5) * 1.4;
                level.addParticle(ParticleTypes.SPLASH, x, y, z, 0, 0.01, 0);
                level.addParticle(ParticleTypes.BUBBLE, x, y, z, 0, 0.005, 0);
            }
        }

        if (tide >= 3) {
            tag.putInt("Tide", 0);
            stack.set(DataComponents.CUSTOM_DATA, CustomData.of(tag));
            if (!level.isClientSide) {
                for (LivingEntity nearby : level.getEntitiesOfClass(LivingEntity.class, target.getBoundingBox().inflate(3.5))) {
                    if (nearby == attacker) continue;

                    nearby.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 60, 1));
                    double dx = nearby.getX() - target.getX();
                    double dz = nearby.getZ() - target.getZ();
                    double len = Math.max(0.001, Math.hypot(dx, dz));
                    nearby.push(dx / len * 0.35, 0.25, dz / len * 0.35);

                    if (nearby.isOnFire()) nearby.clearFire();
                }

                attacker.addEffect(new MobEffectInstance(MobEffects.DOLPHINS_GRACE, 100, 0));
                attacker.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 100, 0));
            }

            if (level.isClientSide) {
                for (int i = 0; i < 24; i++) {
                    double angle = (Math.PI * 2 * i) / 24.0;
                    double r = 2.2;
                    double x = target.getX() + Math.cos(angle) * r;
                    double y = target.getY() + 0.2;
                    double z = target.getZ() + Math.sin(angle) * r;
                    level.addParticle(ParticleTypes.SPLASH, x, y, z, 0, 0.02, 0);
                }
            }
        }

        return super.hurtEnemy(stack, target, attacker);
    }

    @Override public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.translatable("item.rpg_battle_gear.ocean_sword.tooltip").withStyle(ChatFormatting.AQUA));
    }
}
