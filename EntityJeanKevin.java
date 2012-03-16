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
            	if(distance < 16 && ((EntityPlayer)target).canEntityBeSeen(this))
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
    
    public Achievement getKillAch()
    {
    	return mod_FantaBob.killJeanKevinAch;
    }
    
    public void onDeath(DamageSource par1DamageSource)
    {
    	ModLoader.getMinecraftInstance().thePlayer.triggerAchievement(getKillAch());
    	super.onDeath(par1DamageSource);
    }
    
    private float getSoundPitch()
    {
    	return 1.0F;
    }
    
    public void playLivingSound()
    {
        String s = getLivingSound();
        if(s != null)
        {
            worldObj.playSoundAtEntity(this, s, getSoundVolume(), 1.0F);
        }
    }
    
    public int getMaxHealth()
	{
		return 20;
	}
    
    public boolean attackEntityFrom(DamageSource par1DamageSource, int par2)
    {
		super.attackEntityFrom(par1DamageSource, par2);
		entityToAttack = null;
		return false;
    }
}
