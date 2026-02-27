package net.bandit.battlegear.item;

import net.minecraft.ChatFormatting;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

import java.util.*;

public class SoulBladeSwordItem extends SwordItem {

    private static final float BRAND_CHANCE = 0.25F;
    private static final int BRAND_DURATION = 80;
    private static final float DETONATE_DAMAGE = 5.0F;
    private static final float DETONATE_HEAL = 3.0F;

    private static final int NOVA_COOLDOWN = 100;
    private static final double NOVA_RANGE = 5.0D;
    private static final float NOVA_DAMAGE = 2.0F;
    private static final float NOVA_HEAL_PER_TARGET = 0.75F;
    private static final int NOVA_DURABILITY_COST = 8;

    private static final Map<UUID, Long> BRANDED = new HashMap<>();

    public SoulBladeSwordItem(Tier tier, Properties properties) {
        super(tier, properties);
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (attacker instanceof Player player && !player.level().isClientSide) {
            handleSoulBrand(stack, player, target);
        }
        return super.hurtEnemy(stack, target, attacker);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (!player.isShiftKeyDown()) {
            return InteractionResultHolder.pass(stack);
        }

        if (level.isClientSide) {
            return InteractionResultHolder.success(stack);
        }

        if (player.getCooldowns().isOnCooldown(this)) {
            return InteractionResultHolder.fail(stack);
        }

        player.getCooldowns().addCooldown(this, NOVA_COOLDOWN);

        EquipmentSlot slot = LivingEntity.getSlotForHand(hand);
        stack.hurtAndBreak(NOVA_DURABILITY_COST, player, slot);

        AABB box = player.getBoundingBox().inflate(NOVA_RANGE);
        List<LivingEntity> victims = level.getEntitiesOfClass(LivingEntity.class, box,
                e -> e != player && e.isAlive());

        if (victims.isEmpty()) {
            return InteractionResultHolder.success(stack);
        }

        float totalHeal = 0.0F;

        for (LivingEntity e : victims) {
            e.hurt(player.damageSources().magic(), NOVA_DAMAGE);
            e.addEffect(new MobEffectInstance(MobEffects.WITHER, 60, 0, true, false, false));
            e.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 60, 0, true, false, false));
            totalHeal += NOVA_HEAL_PER_TARGET;
        }

        player.heal(totalHeal);

        if (level instanceof ServerLevel serverLevel) {
            serverLevel.sendParticles(ParticleTypes.SOUL, player.getX(), player.getY() + 1.0, player.getZ(),
                    40, 0.6, 0.6, 0.6, 0.02);
            serverLevel.sendParticles(ParticleTypes.SCULK_SOUL, player.getX(), player.getY() + 1.0, player.getZ(),
                    25, 0.6, 0.6, 0.6, 0.02);
        }

        return InteractionResultHolder.success(stack);
    }

    private static void handleSoulBrand(ItemStack stack, Player player, LivingEntity target) {
        Level level = player.level();
        long now = level.getGameTime();

        cleanup(now);

        UUID id = target.getUUID();
        Long expires = BRANDED.get(id);

        if (expires != null && expires >= now) {
            BRANDED.remove(id);

            target.hurt(player.damageSources().magic(), DETONATE_DAMAGE);
            player.heal(DETONATE_HEAL);

            target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 40, 0, true, false, false));
            target.addEffect(new MobEffectInstance(MobEffects.WITHER, 60, 0, true, false, false));

            if (level instanceof ServerLevel serverLevel) {
                serverLevel.sendParticles(ParticleTypes.SCULK_SOUL,
                        target.getX(), target.getY() + 1.0, target.getZ(),
                        18, 0.35, 0.45, 0.35, 0.02);
            }

            return;
        }

        RandomSource rng = player.getRandom();
        if (rng.nextFloat() > BRAND_CHANCE) return;

        BRANDED.put(id, now + BRAND_DURATION);

        target.addEffect(new MobEffectInstance(MobEffects.GLOWING, BRAND_DURATION, 0, true, false, false));
        target.addEffect(new MobEffectInstance(MobEffects.WITHER, 60, 0, true, false, false));

        if (level instanceof ServerLevel serverLevel) {
            serverLevel.sendParticles(ParticleTypes.SOUL,
                    target.getX(), target.getY() + 1.0, target.getZ(),
                    10, 0.25, 0.35, 0.25, 0.02);
        }
    }

    private static void cleanup(long now) {
        Iterator<Map.Entry<UUID, Long>> it = BRANDED.entrySet().iterator();
        while (it.hasNext()) {
            if (it.next().getValue() < now) it.remove();
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext ctx, List<Component> list, TooltipFlag flag) {
        list.add(Component.translatable("item.battlegear.soul_blade.tooltip").withStyle(ChatFormatting.DARK_AQUA));
        list.add(Component.translatable("tooltip.battlegear.soul_blade.line1").withStyle(ChatFormatting.GRAY));
        list.add(Component.translatable("tooltip.battlegear.soul_blade.line2").withStyle(ChatFormatting.GRAY));
    }
}