package net.bandit.battlegear.container;

import net.bandit.battlegear.recipe.InfusionAltarRecipe;
import net.bandit.battlegear.recipe.InfusionInput;
import net.bandit.battlegear.registry.MenuRegistries;
import net.bandit.battlegear.registry.RecipeRegistries;
import net.minecraft.client.Minecraft;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;

import java.util.Optional;

public class InfusionAltarScreenHandler extends AbstractContainerMenu {

    private final ContainerLevelAccess access;
    private final Level level;

    private final SimpleContainer input = new SimpleContainer(2) {
        @Override
        public void setChanged() {
            super.setChanged();
            InfusionAltarScreenHandler.this.slotsChanged(this);
        }
    };

    private final SimpleContainer result = new SimpleContainer(1);

    private RecipeHolder<InfusionAltarRecipe> matched = null;
    private InfusionAltarRecipe.Match matchType = InfusionAltarRecipe.Match.NONE;

    private static final int INPUT_0 = 0;
    private static final int INPUT_1 = 1;
    private static final int RESULT_SLOT = 2;

    private static final int PLAYER_INV_START = 3;
    private static final int PLAYER_INV_END = PLAYER_INV_START + 27;
    private static final int HOTBAR_START = PLAYER_INV_END;
    private static final int HOTBAR_END = HOTBAR_START + 9;

    public InfusionAltarScreenHandler(int syncId, Inventory playerInventory) {
        this(syncId, playerInventory, ContainerLevelAccess.NULL);
    }

    public InfusionAltarScreenHandler(int syncId, Inventory playerInventory, ContainerLevelAccess access) {
        super(MenuRegistries.INFUSION_ALTAR_MENU.get(), syncId);
        this.access = access;
        this.level = playerInventory.player.level();

        this.addSlot(new Slot(input, 0, 27, 47));
        this.addSlot(new Slot(input, 1, 76, 47));
        this.addSlot(new ResultSlot(result, 0, 134, 47));

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                this.addSlot(new Slot(playerInventory, col + row * 9 + 9, 8 + col * 18, 84 + row * 18));
            }
        }
        for (int col = 0; col < 9; col++) {
            this.addSlot(new Slot(playerInventory, col, 8 + col * 18, 142));
        }
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }

    @Override
    public void slotsChanged(Container container) {
        super.slotsChanged(container);
        recalcResult();
    }

    private void recalcResult() {
        result.setItem(0, ItemStack.EMPTY);
        matched = null;
        matchType = InfusionAltarRecipe.Match.NONE;

        ItemStack a = input.getItem(0);
        ItemStack b = input.getItem(1);
        if (a.isEmpty() || b.isEmpty()) return;

        Optional<RecipeHolder<InfusionAltarRecipe>> opt =
                level.getRecipeManager().getRecipeFor(RecipeRegistries.INFUSION_TYPE.get(), new InfusionInput(a, b), level);

        if (opt.isEmpty()) return;

        RecipeHolder<InfusionAltarRecipe> holder = opt.get();
        InfusionAltarRecipe recipe = holder.value();

        InfusionAltarRecipe.Match mt = recipe.match(new InfusionInput(a, b));
        if (mt == InfusionAltarRecipe.Match.NONE) return;

        int neededA = (mt == InfusionAltarRecipe.Match.NORMAL) ? recipe.count1() : recipe.count2();
        int neededB = (mt == InfusionAltarRecipe.Match.NORMAL) ? recipe.count2() : recipe.count1();

        int crafts = Math.min(a.getCount() / neededA, b.getCount() / neededB);
        if (crafts <= 0) return;

        ItemStack out = recipe.resultStack();
        int perCraft = Math.max(1, out.getCount());
        int total = perCraft * crafts;
        out.setCount(Math.min(total, out.getMaxStackSize()));

        result.setItem(0, out);
        matched = holder;
        matchType = mt;
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        Slot slot = this.slots.get(index);
        if (slot == null || !slot.hasItem()) return ItemStack.EMPTY;

        ItemStack stackInSlot = slot.getItem();
        ItemStack original = stackInSlot.copy();

        if (index == RESULT_SLOT) {
            ItemStack takenCopy = stackInSlot.copy();

            if (!this.moveItemStackTo(stackInSlot, PLAYER_INV_START, HOTBAR_END, true)) {
                return ItemStack.EMPTY;
            }

            slot.onTake(player, takenCopy);
            return original;
        }

        if (index >= PLAYER_INV_START) {
            if (!this.moveItemStackTo(stackInSlot, INPUT_0, INPUT_1 + 1, false)) {
                return ItemStack.EMPTY;
            }
        } else {
            if (!this.moveItemStackTo(stackInSlot, PLAYER_INV_START, HOTBAR_END, true)) {
                return ItemStack.EMPTY;
            }
        }

        if (stackInSlot.isEmpty()) slot.set(ItemStack.EMPTY);
        else slot.setChanged();

        return original;
    }

    private class ResultSlot extends Slot {
        public ResultSlot(Container container, int slot, int x, int y) {
            super(container, slot, x, y);
        }

        @Override
        public boolean mayPlace(ItemStack stack) {
            return false;
        }

        @Override
        public boolean mayPickup(Player player) {
            return matched != null && this.hasItem();
        }

        @Override
        public void onTake(Player player, ItemStack taken) {
            if (!player.level().isClientSide && matched != null && !taken.isEmpty()) {
                InfusionAltarRecipe recipe = matched.value();

                int outPerCraft = Math.max(1, recipe.resultStack().getCount());
                int craftsTaken = Math.max(1, taken.getCount() / outPerCraft);

                int consumeA = (matchType == InfusionAltarRecipe.Match.NORMAL) ? recipe.count1() : recipe.count2();
                int consumeB = (matchType == InfusionAltarRecipe.Match.NORMAL) ? recipe.count2() : recipe.count1();

                ItemStack a = input.getItem(0);
                ItemStack b = input.getItem(1);

                a.shrink(consumeA * craftsTaken);
                b.shrink(consumeB * craftsTaken);

                if (a.isEmpty()) input.setItem(0, ItemStack.EMPTY);
                if (b.isEmpty()) input.setItem(1, ItemStack.EMPTY);

                input.setChanged();
                result.setChanged();
                recalcResult();
            }
            super.onTake(player, taken);
        }
    }
}