package net.bandit.battlegear.event;

import dev.architectury.event.EventResult;
import dev.architectury.event.events.common.EntityEvent;
import net.bandit.battlegear.config.BattleGearConfig;
import net.bandit.battlegear.item.armor.SovereignArmorItem;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

public final class SovereignSetEvents {

    private static final float MARK_CHANCE = 0.20F;
    private static final int MARK_DURATION = 80;
    private static final float EXECUTE_DAMAGE = 5.0F;

    private static final Map<UUID, Long> MARKED = new HashMap<>();

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

        if (!BattleGearConfig.ENABLE_SOVEREIGN_SET_BONUS) return;
        if (!BattleGearConfig.ENABLE_SOVEREIGN_SMITE) return;
        if (!SovereignArmorItem.hasFullSet(player)) return;

        long gameTime = player.level().getGameTime();
        UUID id = target.getUUID();

        cleanup(gameTime);

        if (MARKED.containsKey(id)) {
            MARKED.remove(id);

            target.hurt(player.damageSources().magic(), EXECUTE_DAMAGE);

            player.level().getEntitiesOfClass(
                    LivingEntity.class,
                    target.getBoundingBox().inflate(3)
            ).forEach(e -> {
                if (e != target && e != player) {
                    e.hurt(player.damageSources().magic(), 2.0F);
                }
            });

            return;
        }

        RandomSource rng = player.getRandom();
        if (rng.nextFloat() > MARK_CHANCE) return;

        MARKED.put(id, gameTime + MARK_DURATION);
        target.addEffect(new MobEffectInstance(MobEffects.GLOWING, MARK_DURATION));
    }

    private static void cleanup(long currentTick) {
        Iterator<Map.Entry<UUID, Long>> it = MARKED.entrySet().iterator();
        while (it.hasNext()) {
            if (it.next().getValue() < currentTick) {
                it.remove();
            }
        }
    }
}