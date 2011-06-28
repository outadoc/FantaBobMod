package net.minecraft.src;
 
public class EntityFanta extends EntityCreature
{
	public EntityFanta(World world)
	{
		super(world);
		texture = "/fantabob/fanta.png";
	}
 
	protected int getDropItemId()
	{
		return mod_FantaBob.fantaGlasses.shiftedIndex;
	}
   
	public ItemStack getHeldItem()
	{
		return (new ItemStack(Item.swordWood));
	}
   
	protected Entity findPlayerToAttack()
	{
		double d1 = -1D;
		EntityBob bob = null;
       
		for(int i = 0; i < worldObj.loadedEntityList.size(); i++)
		{
			Entity entity1 = (Entity)worldObj.loadedEntityList.get(i);
			
			if(!(entity1 instanceof EntityBob))
			{
				continue;
			}
           
			double d2 = entity1.getDistance(posX, posY, posZ);
			if((d2 < 16) && (d1 == -1D || d2 < d1) && ((EntityBob)entity1).canEntityBeSeen(this))
			{
				d1 = d2;
				bob = (EntityBob)entity1;
			}
		}

		return bob;
	}
   
	protected String getHurtSound() 
	{
		return "fantabob.fantahurt";
	}
   
	protected String getDeathSound() 
	{
		return "fantabob.fantadeath";
	}
	
	protected String getLivingSound() 
	{
		return "fantabob.fanta";
	}
}
