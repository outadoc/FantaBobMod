package fr.mcnanotech.FantaBobMod.common;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityJeanKevin extends EntityCreature {
	public EntityJeanKevin(World world) {
		super(world);
		texture = "/mob/char.png";
	}

	protected int getDropItemId() {
		return FantaBobMod.magabondChop.itemID;
	}

	public ItemStack getHeldItem() {
		return (new ItemStack(Block.dirt));
	}

	protected Entity findPlayerToAttack() {
		EntityPlayer var1 = this.worldObj.getClosestVulnerablePlayerToEntity(
				this, 16.0D);
		if (var1 != null && this.canEntityBeSeen(var1)) {
			var1.triggerAchievement(FantaBobMod.floodAch);
			return var1;
		}

		return null;
	}

	protected String getHurtSound() {
		return "fantabob.kevinhurt";
	}

	protected String getDeathSound() {
		return "fantabob.kevindeath";
	}

	protected String getLivingSound() {
		return "fantabob.kevin";
	}

	public Achievement getKillAch() {
		return FantaBobMod.killJeanKevinAch;
	}

	public void onDeath(DamageSource par1DamageSource) {
		Entity assassin = par1DamageSource.getEntity();

		if (assassin != null && assassin instanceof EntityPlayer) {
			((EntityPlayer) assassin).triggerAchievement(getKillAch());
		}

		super.onDeath(par1DamageSource);
	}

	protected float getSoundPitch() {
		return 1.0F;
	}

	public void playLivingSound() {
		String s = getLivingSound();
		if (s != null) {
			worldObj.playSoundAtEntity(this, s, getSoundVolume(), 1.0F);
		}
	}

	public int getMaxHealth() {
		return 20;
	}

	public boolean attackEntityFrom(DamageSource par1DamageSource, int par2) {
		super.attackEntityFrom(par1DamageSource, par2);
		entityToAttack = null;
		return false;
	}
}
