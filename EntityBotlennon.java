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
	
	public void onDeath(Entity entity) 
 	{
 		super.onDeath(entity);
 		ModLoader.getMinecraftInstance().thePlayer.triggerAchievement(mod_FantaBob.killBotlennonAch);
 	}
}
