package net.minecraft.src;

public class EntityBotlennon extends EntityMob
{
	public EntityBotlennon(World world) 
	{
		super(world);
		texture = "/fantabob/botlennon.png";
	}
	
	protected int getDropItemId()
	{
		return Item.ingotIron.shiftedIndex;
	}
   
	public ItemStack getHeldItem()
	{
		return (new ItemStack(Item.swordStone));
	}
	
	protected String getLivingSound() 
	{
		return "fantabob.botlennon";
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
            	ModLoader.getMinecraftInstance().thePlayer.triggerAchievement(mod_FantaBob.killBotlennonAch);
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
