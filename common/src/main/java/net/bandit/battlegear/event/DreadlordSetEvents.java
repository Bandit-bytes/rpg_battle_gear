package net.bandit.battlegear.event;

import dev.architectury.event.EventResult;
import dev.architectury.event.events.common.EntityEvent;
import net.bandit.battlegear.config.BattleGearConfig;
import net.bandit.battlegear.item.armor.DreadlordArmorItem;
import net.bandit.battlegear.registry.ItemRegistry;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;

public final class DreadlordSetEvents {

    private DreadlordSetEvents() {}

    // Tuning (feel free to tweak)
    private static final float PROC_CHANCE = 0.22F;
    private static final int COOLDOWN_TICKS = 45;
    private static final float BONUS_MAGIC_DAMAGE = 2.0F;
    private static final float HEAL_AMOUNT = 2.0F;

    public static void init() {
        EntityEvent.LIVING_HURT.register((target, source, amount) -> {
            handle(target, source);
            return EventResult.pass();
        });
    }

    private static void handle(LivingEntity target, DamageSource source) {
        Entity attacker = source.getEntity();
        if (!(attacker instanceof Player player)) return;
        if (player.level().isClientSide) return;

        if (!BattleGearConfig.ENABLE_DREADLORD_SET_BONUS) return;
        if (!BattleGearConfig.ENABLE_DREADLORD_BLOOD_PACT) return;
        if (!DreadlordArmorItem.hasFullSet(player)) return;

        Item cdKey = ItemRegistry.DREADLORD_HELMET.get();
        if (player.getCooldowns().isOnCooldown(cdKey)) return;

        RandomSource rng = player.getRandom();
        if (rng.nextFloat() > PROC_CHANCE) return;

        player.getCooldowns().addCooldown(cdKey, COOLDOWN_TICKS);

        target.hurt(player.damageSources().magic(), BONUS_MAGIC_DAMAGE);
        player.heal(HEAL_AMOUNT);

        target.addEffect(new MobEffectInstance(MobEffects.WITHER, 60, 0, true, false, false));   // 3s Wither I
        target.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 60, 0, true, false, false)); // 3s Weakness I
    }
}