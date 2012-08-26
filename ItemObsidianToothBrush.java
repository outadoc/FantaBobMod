package net.minecraft.src;

public class ItemObsidianToothBrush extends Item
{
	protected ItemObsidianToothBrush(int i) 
	{
		super(i);
		this.setTabToDisplayOn(CreativeTabs.tabMisc);
	}

	public ItemStack onItemRightClick(ItemStack itemstack, World world,	EntityPlayer entityplayer) 
	{
		world.playSoundAtEntity(entityplayer, "fantabob.toothbrush", 1, 1);
		ModLoader.getMinecraftInstance().thePlayer.triggerAchievement(mod_FantaBob.brushTeethAch);
		return itemstack;
	}
}
