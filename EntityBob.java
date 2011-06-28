package net.minecraft.src;
 
public class EntityBob extends EntityCreature
{
	public EntityBob(World world)
	{
		super(world);
		texture = "/fantabob/bob.png";
		attackStrength = 4;
	}
 
	protected int getDropItemId()
	{
		return Block.cobblestone.blockID;
	}
   
	public ItemStack getHeldItem()
	{
		return (new ItemStack(Item.flintAndSteel));
	}
	
	protected Entity findPlayerToAttack()
	{
		double d1 = -1D;
		EntityJeanKevin kevin = null;
       
		for(int i = 0; i < worldObj.loadedEntityList.size(); i++)
		{
			Entity entity1 = (Entity)worldObj.loadedEntityList.get(i);
           
			if(!(entity1 instanceof EntityJeanKevin))
			{
				continue;
			}
           
			double d2 = entity1.getDistance(posX, posY, posZ);
			if((d2 < 16) && (d1 == -1D || d2 < d1) && ((EntityJeanKevin)entity1).canEntityBeSeen(this))
			{
				d1 = d2;
				kevin = (EntityJeanKevin)entity1;
			}
		}
		return kevin;
	}
   
	protected void attackEntity(Entity entity, float f)
	{
		if(attackTime <= 0 && f < 2.0F && entity.boundingBox.maxY > boundingBox.minY && entity.boundingBox.minY < boundingBox.maxY)
		{
			attackTime = 20;
			entity.attackEntityFrom(this, attackStrength);
		}
	}

	protected String getHurtSound() 
	{
		return "fantabob.bobhurt";
	}
   
	protected String getDeathSound() 
	{
		return "fantabob.bobdeath";
	}
	
	protected String getLivingSound() 
	{
		return "fantabob.bob";
	}

	protected int attackStrength;
}
