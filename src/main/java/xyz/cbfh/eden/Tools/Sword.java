package xyz.cbfh.eden.Tools;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Rarity;
import xyz.cbfh.eden.Main;

public class Sword extends SwordItem {

    public Sword(ToolMaterial material) {
        super(material, 0, -3.9f, new Item.Settings().group(Main.EDEN_THINGS).rarity(Rarity.EPIC));//伤害值相较ToolFabric中的4-1=3，攻击速度-2.2，设置在fabric_example_group中
    }
    
    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        stack.damage(1, (LivingEntity)attacker, ((e) -> {
           e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND);
        }));
        if(target.isDead())target.playSound(SoundEvents.ENTITY_WITHER_DEATH, 1.0F, 1.0F);
        return true;
     }
    // public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
    //     return TypedActionResult.success(playerEntity.getStackInHand(hand));
    // }
}