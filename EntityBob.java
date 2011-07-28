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
	
	public void onDeath(Entity entity) 
 	{
 		super.onDeath(entity);
 		ModLoader.getMinecraftInstance().thePlayer.triggerAchievement(mod_FantaBob.killBobAch);
 	}
	
	public int getMaxSpawnedInChunk() 
	{
		return 1;
	}
	
	protected float getSoundVolume() 
	{
		return 0.8F;
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
            onDeath(entity);
        } else
        if(flag)
        {
            worldObj.playSoundAtEntity(this, getHurtSound(), getSoundVolume(), 1.0F);
        }
        return true;
    }
    
    public boolean isFollowed()
    {
    	return followed;
    }
    
    public void setFollowed(boolean followed)
    {
    	this.followed = followed;
    }

    private boolean followed;
	protected int attackStrength;
}
