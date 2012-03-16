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
    
    public void onDeath(DamageSource par1DamageSource)
    {
    	ModLoader.getMinecraftInstance().thePlayer.triggerAchievement(getKillAch());
    	super.onDeath(par1DamageSource);
    }
    
    private float getSoundPitch()
    {
    	return 1.0F;
    }
    
    public int getMaxHealth()
	{
		return 12;
	}
    
    public Achievement getKillAch()
    {
    	return mod_FantaBob.killBotlennonAch;
    }
}
