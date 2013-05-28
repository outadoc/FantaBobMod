package fr.outadoc.FantaBobMod.common;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.ICraftingHandler;

public class CraftingHandler implements ICraftingHandler
{
        @Override
        public void onCrafting(EntityPlayer player, ItemStack item, IInventory craftMatrix)
        {
    		if (item.itemID == FantaBobMod.magabondRecord.itemID)
    			player.triggerAchievement(FantaBobMod.craftMagabondRecAch);
    		else if (item.itemID == FantaBobMod.bouse.blockID)
    			player.triggerAchievement(FantaBobMod.getBouseAch);
    		else if (item.itemID == FantaBobMod.hairPotion.itemID)
    			player.triggerAchievement(FantaBobMod.getHairPotionAch);
    		else if (item.itemID == FantaBobMod.bambooSword.itemID)
    			player.triggerAchievement(FantaBobMod.getBambooSwordAch);
        }
        
        @Override
        public void onSmelting(EntityPlayer player, ItemStack item)
        {
        	
        }
}
