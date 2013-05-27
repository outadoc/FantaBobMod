package fr.mcnanotech.FantaBobMod.common;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityFanta extends EntityCreature {
	public EntityFanta(World world) {
		super(world);
		texture = "/fantabob/fanta.png";
	}

	protected int getDropItemId() {
		return FantaBobMod.fantaGlasses.itemID;
	}

	public ItemStack getHeldItem() {
		return (new ItemStack(Item.swordWood));
	}

	protected Entity findPlayerToAttack() {
		EntityBob bob = null;

		for (int i = 0; i < worldObj.loadedEntityList.size(); i++) {
			Entity target = (Entity) worldObj.loadedEntityList.get(i);

			if (target instanceof EntityBob) {
				double distance = target.getDistance(posX, posY, posZ);
				if (distance <= detectionRadius && distance >= rejoinRadius
						&& ((EntityBob) target).canEntityBeSeen(this)) {
					bob = (EntityBob) target;

					if (bob.isFollowed())
						bob = null;
					else
						bob.setFollowed(true);
				}
			}
		}

		return bob;
	}

	public void onLivingUpdate() {
		super.onLivingUpdate();
		if (entityToAttack != null) {
			double distance = entityToAttack.getDistance(posX, posY, posZ);
			if (distance < proximityRadius) {
				((EntityBob) entityToAttack).setFollowed(false);
				entityToAttack = null;
			}
		}
	}

	protected String getHurtSound() {
		return "fantabob.fantahurt";
	}

	protected String getDeathSound() {
		return "fantabob.fantadeath";
	}

	protected String getLivingSound() {
		return "fantabob.fanta";
	}

	public int getMaxSpawnedInChunk() {
		return 1;
	}

	protected void dropFewItems() {
		int i = getDropItemId();
		dropItem(i, 1);
	}

	public Achievement getKillAch() {
		return FantaBobMod.killFantaAch;
	}

	public void onDeath(DamageSource par1DamageSource) {
		if (entityToAttack != null) {
			((EntityBob) entityToAttack).setFollowed(false);
		}

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
		return 10;
	}

	public boolean attackEntityFrom(DamageSource par1DamageSource, int par2) {
		super.attackEntityFrom(par1DamageSource, par2);
		entityToAttack = null;
		return false;
	}

	private static final double proximityRadius = 3;
	private static final double detectionRadius = 16;
	private static final double rejoinRadius = 5;
}
