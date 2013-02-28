package net.minecraft.src;

import java.util.List;

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
}
