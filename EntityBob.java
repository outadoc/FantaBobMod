package net.minecraft.src;

import java.util.Random;
 
public class EntityBob extends EntityCreature
{
	public EntityBob(World world)
	{
		super(world);
		texture = "/fantabob/bob.png";
		attackStrength = 4;
		isImmuneToFire = mod_FantaBob.getBooleanProp("boblennon.immunetofire");
		
		canBurnWool = mod_FantaBob.getBooleanProp("boblennon.pyromaniac.fire.wool");
		canBurnWood = mod_FantaBob.getBooleanProp("boblennon.pyromaniac.fire.wood");
		canBurnTNT = mod_FantaBob.getBooleanProp("boblennon.pyromaniac.fire.tnt");
		canBurnPlants = mod_FantaBob.getBooleanProp("boblennon.pyromaniac.fire.plants");
		canBurnLeaves = mod_FantaBob.getBooleanProp("boblennon.pyromaniac.fire.leaves");
		
		isPyromaniac = mod_FantaBob.getBooleanProp("boblennon.pyromaniac");
		pyroRate = mod_FantaBob.getIntegerProp("boblennon.pyromaniac.rate");
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
		EntityJeanKevin kevin = null;
       
		for(int i = 0; i < worldObj.loadedEntityList.size(); i++)
		{
			Entity target = (Entity)worldObj.loadedEntityList.get(i);
           
			if(target instanceof EntityJeanKevin)
			{
				double distance = target.getDistance(posX, posY, posZ);
				if(distance < 16 && ((EntityJeanKevin)target).canEntityBeSeen(this))
				{
					kevin = (EntityJeanKevin)target;
				}
			}
		}
		return kevin;
	}
   
	protected void attackEntity(Entity entity, float f)
	{
		if(attackTime <= 0 && f < 2.0F && entity.boundingBox.maxY > boundingBox.minY && entity.boundingBox.minY < boundingBox.maxY)
		{
			attackTime = 20;
			World world = ModLoader.getMinecraftInstance().theWorld;
			Material material = world.getBlockMaterial((int)entity.posX, (int)entity.posY, (int)entity.posZ);
			if(material == Material.air && isPyromaniac)
				world.setBlockWithNotify((int)entity.posX, (int)entity.posY, (int)entity.posZ, Block.fire.blockID);
			else
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
            if(entity instanceof EntityPlayer)
            {
            	ModLoader.getMinecraftInstance().thePlayer.triggerAchievement(mod_FantaBob.killBobAch);
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
    
    public void onLivingUpdate()
    {
    	super.onLivingUpdate();

    	if(isPyromaniac)
    	{
    		World world = ModLoader.getMinecraftInstance().theWorld;
	    	Random rand = new Random();
	    	int j = rand.nextInt(100);
	    	if(j >= 0 && j <= pyroRate)
	    	{
	    		Material material = world.getBlockMaterial((int)this.posX + 1, (int)this.posY - 1, (int)this.posZ);
	        	if((canBurnWood && material == Material.wood) || (canBurnWool && material == Material.cloth) || (canBurnTNT && material == Material.tnt) || (canBurnPlants && material == Material.plants) || (canBurnLeaves && material == Material.leaves))
	        	{
	        		world.setBlockWithNotify((int)this.posX + 1, (int)this.posY, (int)this.posZ, Block.fire.blockID);
	        	}
	    	}
    	}
    }

    private boolean followed;
	protected int attackStrength;
	
	private boolean canBurnWood;
	private boolean canBurnWool;
	private boolean canBurnTNT;
	private boolean canBurnPlants;
	private boolean canBurnLeaves;
	
	private boolean isPyromaniac;
	private Integer pyroRate;
}
