package net.minecraft.src;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemObsidianToothBrush extends Item
{
	protected ItemObsidianToothBrush(int i) 
	{
		super(i);
		this.setCreativeTab(CreativeTabs.tabMisc);
	}

	public ItemStack onItemRightClick(ItemStack itemstack, World world,	EntityPlayer entityplayer) 
	{
		world.playSoundAtEntity(entityplayer, "fantabob.toothbrush", 1, 1);
		ModLoader.getMinecraftInstance().thePlayer.triggerAchievement(mod_FantaBob.brushTeethAch);
		return itemstack;
	}
}
