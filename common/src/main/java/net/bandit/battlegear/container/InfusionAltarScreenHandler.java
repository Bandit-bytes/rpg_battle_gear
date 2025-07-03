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

        for (SimpleInfusionRecipe recipe : getInfusionRecipes()) {
            if (recipe.matches(input1, input2)) {
                int crafts = Math.min(
                        input1.getCount() / recipe.getRequiredCount1(),
                        input2.getCount() / recipe.getRequiredCount2()
                );

                if (crafts > 0) {
                    ItemStack result = recipe.getResult().copy();
                    result.setCount(result.getCount() * crafts);
                    this.resultSlots.setItem(0, result);
                }
                return;
            }
        }
    }

    public static List<SimpleInfusionRecipe> getInfusionRecipes() {
        return Arrays.asList(
                new SimpleInfusionRecipe(new ItemStack(Items.ECHO_SHARD), new ItemStack(Items.PHANTOM_MEMBRANE), new ItemStack(BattleGearItems.SACRED_GEM.get()), 1, 1),
                new SimpleInfusionRecipe(new ItemStack(Items.DIAMOND), new ItemStack(Items.NETHERITE_INGOT), new ItemStack(BattleGearItems.ANCIENT_CORE.get()), 1, 1),
                new SimpleInfusionRecipe(new ItemStack(Items.BLAZE_ROD), new ItemStack(Items.GHAST_TEAR), new ItemStack(BattleGearItems.MYSTIC_ESSENCE.get()), 1, 1),
                new SimpleInfusionRecipe(new ItemStack(Items.DIAMOND), new ItemStack(Items.GOLD_INGOT), new ItemStack(BattleGearItems.TEMPLAR_EMBLEM.get()), 1, 1),
                new SimpleInfusionRecipe(new ItemStack(Items.GOLDEN_APPLE), new ItemStack(Items.GHAST_TEAR), new ItemStack(BattleGearItems.GUARDIAN_HEART.get()), 1, 1)
        );
    }

}
