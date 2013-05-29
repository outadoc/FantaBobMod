package fr.outadoc.FantaBobMod.common;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemFood;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemFBMFood extends ItemFood
{

	public ItemFBMFood(int par1, int par2, float par3, boolean par4) 
	{
		super(par1, par2, par3, par4);
		setMaxStackSize(64);
		setCreativeTab(CreativeTabs.tabFood);
	}
	
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        itemIcon = par1IconRegister.registerIcon("FantaBobMod:magabond_chop");
    }

}
