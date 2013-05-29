package fr.outadoc.FantaBobMod.common;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.IPickupNotifier;

public class PickupHandler implements IPickupNotifier 
{

	@Override
	public void notifyPickup(EntityItem item, EntityPlayer player) 
	{
        ItemStack itemstack = item.getEntityItem();
		if (itemstack.itemID == FantaBobMod.fantaGlasses.itemID)
		{
			player.triggerAchievement(FantaBobMod.getFantaGlassesAch);
		}
		else if (itemstack.itemID == FantaBobMod.magabondChop.itemID)
		{
			player.triggerAchievement(FantaBobMod.getMagabondChopAch);
		}

	}

}
