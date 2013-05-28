package fr.outadoc.FantaBobMod.common;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityBob extends EntityCreature
{
	public EntityBob(World world) 
	{
		super(world);
		texture = "/mods/FantaBobMod/Textures/Mobs/bob.png";
		attackStrength = 4;
		hasPlayedBurnSound = false;
		isImmuneToFire = FantaBobMod.isImmuneToFire;
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

		for (int i = 0; i < worldObj.loadedEntityList.size(); i++) 
		{
			Entity target = (Entity) worldObj.loadedEntityList.get(i);

			if (target instanceof EntityJeanKevin) {
				double distance = target.getDistance(posX, posY, posZ);
				if (distance < 16
						&& ((EntityJeanKevin) target).canEntityBeSeen(this)) 
				{
					kevin = (EntityJeanKevin) target;
				}
			}
		}
		return kevin;
	}

	protected void attackEntity(Entity entity, float f) 
	{
		if (attackTime <= 0 && f < 2.0F && entity.boundingBox.maxY > boundingBox.minY && entity.boundingBox.minY < boundingBox.maxY) 
		{
			attackTime = 20;
			Material material = worldObj.getBlockMaterial((int) entity.posX, (int) entity.posY, (int) entity.posZ);
			if (material == Material.air && FantaBobMod.isPyromaniac)
			{
				worldObj.setBlock((int) entity.posX, (int) entity.posY,
						(int) entity.posZ, Block.fire.blockID);
				worldObj.playSoundAtEntity(this, getBurnSound(),
						getSoundVolume(), 1.0F);
			}
			else
				attackEntityAsMob(entity);
		}
	}

	public boolean attackEntityAsMob(Entity entity) 
	{
		return entity.attackEntityFrom(DamageSource.causeMobDamage(this), attackStrength);
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

	private String getBurnSound() 
	{
		return "fantabob.bobburn";
	}

	protected float getSoundVolume() 
	{
		return 0.8F;
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
		if (FantaBobMod.isPyromaniac)
		{
			Random rand = new Random();
			int j = rand.nextInt(100);
			if (j <= FantaBobMod.pyroRate) 
			{
				if (worldObj != null) 
				{
					Material material = worldObj.getBlockMaterial((int) this.posX + 1, (int) this.posY - 1, (int) this.posZ);
					if ((FantaBobMod.canBurnWood && material == Material.wood)
							|| (FantaBobMod.canBurnWool && material == Material.cloth)
							|| (FantaBobMod.canBurnTNT && material == Material.tnt)
							|| (FantaBobMod.canBurnPlants && material == Material.plants)
							|| (FantaBobMod.canBurnLeaves && material == Material.leaves))
					{
						worldObj.setBlock((int) this.posX + 1, (int) this.posY, (int) this.posZ, Block.fire.blockID);
						if (!hasPlayedBurnSound)
						{
							worldObj.playSoundAtEntity(this, getBurnSound(), getSoundVolume(), 1.0F);
							hasPlayedBurnSound = true;
						}
					}
				}
			}
		}
	}

	public Achievement getKillAch() 
	{
		return FantaBobMod.killBobAch;
	}

	public int getMaxHealth()
	{
		return 10;
	}

	public boolean attackEntityFrom(DamageSource par1DamageSource, int par2) 
	{
		super.attackEntityFrom(par1DamageSource, par2);
		entityToAttack = null;
		return false;
	}

	public void onDeath(DamageSource par1DamageSource)
	{
		Entity assassin = par1DamageSource.getEntity();

		if (assassin != null && assassin instanceof EntityPlayer) 
		{
			((EntityPlayer) assassin).triggerAchievement(getKillAch());
		}

		super.onDeath(par1DamageSource);
	}

	protected float getSoundPitch() 
	{
		return 1.0F;
	}

	public void playLivingSound()
	{
		String s = getLivingSound();
		if (s != null) 
		{
			worldObj.playSoundAtEntity(this, s, getSoundVolume(), 1.0F);
		}
	}

	private boolean followed;
	protected int attackStrength;
	private boolean hasPlayedBurnSound;
}
