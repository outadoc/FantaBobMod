package fr.mcnanotech.FantaBobMod.common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;
import java.util.logging.Logger;

import net.minecraft.block.Block;
import net.minecraft.block.StepSound;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.network.packet.Packet23VehicleSpawn;
import net.minecraft.potion.Potion;
import net.minecraft.src.ModLoader;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.AchievementList;
import net.minecraft.world.World;
import net.minecraftforge.common.Configuration;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = "FantaBobMod", name = "Fanta Bob Mod", version = "2.0.0")
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class FantaBobMod
{
	@SidedProxy(clientSide = "fr.mcnanotech.FantaBobMod.client.ClientProxy", serverSide = "fr.mcnanotech.FantaBobMod.common.CommonProxy")
	public static CommonProxy proxy;
	// Items
	public static Item fantaGlasses;
	public static Item cobbleTie;
	public static Item toothBrush;
	public static Item stampCollection;
	public static Item bambooSword;
	public static Item hairPotion;
	public static Item magabondChop;
	public static Item magabondRecord;

	// Achievement
	public static Achievement installModAch;
	public static Achievement craftMagabondRecAch;
	public static Achievement getFantaGlassesAch;
	public static Achievement killBobAch;
	public static Achievement killFantaAch;
	public static Achievement killJeanKevinAch;
	public static Achievement floodAch;
	public static Achievement killBotlennonAch;
	public static Achievement getBouseAch;
	public static Achievement brushTeethAch;
	public static Achievement getHairPotionAch;
	public static Achievement getStampCollectionAch;
	public static Achievement getMagabondChopAch;
	public static Achievement getCobbleTieAch;
	public static Achievement getBambooSwordAch;

	// Block
	public static Block bouse;

	public static LinkedProperties props;
	public static String propsLocation;
	
	//Log
	public static Logger FBMlog = Logger.getLogger("FantaBobMod");
	
	//cfg
	public static boolean fantaSpawn, BoblennonSpawn, JeankevinSpawn, BotlennonSpawn;
	public static int fantaSpawnRate, BoblennonSpawnRate, JeankevinSpawnRate, BotlennonSpawnRate;
	// BobLennon capacities
	public static boolean canBurnWool, canBurnWood, canBurnTNT, canBurnPlants, canBurnLeaves, isPyromaniac, isImmuneToFire;
	public static int pyroRate;

	/*
	 * IDs: items: 400 ˆ 407 blocs: 243 entites: 101 ˆ 104 achievements: 888 ˆ
	 * 901
	 */

	@PreInit
	public void preload(FMLPreInitializationEvent event)
	{
		FBMlog.setParent(FMLLog.getLogger());
		//config
		Configuration cfg = new Configuration(event.getSuggestedConfigurationFile());
		cfg.load();
		fantaSpawn = cfg.get("Mob Spawn", "Fanta", true).getBoolean(true);
		BoblennonSpawn = cfg.get("Mob Spawn", "Boblennon", true).getBoolean(true);
		JeankevinSpawn = cfg.get("Mob Spawn", "Jean kevin", true).getBoolean(true);
		BotlennonSpawn = cfg.get("Mob Spawn", "Botlennon", true).getBoolean(true);

		fantaSpawnRate = cfg.get("Mob Spawn Rate", "Fanta", 9).getInt();
		BoblennonSpawnRate = cfg.get("Mob Spawn Rate", "Boblennon", 9).getInt();
		JeankevinSpawnRate = cfg.get("Mob Spawn Rate", "Jean kevin", 10).getInt();
		BotlennonSpawnRate = cfg.get("Mob Spawn Rate", "Botlennon", 9).getInt();

		isImmuneToFire = cfg.get("BobLennon", "boblennon.immunetofire", true).getBoolean(true);
		isPyromaniac = cfg.get("BobLennon", "Pyromaniac", false).getBoolean(true);
		pyroRate = cfg.get("BobLennon", "Pyromaniac Rate", 40).getInt();
		canBurnWood = cfg.get("BobLennon", "Fire Wood", true).getBoolean(true);
		canBurnWool = cfg.get("BobLennon", "Fire Wool", true).getBoolean(true);
		canBurnPlants = cfg.get("BobLennon", "Fire Plants", true).getBoolean(true);
		canBurnTNT = cfg.get("BobLennon", "Fire TNT", true).getBoolean(true);
		canBurnLeaves = cfg.get("BobLennon", "Fire Leaves", true).getBoolean(true);
		
		cfg.save();

	}

	/*
	@Init
	public void load(FMLInitializationEvent event)
	{

		// enregistrement des mobs
		ModLoader.registerEntityID(EntityBob.class, "Boblennon", 101, 204, 13613421);
		ModLoader.registerEntityID(EntityFanta.class, "TheFantasio974", 102, 0, 16777215);
		ModLoader.registerEntityID(EntityJeanKevin.class, "JeanKevin", 103, 44975, 11762530);
		ModLoader.registerEntityID(EntityBotlennon.class, "Botlennon", 104, 204, 16777215);

		// localisation des noms des mobs, pour les oeufs
		LanguageRegistry.instance().addStringLocalization("entity.Boblennon.name", "Boblennon");
		LanguageRegistry.instance().addStringLocalization("entity.TheFantasio974.name", "TheFantasio974");
		LanguageRegistry.instance().addStringLocalization("entity.JeanKevin.name", "Jean-Kevin");
		LanguageRegistry.instance().addStringLocalization("entity.Botlennon.name", "Botlennon");

		// fix pour mc 1.3: impossible d'utiliser ModLoader.getUniqueEntityId(),
		// donc on utilise des ids fixes
		ModLoader.addEntityTracker(this, EntityBob.class, 101, 64, 1, true);
		ModLoader.addEntityTracker(this, EntityFanta.class, 102, 64, 1, true);
		ModLoader.addEntityTracker(this, EntityJeanKevin.class, 103, 64, 1,
				true);
		ModLoader.addEntityTracker(this, EntityBotlennon.class, 104, 64, 1,
				true);

		// paramŽtrage du spawn des mobs
		if (BoblennonSpawn)
			ModLoader.addSpawn(EntityBob.class, BoblennonSpawnRate, 1, 2, EnumCreatureType.creature);
		if (fantaSpawn)
			ModLoader.addSpawn(EntityFanta.class, fantaSpawnRate, 1, 2, EnumCreatureType.creature);
		if (JeankevinSpawn)
			ModLoader.addSpawn(EntityJeanKevin.class, JeankevinSpawnRate, 2, 3, EnumCreatureType.creature);
		if (BotlennonSpawn)
			ModLoader.addSpawn(EntityBotlennon.class, BotlennonSpawnRate, 1, 3, EnumCreatureType.monster);

		// ajout des armures personnalisees
		ModLoader.addArmor("fantabob");
		ModLoader.addArmor("fantabob2");

		// instanciation des objets
		fantaGlasses = new ItemArmor(400, EnumArmorMaterial.CLOTH, 5, 0).setUnlocalizedName("fantaGlasses").setMaxStackSize(64).setIconIndex(ModLoader.addOverride("/gui/items.png", "/fantabob/glasses.png")).setCreativeTab(CreativeTabs.tabDecorations);
		cobbleTie = (new ItemArmor(401, EnumArmorMaterial.CLOTH, 5, 1))
				.setItemName("cobbleTie")
				.setIconIndex(
						ModLoader.addOverride("/gui/items.png",
								"/fantabob/cobble_tie.png"))
				.setCreativeTab(CreativeTabs.tabDecorations);
		toothBrush = (new ItemObsidianToothBrush(402))
				.setItemName("toothBrush").setIconIndex(
						ModLoader.addOverride("/gui/items.png",
								"/fantabob/obsi_toothbrush.png"));
		stampCollection = (new Item(403))
				.setItemName("stampCollection")
				.setIconIndex(
						ModLoader.addOverride("/gui/items.png",
								"/fantabob/stamp_collection.png"))
				.setCreativeTab(CreativeTabs.tabDecorations);
		bambooSword = (new ItemSword(404, EnumToolMaterial.WOOD))
				.setItemName("bambooSword")
				.setIconIndex(
						ModLoader.addOverride("/gui/items.png",
								"/fantabob/bamboo_sword.png"))
				.setCreativeTab(CreativeTabs.tabCombat);
		hairPotion = (new ItemArmor(405, EnumArmorMaterial.CLOTH, 6, 0))
				.setItemName("hairPotion")
				.setIconIndex(
						ModLoader.addOverride("/gui/items.png",
								"/fantabob/hair_potion.png"))
				.setCreativeTab(CreativeTabs.tabMisc);
		magabondChop = new ItemFood(406, 4, 0.1F, true)
				.setPotionEffect(Potion.confusion.id, 20, 0, 0.8F)
				.setItemName("magabondChop")
				.setMaxStackSize(64)
				.setIconIndex(
						ModLoader.addOverride("/gui/items.png",
								"/fantabob/magabond_chop.png"))
				.setCreativeTab(CreativeTabs.tabFood);
		magabondRecord = (new ItemRecordFantaBob(407, "Magabond", "ATE BITS"))
				.setIconIndex(
						ModLoader.addOverride("/gui/items.png",
								"/fantabob/magabond_record.png")).setItemName(
						"magabondRecord");
		bouse = new BlockBouse(200, 0).setHardness(0.6F).setResistance(4.0F)
				.setBlockName("bouse")
				.setStepSound(new StepSound("bouse", 3.0F, 1.0F));
		bouse.blockIndexInTexture = ModLoader.addOverride("/terrain.png",
				"/fantabob/bouse.png");

		// enregistrement des blocs
		ModLoader.registerBlock(bouse);

		// recettes de craft
		ModLoader.addRecipe(new ItemStack(fantaGlasses, 1), new Object[] {
				"# #", "# #", "X#X", Character.valueOf('#'), Item.stick,
				Character.valueOf('X'), Block.glass });
		ModLoader.addRecipe(new ItemStack(cobbleTie, 1),
				new Object[] { "#", "X", "X", Character.valueOf('#'),
						Item.silk, Character.valueOf('X'), Block.cobblestone });
		ModLoader.addRecipe(new ItemStack(toothBrush, 1),
				new Object[] { "#X", "# ", "# ", Character.valueOf('#'),
						Block.obsidian, Character.valueOf('X'), Block.cloth });
		ModLoader.addRecipe(new ItemStack(stampCollection, 1), new Object[] {
				"#XX", "#XX", "#XX", Character.valueOf('#'), Item.book,
				Character.valueOf('X'), Item.paper });
		ModLoader.addRecipe(new ItemStack(bambooSword, 1),
				new Object[] { "#", "#", "X", Character.valueOf('#'),
						Item.reed, Character.valueOf('X'), Item.stick });
		ModLoader.addRecipe(new ItemStack(hairPotion, 1),
				new Object[] { " # ", "#X#", " # ", Character.valueOf('#'),
						Block.glass, Character.valueOf('X'), Item.redstone });
		ModLoader.addRecipe(new ItemStack(bouse, 3), new Object[] { "X", "#",
				Character.valueOf('#'), Block.dirt, Character.valueOf('X'),
				Item.bucketWater });
		ModLoader.addRecipe(new ItemStack(magabondRecord, 1), new Object[] {
				" # ", "#X#", " # ", Character.valueOf('#'), magabondChop,
				Character.valueOf('X'), Item.redstone });

		// ajout du nom des blocs/objets
		ModLoader.addName(fantaGlasses, "Lunettes de Fantasio");
		ModLoader.addName(cobbleTie, "Cravate en cobble");
		ModLoader.addName(toothBrush, "Brosse ˆ dents en obsidienne");
		ModLoader.addName(stampCollection, "Collection de timbres");
		ModLoader.addName(bambooSword, "EpŽe en bambou");
		ModLoader.addName(hairPotion, "Lotion capillaire de Papy Lennon");
		ModLoader.addName(bouse, "Bouse");
		ModLoader.addName(magabondChop, "C™telette de magabond");
		ModLoader.addName(magabondRecord, "Disque");

		// ajout des achievements
		// installModAch = new Achievement(887, "installModAch", -2, 0,
		// Item.paper, null).registerAchievement();
		killBobAch = new Achievement(888, "killBobAch", -4, 0,
				Block.cobblestone, AchievementList.openInventory)
				.registerAchievement();
		killFantaAch = new Achievement(889, "killFantaAch", -6, 0,
				Item.swordWood, AchievementList.openInventory)
				.registerAchievement();
		floodAch = new Achievement(891, "floodAch", -8, 0, Item.bucketLava,
				AchievementList.openInventory).registerAchievement();
		killJeanKevinAch = new Achievement(890, "killJeanKevinAch", -10, 0,
				Item.arrow, AchievementList.openInventory)
				.registerAchievement();
		killBotlennonAch = new Achievement(892, "killBotlennonAch", -12, 0,
				Item.ingotIron, AchievementList.openInventory)
				.registerAchievement();
		getBouseAch = new Achievement(893, "getBouseAch", -14, 0, bouse,
				AchievementList.openInventory).registerAchievement();
		brushTeethAch = new Achievement(894, "brushTeethAch", -14, 2,
				toothBrush, AchievementList.openInventory)
				.registerAchievement();
		getHairPotionAch = new Achievement(895, "getHairPotionAch", -12, 2,
				hairPotion, AchievementList.openInventory)
				.registerAchievement();
		getStampCollectionAch = new Achievement(896, "getStampCollectionAch",
				-10, 2, stampCollection, AchievementList.openInventory)
				.registerAchievement();
		getCobbleTieAch = new Achievement(897, "getCobbleTieAch", -8, 2,
				cobbleTie, AchievementList.openInventory).registerAchievement();
		getBambooSwordAch = new Achievement(901, "getBambooSwordAch", -6, 2,
				bambooSword, AchievementList.openInventory)
				.registerAchievement();
		getMagabondChopAch = new Achievement(898, "getMagabondChopAch", -8, -2,
				magabondChop, killJeanKevinAch).registerAchievement();
		craftMagabondRecAch = new Achievement(899, "craftMagabondRecAch", -10,
				-2, magabondRecord, getMagabondChopAch).registerAchievement();
		getFantaGlassesAch = new Achievement(900, "getFantaGlassesAch", -6, -2,
				fantaGlasses, killFantaAch).registerAchievement();

		// descriptions des achievements
		// ModLoader.addAchievementDesc(installModAch, "Youtuber",
		// "Installer le mod FantaBobShow");
		ModLoader.addAchievementDesc(craftMagabondRecAch, "Magabooond", "Crafter un magabond remix");
		ModLoader.addAchievementDesc(getFantaGlassesAch, "Binoclard", "RŽcuperer les lunettes de Fantasio");
		ModLoader.addAchievementDesc(killBobAch, "Bob Lennon Hater", "Tuer Bob Lennon");
		ModLoader.addAchievementDesc(killFantaAch, "Spirou", "Tuer Fantasio");
		ModLoader.addAchievementDesc(killJeanKevinAch, "Sauveur de l'humanitŽ", "Tuer Jean-Kevin");
		ModLoader.addAchievementDesc(floodAch, "Flooooood !", "Se faire suivre par Jean-Kevin");
		ModLoader.addAchievementDesc(killBotlennonAch, "Tas de ferraille", "Tuer Botlennon");
		ModLoader.addAchievementDesc(getBouseAch, "ElŽment naturel", "Crafter de la bouse");
		ModLoader.addAchievementDesc(brushTeethAch, "Hygi�ne dentaire", "Se brosser les dents");
		ModLoader.addAchievementDesc(getHairPotionAch, "Crane r‰sŽ", "Crafter une lotion capillaire");
		ModLoader.addAchievementDesc(getStampCollectionAch, "PhilatŽliste", "Crafter une collection de timbres");
		ModLoader.addAchievementDesc(getMagabondChopAch, "Repas avariŽ", "Obtenir une c™telette de magabond");
		ModLoader.addAchievementDesc(getCobbleTieAch, "Bonjour patron !", "Crafter une cravate en cobble");
		ModLoader.addAchievementDesc(getBambooSwordAch, "Assassin au naturel", "Crafter une ŽpŽe en bambou");

		// pour le onTickInGame()
		// ModLoader.setInGameHook(this, true, false);
	}

	public void takenFromCrafting(EntityPlayer entityplayer, ItemStack itemstack) 
	{
		if (itemstack.itemID == magabondRecord.itemID)
			entityplayer.triggerAchievement(craftMagabondRecAch);
		else if (itemstack.itemID == bouse.blockID)
			entityplayer.triggerAchievement(getBouseAch);
		else if (itemstack.itemID == hairPotion.itemID)
			entityplayer.triggerAchievement(getHairPotionAch);
		else if (itemstack.itemID == bambooSword.itemID)
			entityplayer.triggerAchievement(getBambooSwordAch);
	}

	public void onItemPickup(EntityPlayer entityplayer, ItemStack itemstack) 
	{
		if (itemstack.itemID == fantaGlasses.itemID)
			entityplayer.triggerAchievement(getFantaGlassesAch);
		else if (itemstack.itemID == magabondChop.itemID)
			entityplayer.triggerAchievement(getMagabondChopAch);
	}

	/*
	 * public boolean onTickInGame(float tick, Minecraft minecraft) {
	 * minecraft.thePlayer.triggerAchievement(installModAch); return true; }
	 */

	/*
	public void addRenderer(Map map)
	{
		map.put(EntityBob.class, new RenderBiped(new ModelBiped(), 0.5F));
		map.put(EntityFanta.class, new RenderBiped(new ModelBiped(), 0.5F));
		map.put(EntityJeanKevin.class, new RenderBiped(new ModelBiped(), 0.5F));
		map.put(EntityBotlennon.class, new RenderBiped(new ModelBiped(), 0.5F));
	}

	public Entity spawnEntity(int entityId, World worldClient, double x, double y, double z) 
	{
		switch (entityId) {
		case 101:
			return new EntityBob(worldClient);
		case 102:
			return new EntityFanta(worldClient);
		case 103:
			return new EntityJeanKevin(worldClient);
		case 104:
			return new EntityBotlennon(worldClient);
		default:
			return null;
		}
	}

	public Packet23VehicleSpawn getSpawnPacket(Entity entity, int type) 
	{
		if (entity instanceof EntityBotlennon || entity instanceof EntityBob
				|| entity instanceof EntityFanta
				|| entity instanceof EntityJeanKevin)
			return new Packet23VehicleSpawn(entity, type);
		else
			return null;
	}
	*/
}