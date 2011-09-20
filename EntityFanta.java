package net.minecraft.src;
 
public class EntityFanta extends EntityFBMob
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
		EntityBob bob = null;
       
		for(int i = 0; i < worldObj.loadedEntityList.size(); i++)
		{
			Entity target = (Entity)worldObj.loadedEntityList.get(i);
			
			if(target instanceof EntityBob)
			{
				double distance = target.getDistance(posX, posY, posZ);
				if(distance <= detectionRadius && distance >= rejoinRadius && ((EntityBob)target).canEntityBeSeen(this))
				{
					bob = (EntityBob)target;
					
					if(bob.isFollowed())
						bob = null;
					else
						bob.setFollowed(true);
				}
			}
		}

		return bob;
	}
	
	public void onLivingUpdate() 
	{
		super.onLivingUpdate();
		if(entityToAttack != null)
		{	
			double distance = entityToAttack.getDistance(posX, posY, posZ);
			if(distance < proximityRadius)
			{
				((EntityBob)entityToAttack).setFollowed(false);
				entityToAttack = null;
			}
		}
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
	
	public int getMaxSpawnedInChunk() 
	{
		return 1;
	}
	
    protected void dropFewItems()
    {
        int i = getDropItemId();
        dropItem(i, 1);
    }
    
    public void playLivingSound()
    {
        String s = getLivingSound();
        if(s != null)
        {
            worldObj.playSoundAtEntity(this, s, getSoundVolume(), 1.0F);
        }
    }
    
    public void onDeath(DamageSource damagesource)
    {
    	if(entityToAttack != null)
    	{
    		((EntityBob)entityToAttack).setFollowed(false);
    	}
    	super.onDeath(damagesource);
    }
    
    public Achievement getKillAch()
    {
    	return mod_FantaBob.killFantaAch;
    }
    
    private static final double proximityRadius = 3;
	private static final double detectionRadius = 16;
	private static final double rejoinRadius    = 5;
}
