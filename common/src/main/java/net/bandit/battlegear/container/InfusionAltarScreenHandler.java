package net.bandit.battlegear.container;

import net.bandit.battlegear.registry.BattleGearItems;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AnvilMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.Arrays;
import java.util.List;

public class InfusionAltarScreenHandler extends AnvilMenu {

    public InfusionAltarScreenHandler(int syncId, Inventory playerInventory) {
        this(syncId, playerInventory, ContainerLevelAccess.NULL);
    }
    public InfusionAltarScreenHandler(int syncId, Inventory playerInventory, ContainerLevelAccess context) {
        super(syncId, playerInventory, context);
    }
    @Override
    public boolean stillValid(Player player) {
        return true;
    }

    @Override
    public boolean mayPickup(Player player, boolean present) {
        ItemStack resultStack = this.resultSlots.getItem(0);
        return !resultStack.isEmpty();
    }
    @Override
    public void slotsChanged(Container inventory) {
        this.resultSlots.setItem(0, ItemStack.EMPTY);

        ItemStack input1 = this.inputSlots.getItem(0);
        ItemStack input2 = this.inputSlots.getItem(1);

        if (input1.getItem() == Items.ECHO_SHARD && input2.getItem() == Items.PHANTOM_MEMBRANE) {
            this.resultSlots.setItem(0, new ItemStack(BattleGearItems.SACRED_GEM.get()));
        } else if (input1.getItem() == Items.DIAMOND && input2.getItem() == Items.NETHERITE_INGOT) {
            this.resultSlots.setItem(0, new ItemStack(BattleGearItems.ANCIENT_CORE.get()));
        } else if (input1.getItem() == Items.BLAZE_ROD && input2.getItem() == Items.GHAST_TEAR) {
            this.resultSlots.setItem(0, new ItemStack(BattleGearItems.MYSTIC_ESSENCE.get()));
        } else if (input1.getItem() == Items.DIAMOND && input2.getItem() == Items.GOLD_INGOT) {
            this.resultSlots.setItem(0, new ItemStack(BattleGearItems.TEMPLAR_EMBLEM.get()));
        } else if (input1.getItem() == Items.GOLDEN_APPLE && input2.getItem() == Items.GHAST_TEAR) {
            this.resultSlots.setItem(0, new ItemStack(BattleGearItems.GUARDIAN_HEART.get()));
        }
    }
    public static List<SimpleInfusionRecipe> getInfusionRecipes() {
        return Arrays.asList(
                new SimpleInfusionRecipe(new ItemStack(Items.ECHO_SHARD), new ItemStack(Items.PHANTOM_MEMBRANE), new ItemStack(BattleGearItems.SACRED_GEM.get())),
                new SimpleInfusionRecipe(new ItemStack(Items.DIAMOND), new ItemStack(Items.NETHERITE_INGOT), new ItemStack(BattleGearItems.ANCIENT_CORE.get())),
                new SimpleInfusionRecipe(new ItemStack(Items.BLAZE_ROD), new ItemStack(Items.GHAST_TEAR), new ItemStack(BattleGearItems.MYSTIC_ESSENCE.get())),
                new SimpleInfusionRecipe(new ItemStack(Items.DIAMOND), new ItemStack(Items.GOLD_INGOT), new ItemStack(BattleGearItems.TEMPLAR_EMBLEM.get())),
                new SimpleInfusionRecipe(new ItemStack(Items.GOLDEN_APPLE), new ItemStack(Items.GHAST_TEAR), new ItemStack(BattleGearItems.GUARDIAN_HEART.get()))
        );
    }
}
