package net.bandit.battlegear.item;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class StormreaverSwordItem extends SwordItem {


    private static final float STORM_CHAIN_CHANCE = 0.25F;
    private static final int STORM_CHAIN_COOLDOWN = 30;
    private static final float BONUS_HIT_DAMAGE = 2.5F;
    private static final float CHAIN_DAMAGE = 1.5F;
    private static final double CHAIN_RANGE = 4.0D;
    private static final int SPEED_TICKS = 50;

    private static final int SURGE_COOLDOWN = 80;
    private static final double SURGE_RANGE = 5.0D;
    private static final float SURGE_DAMAGE = 2.0F;
    private static final int SURGE_DURABILITY_COST = 6;

    public StormreaverSwordItem(Tier tier, Properties properties) {
        super(tier, properties);
    }


    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (attacker instanceof Player player && !player.level().isClientSide) {
            tryStormChain(stack, player, target);
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

        player.getCooldowns().addCooldown(this, SURGE_COOLDOWN);
        stack.hurtAndBreak(SURGE_DURABILITY_COST, player,
                LivingEntity.getSlotForHand(hand));

        AABB box = player.getBoundingBox().inflate(SURGE_RANGE);
        List<LivingEntity> victims = level.getEntitiesOfClass(LivingEntity.class, box, e -> e != player && e.isAlive());

        if (!victims.isEmpty()) {
            spawnVisualLightning((ServerLevel) level, player.blockPosition(), player);

            for (LivingEntity e : victims) {
                e.hurt(player.damageSources().magic(), SURGE_DAMAGE);
                e.addEffect(new MobEffectInstance(MobEffects.GLOWING, 40, 0, true, false, false));
                e.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 40, 0, true, false, false));
            }
        }

        return InteractionResultHolder.success(stack);
    }

    private void tryStormChain(ItemStack stack, Player player, LivingEntity target) {

        if (player.getCooldowns().isOnCooldown(this)) return;

        float chance = STORM_CHAIN_CHANCE;
        if (target.isInWaterRainOrBubble()) {
            chance += 0.10F;
        }

        RandomSource rng = player.getRandom();
        if (rng.nextFloat() > chance) return;

        player.getCooldowns().addCooldown(this, STORM_CHAIN_COOLDOWN);

        Level level = player.level();
        if (!(level instanceof ServerLevel serverLevel)) return;

        target.hurt(player.damageSources().magic(), BONUS_HIT_DAMAGE);
        spawnVisualLightning(serverLevel, target.blockPosition(), player);

        AABB box = target.getBoundingBox().inflate(CHAIN_RANGE);
        List<LivingEntity> nearby = level.getEntitiesOfClass(LivingEntity.class, box,
                e -> e != player && e != target && e.isAlive());

        for (LivingEntity e : nearby) {
            e.hurt(player.damageSources().magic(), CHAIN_DAMAGE);
            e.addEffect(new MobEffectInstance(MobEffects.GLOWING, 40, 0, true, false, false));
        }

        player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, SPEED_TICKS, 0, true, false, false));
    }

    private void spawnVisualLightning(ServerLevel level, BlockPos pos, Player cause) {
        LightningBolt bolt = EntityType.LIGHTNING_BOLT.create(level);
        if (bolt == null) return;

        bolt.moveTo(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D);
        bolt.setVisualOnly(true);

        if (cause instanceof ServerPlayer sp) {
            bolt.setCause(sp);
        }

        level.addFreshEntity(bolt);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext ctx, List<Component> list, TooltipFlag flag) {
        list.add(Component.translatable("item.battlegear.stormreaver.tooltip").withStyle(ChatFormatting.AQUA));
        list.add(Component.translatable("tooltip.battlegear.stormreaver.line1").withStyle(ChatFormatting.GRAY));
        list.add(Component.translatable("tooltip.battlegear.stormreaver.line2").withStyle(ChatFormatting.GRAY));
    }
}