package xyz.cbfh.eden.Tools;

import java.util.UUID;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.thrown.EnderPearlEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.network.MessageType;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.PlayerManager;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.Util;
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
     public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        world.playSound((PlayerEntity)null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_ENDER_PEARL_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (RANDOM.nextFloat() * 0.4F + 0.8F));
        //user.getItemCooldownManager().set(this, 20);
        if (!world.isClient) {
           EnderPearlEntity enderPearlEntity = new EnderPearlEntity(world, user);
           enderPearlEntity.setItem(itemStack);
           enderPearlEntity.setProperties(user, user.pitch, user.yaw, 0.0F, 1.5F, 1.0F);
           world.spawnEntity(enderPearlEntity);
        }
  
        user.incrementStat(Stats.USED.getOrCreateStat(this));
  
        return TypedActionResult.success(itemStack, world.isClient());
     }
}