package fr.outadoc.FantaBobMod.common;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemRecord;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemRecordFantaBob extends ItemRecord 
{
	public final String authorName;

	protected ItemRecordFantaBob(int i, String song, String author) 
	{
		super(i, song);
		this.authorName = author;
		this.setCreativeTab(CreativeTabs.tabMisc);
	}

	public void addInformation(ItemStack itemstack, List list)
	{
		list.add((new StringBuilder()).append(authorName).append(" - ").append(recordName).toString());
	}
	
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        itemIcon = par1IconRegister.registerIcon("FantaBobMod:magabond_record");
    }
}
