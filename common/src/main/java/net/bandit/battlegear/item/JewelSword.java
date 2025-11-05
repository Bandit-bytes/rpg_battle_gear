package net.bandit.battlegear.item;

import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.Level;
import net.minecraft.core.particles.ParticleTypes;

import java.util.List;

public class JewelSword extends SwordItem {
    public JewelSword(Tier tier, Properties properties) {
        super(tier, properties);
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        Level level = attacker.level();

        CustomData data = stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY);
        CompoundTag tag = data.copyTag();

        int charge = tag.getInt("JewelCharge");
        charge++;
        tag.putInt("JewelCharge", charge);

        stack.set(DataComponents.CUSTOM_DATA, CustomData.of(tag));

        if (charge >= 3) {
            tag.putInt("JewelCharge", 0);
            stack.set(DataComponents.CUSTOM_DATA, CustomData.of(tag)); // Reset charge

            for (LivingEntity nearby : level.getEntitiesOfClass(LivingEntity.class, target.getBoundingBox().inflate(3))) {
                if (nearby != attacker) {
                    nearby.hurt(attacker.damageSources().magic(), 4.0F);
                    nearby.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 40, 0));
                }
            }

            if (level.isClientSide) {
                for (int i = 0; i < 20; i++) {
                    double x = target.getX() + (level.random.nextDouble() - 0.5) * 2.5;
                    double y = target.getY() + level.random.nextDouble() * 2;
                    double z = target.getZ() + (level.random.nextDouble() - 0.5) * 2.5;
                    level.addParticle(ParticleTypes.END_ROD, x, y, z, 0, 0.02, 0);
                }
            }
        }

        return super.hurtEnemy(stack, target, attacker);
    }

    @Override public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.translatable("item.rpg_battle_gear.jewel_sword.tooltip").withStyle(ChatFormatting.LIGHT_PURPLE));
    }
}
