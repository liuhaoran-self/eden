package xyz.cbfh.eden.Tools;

import java.util.function.Consumer;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import xyz.cbfh.eden.*;

public class Sword extends SwordItem {
    public Sword(ToolMaterial material) {
        super(material, 0, +0.0f, Edenium.EDENIUM);//伤害值相较ToolFabric中的4-1=3，攻击速度-2.2，设置在fabric_example_group中
    }
    
    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        stack.damage(1, (LivingEntity)attacker, ((e) -> {
           e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND);
        }));
        attacker.addStatusEffect(new StatusEffectInstance(StatusEffects.INVISIBILITY,60,1,true,true,true));
        if(target.isAlive()){ 
            target.playSound(SoundEvents.BLOCK_ANVIL_LAND, 1.0F, 1.0F);
            target.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS,50,255));
            target.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS,50,1));
            target.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS,50,2));
            target.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING,50,1));
        }
        return true;
     }
     @Override
     public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        if (user instanceof PlayerEntity) {
            PlayerEntity playerEntity = (PlayerEntity) user;
            if (true) {
                if (!world.isClient) {
                    stack.damage(1, (LivingEntity) playerEntity, (Consumer) ((p) -> {
                        ((LivingEntity) p).sendToolBreakStatus(user.getActiveHand());
                    }));
                }
                float f = playerEntity.yaw;
                float g = playerEntity.pitch;
                float h = -MathHelper.sin(f * 0.017453292F) * MathHelper.cos(g * 0.017453292F);
                float k = -MathHelper.sin(g * 0.017453292F);
                float l = MathHelper.cos(f * 0.017453292F) * MathHelper.cos(g * 0.017453292F);
                float m = MathHelper.sqrt(h * h + k * k + l * l);
                float n = 3.0F;
                h *= n / m;
                k *= n / m;
                l *= n / m;
                playerEntity.addVelocity((double) h, (double) k, (double) l);
                playerEntity.setRiptideTicks(20);
                if (playerEntity.isOnGround()) {
                    playerEntity.move(MovementType.SELF, new Vec3d(0.0D, 1.1999999284744263D, 0.0D));
                }
                world.playSoundFromEntity((PlayerEntity) null, playerEntity, SoundEvents.ITEM_TRIDENT_RIPTIDE_1, SoundCategory.PLAYERS,
                        1.0F, 1.0F);
            }
        }
    }
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        user.setCurrentHand(hand);
        return TypedActionResult.consume(itemStack);
    }
    @Override
    public int getMaxUseTime(ItemStack stack) {
        return 72000;
    }
}