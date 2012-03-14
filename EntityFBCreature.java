package net.minecraft.src;

public class EntityFBCreature extends EntityCreature
{

	public EntityFBCreature(World world) 
	{
		super(world);
	}
	
    public void onDeath(DamageSource par1DamageSource)
    {
    	ModLoader.getMinecraftInstance().thePlayer.triggerAchievement(getKillAch());
    }
    
    private float getSoundPitch()
    {
    	return 1.0F;
    }
    
    public Achievement getKillAch()
    {
    	return null;
    }
    
    protected int field_34905_c;
    protected EntityPlayer field_34904_b;

	public int getMaxHealth()
	{
		return 10;
	}
}
