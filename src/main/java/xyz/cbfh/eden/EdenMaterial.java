package xyz.cbfh.eden;

import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public class EdenMaterial implements ToolMaterial {
    @Override
    public int getDurability() {
        return 1000;//耐久值
    }

    @Override
    public float getAttackDamage() {
        return 7.0f;//伤害值
    }

    @Override
    public int getMiningLevel() {
        return 4;//挖掘级别4
    }

    @Override
    public int getEnchantability() {
        return 30;//附魔等级30
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(Main.EDEN_MATERIAL);//修复配方，可选
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return 0;
    }
}