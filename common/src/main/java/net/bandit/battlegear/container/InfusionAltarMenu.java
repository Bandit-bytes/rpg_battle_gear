package net.bandit.battlegear.container;

import net.bandit.battlegear.registry.BattleGearItems;
import net.bandit.battlegear.registry.MenuRegistries;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.Arrays;
import java.util.List;

public class InfusionAltarMenu extends AbstractContainerMenu {

    private final Container inputSlots = new SimpleContainer(2);
    private final Container resultSlots = new SimpleContainer(1);
    private final ContainerLevelAccess context;

    public InfusionAltarMenu(int syncId, Inventory playerInventory) {
        this(syncId, playerInventory, ContainerLevelAccess.NULL);
    }

    public InfusionAltarMenu(int syncId, Inventory playerInventory, ContainerLevelAccess context) {
        super(MenuRegistries.INFUSION_ALTAR_MENU.get(), syncId);
        this.context = context;

        // Input slots
        this.addSlot(new Slot(inputSlots, 0, 27, 47));
        this.addSlot(new Slot(inputSlots, 1, 76, 47));

        // Output slot
        this.addSlot(new Slot(resultSlots, 0, 134, 47) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return false;
            }

            @Override
            public void onTake(Player player, ItemStack stack) {
                shrinkInputs(stack);
                super.onTake(player, stack);
            }
        });

        // Player inventory slots
        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 9; ++col) {
                this.addSlot(new Slot(playerInventory, col + row * 9 + 9, 8 + col * 18, 84 + row * 18));
            }
        }

        for (int hotbar = 0; hotbar < 9; ++hotbar) {
            this.addSlot(new Slot(playerInventory, hotbar, 8 + hotbar * 18, 142));
        }
    }

    @Override
    public ItemStack quickMoveStack(Player player, int i) {
        return null;
    }

    @Override
    public void slotsChanged(Container container) {
        super.slotsChanged(container);
        updateOutput();
    }

    private void updateOutput() {
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

    private void shrinkInputs(ItemStack resultStack) {
        ItemStack input1 = this.inputSlots.getItem(0);
        ItemStack input2 = this.inputSlots.getItem(1);

        for (SimpleInfusionRecipe recipe : getInfusionRecipes()) {
            if (recipe.matches(input1, input2)) {
                int crafts = resultStack.getCount() / recipe.getResult().getCount();
                input1.shrink(recipe.getRequiredCount1() * crafts);
                input2.shrink(recipe.getRequiredCount2() * crafts);
                break;
            }
        }

        this.slotsChanged(this.inputSlots);
    }

    @Override
    public boolean stillValid(Player player) {
        return true; // Optionally use context.stillValid()
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
