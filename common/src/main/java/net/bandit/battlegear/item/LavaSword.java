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

public class LavaSword extends SwordItem {
    public LavaSword(Tier tier, Properties properties) {
        super(tier, properties);
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        Level level = attacker.level();

        CustomData data = stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY);
        CompoundTag tag = data.copyTag();
        int heat = tag.getInt("LavaHeat") + 1;
        tag.putInt("LavaHeat", heat);
        stack.set(DataComponents.CUSTOM_DATA, CustomData.of(tag));

        // Small on-hit fire sting so it feels lava-y every swing
        target.setRemainingFireTicks(5);

        if (heat >= 3) {
            tag.putInt("LavaHeat", 0);
            stack.set(DataComponents.CUSTOM_DATA, CustomData.of(tag));

            if (!level.isClientSide) {
                for (LivingEntity nearby : level.getEntitiesOfClass(LivingEntity.class, target.getBoundingBox().inflate(3.5))) {
                    if (nearby == attacker) continue;

                    nearby.setRemainingFireTicks(5);
                    nearby.hurt(attacker.damageSources().onFire(), 4.0F);
                    nearby.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 40, 0));

                    double dx = nearby.getX() - target.getX();
                    double dz = nearby.getZ() - target.getZ();
                    double len = Math.max(0.001, Math.hypot(dx, dz));
                    nearby.push(dx / len * 0.4, 0.35, dz / len * 0.4);
                }
            }

            if (level.isClientSide) {
                for (int i = 0; i < 20; i++) {
                    double x = target.getX() + (level.random.nextDouble() - 0.5) * 2.2;
                    double y = target.getY() + 0.5 + level.random.nextDouble() * 1.6;
                    double z = target.getZ() + (level.random.nextDouble() - 0.5) * 2.2;
                    level.addParticle(ParticleTypes.LAVA, x, y, z, 0, 0.02, 0);
                }
                for (int i = 0; i < 14; i++) {
                    double x = target.getX() + (level.random.nextDouble() - 0.5) * 2.6;
                    double y = target.getY() + 0.2 + level.random.nextDouble() * 1.0;
                    double z = target.getZ() + (level.random.nextDouble() - 0.5) * 2.6;
                    level.addParticle(ParticleTypes.FLAME, x, y, z, 0, 0.01, 0);
                }
            }
        }

        return super.hurtEnemy(stack, target, attacker);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.translatable("item.rpg_battle_gear.lava_sword.tooltip").withStyle(ChatFormatting.RED));
    }
}
