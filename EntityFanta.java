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
    
    public boolean attackEntityFrom(Entity entity, int i) 
    {
        if(worldObj.multiplayerWorld)
        {
            return false;
        }
        entityAge = 0;
        if(health <= 0)
        {
            return false;
        }
        field_704_R = 1.5F;
        boolean flag = true;
        if((float)heartsLife > (float)heartsHalvesLife / 2.0F)
        {
            if(i <= field_9346_af)
            {
                return false;
            }
            damageEntity(i - field_9346_af);
            field_9346_af = i;
            flag = false;
        } else
        {
            field_9346_af = i;
            prevHealth = health;
            heartsLife = heartsHalvesLife;
            damageEntity(i);
            hurtTime = maxHurtTime = 10;
        }
        attackedAtYaw = 0.0F;
        if(flag)
        {
            worldObj.func_9425_a(this, (byte)2);
            setBeenAttacked();
            if(entity != null)
            {
                double d = entity.posX - posX;
                double d1;
                for(d1 = entity.posZ - posZ; d * d + d1 * d1 < 0.0001D; d1 = (Math.random() - Math.random()) * 0.01D)
                {
                    d = (Math.random() - Math.random()) * 0.01D;
                }

                attackedAtYaw = (float)((Math.atan2(d1, d) * 180D) / 3.1415927410125732D) - rotationYaw;
                knockBack(entity, i, d, d1);
            } else
            {
                attackedAtYaw = (int)(Math.random() * 2D) * 180;
            }
        }
        if(health <= 0)
        {
            if(flag)
            {
                worldObj.playSoundAtEntity(this, getDeathSound(), getSoundVolume(), 1.0F);
            }
            if(entity instanceof EntityPlayer)
            {
            	ModLoader.getMinecraftInstance().thePlayer.triggerAchievement(mod_FantaBob.killFantaAch);
            }
            onDeath(entity);
        } else
        if(flag)
        {
            worldObj.playSoundAtEntity(this, getHurtSound(), getSoundVolume(), 1.0F);
        }
        return true;
    }
    
    public void onDeath(Entity entity)
    {
    	if(entityToAttack != null)
    	{
    		((EntityBob)entityToAttack).setFollowed(false);
    	}
    	super.onDeath(entity);
    }
    
    private static final double proximityRadius = 3;
	private static final double detectionRadius = 16;
	private static final double rejoinRadius    = 5;
}
