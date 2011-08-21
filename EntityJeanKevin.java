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
        EntityPlayer player = null;
        
        for(int i = 0; i < worldObj.loadedEntityList.size(); i++)
        {
            Entity target = (Entity)worldObj.loadedEntityList.get(i);
            
            if(target instanceof EntityPlayer)
            {
            	double distance = target.getDistance(posX, posY, posZ);
            	if(distance < 16 && ((EntityJeanKevin)target).canEntityBeSeen(this))
                {
                    player = (EntityPlayer)target;
                    ModLoader.getMinecraftInstance().thePlayer.triggerAchievement(mod_FantaBob.floodAch);
                }
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
 	
 	public int getMaxSpawnedInChunk()
 	{
 		return 3;
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
            	ModLoader.getMinecraftInstance().thePlayer.triggerAchievement(mod_FantaBob.killJeanKevinAch);
            }
            onDeath(entity);
        } else
        if(flag)
        {
            worldObj.playSoundAtEntity(this, getHurtSound(), getSoundVolume(), 1.0F);
        }
        return true;
    }
}
