package fr.outadoc.FantaBobMod.common;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemStampCollection extends Item
{

	public ItemStampCollection(int par1) 
	{
		super(par1);
		setCreativeTab(CreativeTabs.tabDecorations);
	}
	
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        itemIcon = par1IconRegister.registerIcon("FantaBobMod:stamp_collection");
    }

}
