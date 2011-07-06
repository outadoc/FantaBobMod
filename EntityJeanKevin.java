package net.minecraft.src;

public class EntityJeanKevin extends EntityCreature 
{
	public EntityJeanKevin(World world) 
	{
		super(world);
		texture = "/mob/char.png";
	}
	
	protected int getDropItemId()
	{
		return mod_FantaBob.magabondChop.shiftedIndex;
	}
	
	public ItemStack getHeldItem()
	{
		return (new ItemStack(Block.dirt));
	}
    
    protected Entity findPlayerToAttack()
    {
        double d1 = -1D;
        EntityPlayer player = null;
        
        for(int i = 0; i < worldObj.loadedEntityList.size(); i++)
        {
            Entity entity1 = (Entity)worldObj.loadedEntityList.get(i);
            
            if(!(entity1 instanceof EntityPlayer))
            {
                continue;
            }
            
            double d2 = entity1.getDistance(posX, posY, posZ);
            if((d2 < 16) && (d1 == -1D || d2 < d1) && ((EntityPlayer)entity1).canEntityBeSeen(this))
            {
                d1 = d2;
                player = (EntityPlayer)entity1;
            }
        }

        return player;
    }
    
    protected String getHurtSound() 
    {
 	   return "fantabob.kevinhurt";
    }
    
 	protected String getDeathSound() 
 	{
 		return "fantabob.kevindeath";
 	}
 	
 	protected String getLivingSound() 
 	{
 		return "fantabob.kevin";
 	}
}
