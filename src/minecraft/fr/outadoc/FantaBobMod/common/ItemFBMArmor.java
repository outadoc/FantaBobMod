package fr.outadoc.FantaBobMod.common;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.IArmorTextureProvider;

public class ItemFBMArmor extends ItemArmor
{
	public ItemFBMArmor(int par1, EnumArmorMaterial par2EnumArmorMaterial, int par3, int par4) 
	{
		super(par1, par2EnumArmorMaterial, par3, par4);
		setCreativeTab(CreativeTabs.tabDecorations);
	}
	
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, int layer)
    {
    	if(stack.itemID == FantaBobMod.fantaGlasses.itemID || stack.itemID == FantaBobMod.cobbleTie.itemID)
    	{
    		return "/mods/FantaBobMod/Textures/Armor/fantabob_1.png";
    	}
    	else if(stack.itemID == FantaBobMod.hairPotion.itemID)
    	{
    		return "/mods/FantaBobMod/Textures/Armor/fantabob_2.png";
    	}
    	else
    	{
            return "error";
    	}
    }
    
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        itemIcon = par1IconRegister.registerIcon("FantaBobMod:" + getUnlocalizedName().substring(5));
    }

}
