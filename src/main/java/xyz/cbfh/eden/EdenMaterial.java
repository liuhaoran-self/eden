package xyz.cbfh.eden;

import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public class EdenMaterial implements ToolMaterial {

    @Override
    public int getDurability() {
        return 10;//耐久值
    }

    @Override
    public float getAttackDamage() {
        return 29.0f;//伤害值
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
        return null;//修复配方，可选
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return 0;
    }
}