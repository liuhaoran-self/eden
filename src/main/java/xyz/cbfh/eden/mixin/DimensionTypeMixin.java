package xyz.cbfh.eden.mixin;

import net.minecraft.world.dimension.DimensionType;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(DimensionType.class) // 只有被@Mixin修饰的类才会被识别并注入
public class DimensionTypeMixin {
    /**
     * @author TuRou
     * @reason Make water can place at all dimensions.
     */
    @Overwrite() // 将目标方法完全替换为下方的代码
    public boolean isUltrawarm() {
        return false; // 所有维度都返回false, 包括地狱
    }
}