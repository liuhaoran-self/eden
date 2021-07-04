package xyz.cbfh.eden;

import net.minecraft.item.*;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Rarity;

public class Edenium extends Item{
	public static Settings EDENIUM = new Item.Settings().group(Main.EDEN_THINGS).rarity(Rarity.EPIC).fireproof();
	public Edenium(Settings settings) {
		super(settings);
	}

	public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
		entity.playSound(SoundEvents.ENTITY_ELDER_GUARDIAN_CURSE, 1.0F, 1.0F);
		return ActionResult.PASS;
	}
	
}