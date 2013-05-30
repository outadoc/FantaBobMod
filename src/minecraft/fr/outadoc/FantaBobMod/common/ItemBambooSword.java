package fr.outadoc.FantaBobMod.common;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemBambooSword extends ItemSword
{

	public ItemBambooSword(int par1, EnumToolMaterial par2EnumToolMaterial)
	{
		super(par1, par2EnumToolMaterial);
		setCreativeTab(CreativeTabs.tabCombat);
	}
	
    public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack)
    {
        return true;
    }
    
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        itemIcon = par1IconRegister.registerIcon("FantaBobMod:bamboo_sword");
    }

}
