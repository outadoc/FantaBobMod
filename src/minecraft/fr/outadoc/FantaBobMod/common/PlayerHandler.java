package fr.outadoc.FantaBobMod.common;

import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.IPlayerTracker;

public class PlayerHandler implements IPlayerTracker
{

	@Override
	public void onPlayerLogin(EntityPlayer player)
	{
		//player.triggerAchievement(FantaBobMod.installModAch);
	}

	@Override
	public void onPlayerLogout(EntityPlayer player)
	{
		
	}

	@Override
	public void onPlayerChangedDimension(EntityPlayer player) 
	{
		
	}

	@Override
	public void onPlayerRespawn(EntityPlayer player) 
	{
		
	}

}
