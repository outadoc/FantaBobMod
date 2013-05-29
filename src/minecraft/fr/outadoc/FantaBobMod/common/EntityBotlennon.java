package fr.outadoc.FantaBobMod.common;

import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityBotlennon extends EntityMob
{
	public EntityBotlennon(World world)
	{
		super(world);
		texture = "/mods/FantaBobMod/textures/mobs/botlennon.png";
	}

	protected int getDropItemId()
	{
		return Item.ingotIron.itemID;
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
		if (s != null) {
			worldObj.playSoundAtEntity(this, s, getSoundVolume(), 1.0F);
		}
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

	public void onLivingUpdate() 
	{
		if (this.worldObj.isDaytime() && !this.worldObj.isRemote)
		{
			float var1 = this.getBrightness(1.0F);

			if (var1 > 0.5F
					&& this.worldObj.canBlockSeeTheSky(
							MathHelper.floor_double(this.posX),
							MathHelper.floor_double(this.posY),
							MathHelper.floor_double(this.posZ))
					&& this.rand.nextFloat() * 30.0F < (var1 - 0.4F) * 2.0F) {
				this.setFire(8);
			}
		}

		super.onLivingUpdate();
	}

	protected float getSoundPitch() 
	{
		return 1.0F;
	}

	public int getMaxHealth()
	{
		return 12;
	}

	public Achievement getKillAch()
	{
		return FantaBobMod.killBotlennonAch;
	}
}
