package net.bandit.battlegear.fabric.emi;

import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.render.EmiTexture;
import dev.emi.emi.api.stack.EmiStack;
import net.bandit.battlegear.BattleGearMod;
import net.bandit.battlegear.registry.BlockRegistry;
import net.minecraft.resources.ResourceLocation;

public class InfusionAltarEmiCategory {


    public static final ResourceLocation ANVIL_TEX =
            ResourceLocation.withDefaultNamespace("textures/gui/container/anvil.png");

    public static final EmiStack WORKSTATION =
            EmiStack.of(BlockRegistry.INFUSION_ALTAR.get());

    public static final ResourceLocation ID =
            ResourceLocation.fromNamespaceAndPath(BattleGearMod.MOD_ID, "infusion_altar");

    public static final EmiRecipeCategory CATEGORY =
            new EmiRecipeCategory(ID, WORKSTATION, new EmiTexture(ANVIL_TEX, 0, 0, 16, 16));

    private InfusionAltarEmiCategory() {}
}