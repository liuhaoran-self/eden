package xyz.cbfh.eden;

import java.lang.Override;
import net.minecraft.item.*;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class EdenItem extends Item{

	public EdenItem(Settings settings) {
		super(settings);
	}

	public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
		entity.playSound(SoundEvents.ENTITY_CHICKEN_AMBIENT, 1.0F, 1.0F);
		entity.age++;
		return ActionResult.PASS;
	}
	
}