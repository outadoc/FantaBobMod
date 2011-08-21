package net.minecraft.src;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;
import java.util.Properties;

import net.minecraft.client.Minecraft;

public class mod_FantaBob extends BaseMod
{
    public String Version()
    {
        return "1.7.2";
    } 
    
    /*
     * IDs:
     * items: 400 à 407
     * blocs: 120
     * achievements: 887 à 901
     */
    
	public mod_FantaBob()
    {
		propsLocation = new StringBuilder().append(Minecraft.getMinecraftDir()).append("/").append("mod_FantaBob.props").toString();
		try {
			props = loadProperties(propsLocation);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			try {
				createPropsFile(propsLocation);
				props = loadProperties(propsLocation);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//enregistrement des mobs
		ModLoader.RegisterEntityID(EntityBob.class, "Boblennon", ModLoader.getUniqueEntityId());
        ModLoader.RegisterEntityID(EntityFanta.class, "TheFantasio974", ModLoader.getUniqueEntityId());
        ModLoader.RegisterEntityID(EntityJeanKevin.class, "JeanKevin", ModLoader.getUniqueEntityId());
        ModLoader.RegisterEntityID(EntityBotlennon.class, "Botlennon", ModLoader.getUniqueEntityId());

        //paramètrage du spawn des mobs
        if(getBooleanProp("boblennon.spawn"))
        	ModLoader.AddSpawn(EntityBob.class, getIntegerProp("boblennon.spawn.rate"), EnumCreatureType.creature);
        if(getBooleanProp("fanta.spawn"))
        	ModLoader.AddSpawn(EntityFanta.class, getIntegerProp("fanta.spawn.rate"), EnumCreatureType.creature);
        if(getBooleanProp("jeankevin.spawn"))
        	ModLoader.AddSpawn(EntityJeanKevin.class, getIntegerProp("jeankevin.spawn.rate"), EnumCreatureType.creature);
        if(getBooleanProp("botlennon.spawn"))
        	ModLoader.AddSpawn(EntityBotlennon.class, getIntegerProp("botlennon.spawn.rate"), EnumCreatureType.monster);
        
        //ajout des armures personnalisées
        ModLoader.AddArmor("fantabob");
        ModLoader.AddArmor("fantabob2");
        
        //instanciation des objets
        fantaGlasses = (new ItemArmor(144, 3, 5, 0))
        	.setItemName("fantaGlasses")
        	.setMaxStackSize(64)
        	.setIconIndex(ModLoader.addOverride("/gui/items.png", "/fantabob/glasses.png"));
        cobbleTie = (new ItemArmor(145, 3, 5, 1))
        	.setItemName("cobbleTie")
        	.setIconIndex(ModLoader.addOverride("/gui/items.png", "/fantabob/cobble_tie.png"));
        toothBrush = (new ItemObsidianToothBrush(402))
        	.setItemName("toothBrush")
        	.setIconIndex(ModLoader.addOverride("/gui/items.png", "/fantabob/obsi_toothbrush.png"));
        stampCollection = (new Item(403))
    		.setItemName("stampCollection")
    		.setIconIndex(ModLoader.addOverride("/gui/items.png", "/fantabob/stamp_collection.png"));
        bambooSword = (new ItemSword(404, EnumToolMaterial.WOOD))
        	.setItemName("bambooSword")
        	.setIconIndex(ModLoader.addOverride("/gui/items.png", "/fantabob/bamboo_sword.png"));
        hairPotion = (new ItemArmor(149, 3, 6, 0))
    		.setItemName("hairPotion")
    		.setIconIndex(ModLoader.addOverride("/gui/items.png", "/fantabob/hair_potion.png"));
        bouse = new BlockBouse(120, 0)
        	.setHardness(0.6F)
        	.setResistance(4.0F)
        	.setBlockName("bouse")
        	.setStepSound(new StepSound("bouse", 3.0F, 1.0F));
        bouse.blockIndexInTexture = ModLoader.addOverride("/terrain.png", "/fantabob/bouse.png");
        magabondChop = new ItemFood(406, 6, true)
        	.setItemName("magabondChop")
        	.setMaxStackSize(64)
        	.setIconCoord(7, 5);
        magabondRecord = (new ItemRecord(407, "ATE BITS - Magabond"))
        	.setIconIndex(ModLoader.addOverride("/gui/items.png", "/fantabob/magabond_record.png"))
        	.setItemName("magabondRecord");
        
        //enregistrement des objets spéciaux
        ModLoader.RegisterEntityID(ItemObsidianToothBrush.class, "ToothBrush", ModLoader.getUniqueEntityId());
        
        //enregistrement des blocs
        ModLoader.RegisterBlock(bouse);
        
        //recettes de craft
        ModLoader.AddRecipe(new ItemStack(fantaGlasses, 1), new Object[]
            {"# #", "# #", "X#X", Character.valueOf('#'), Item.stick, Character.valueOf('X'), Block.glass});
        ModLoader.AddRecipe(new ItemStack(cobbleTie, 1), new Object[]
            {"#", "X", "X", Character.valueOf('#'), Item.silk, Character.valueOf('X'), Block.cobblestone});
        ModLoader.AddRecipe(new ItemStack(toothBrush, 1), new Object[]
            {"#X", "# ", "# ", Character.valueOf('#'), Block.obsidian, Character.valueOf('X'), Block.cloth});
        ModLoader.AddRecipe(new ItemStack(stampCollection, 1), new Object[]
            {"#XX", "#XX", "#XX", Character.valueOf('#'), Item.book, Character.valueOf('X'), Item.paper});
        ModLoader.AddRecipe(new ItemStack(bambooSword, 1), new Object[]
            {"#", "#", "X", Character.valueOf('#'), Item.reed, Character.valueOf('X'), Item.stick});
        ModLoader.AddRecipe(new ItemStack(hairPotion, 1), new Object[]
            {"#", "#X#", "#", Character.valueOf('#'), Block.glass, Character.valueOf('X'), Item.redstone});
        ModLoader.AddRecipe(new ItemStack(bouse, 3), new Object[]
            {"X", "#", Character.valueOf('#'), Block.dirt, Character.valueOf('X'), Item.bucketWater});
        ModLoader.AddRecipe(new ItemStack(magabondRecord, 1), new Object[]
            {" # ", "#X#", " # ", Character.valueOf('#'), magabondChop, Character.valueOf('X'), Item.redstone});
        
        //ajout du nom des blocs/objets
        ModLoader.AddName(fantaGlasses, "Lunettes de Fantasio");
        ModLoader.AddName(cobbleTie, "Cravate en cobble");
        ModLoader.AddName(toothBrush, "Brosse à dents en obsidienne");
        ModLoader.AddName(stampCollection, "Collection de timbres");
        ModLoader.AddName(bambooSword, "Épée en bambou");
        ModLoader.AddName(hairPotion, "Lotion capillaire de Papy Lennon");
        ModLoader.AddName(bouse, "Bouse");
        ModLoader.AddName(magabondChop, "Côtelette de magabond");
        ModLoader.AddName(magabondRecord, "Magabond Remix");
        
        //ajout des achievements
        installModAch = new Achievement(887, "installModAch", -2, 0, Item.paper, null).registerAchievement();
        killBobAch = new Achievement(888, "killBobAch", -2, 3, Block.cobblestone, installModAch).registerAchievement();
        killFantaAch = new Achievement(889, "killFantaAch", -2, -1, Item.swordWood, installModAch).registerAchievement();
        killJeanKevinAch = new Achievement(890, "killJeanKevinAch", -2, -4, Item.arrow, installModAch).registerAchievement();
        floodAch = new Achievement(891, "floodAch", -4, 0, Item.bucketLava, installModAch).registerAchievement();
        killBotlennonAch = new Achievement(892, "killBotlennonAch", -2, 1, Item.ingotIron, installModAch).registerAchievement();
        getBouseAch = new Achievement(893, "getBouseAch", -5, 2, bouse, installModAch).registerAchievement();
        brushTeethAch = new Achievement(894, "brushTeethAch", 0, -5, toothBrush, installModAch).registerAchievement();
        getHairPotionAch = new Achievement(895, "getHairPotionAch", -3, 4, hairPotion, installModAch).registerAchievement();
        getStampCollectionAch = new Achievement(896, "getStampCollectionAch", 1, -2, stampCollection, installModAch).registerAchievement();
        getCobbleTieAch = new Achievement(897, "getCobbleTieAch", -7, -6, cobbleTie, installModAch).registerAchievement();
        getMagabondChopAch = new Achievement(898, "getMagabondChopAch", -5, -3, magabondChop, killJeanKevinAch).registerAchievement();
        craftMagabondRecAch = new Achievement(899, "craftMagabondRecAch", -5, -5, magabondRecord, getMagabondChopAch).registerAchievement();
        getFantaGlassesAch = new Achievement(900, "getFantaGlassesAch", -4, -2, fantaGlasses, killFantaAch).registerAchievement();
        getBambooSwordAch = new Achievement(901, "getBambooSwordAch", -3, -7, bambooSword, installModAch).registerAchievement();
        
        //descriptions des achievements
        ModLoader.AddAchievementDesc(installModAch, "Youtuber", "Installer le mod FantaBobShow");
        ModLoader.AddAchievementDesc(craftMagabondRecAch, "Magabooond", "Crafter un magabond remix");
        ModLoader.AddAchievementDesc(getFantaGlassesAch, "Binoclard", "Récupérer les lunettes de Fantasio");
        ModLoader.AddAchievementDesc(killBobAch, "Bob Lennon Hater", "Tuer Bob Lennon");
        ModLoader.AddAchievementDesc(killFantaAch, "Spirou", "Tuer Fantasio");
        ModLoader.AddAchievementDesc(killJeanKevinAch, "Sauveur de l'humanité", "Tuer Jean-Kévin");
        ModLoader.AddAchievementDesc(floodAch, "Flooooood !", "Etre suivi par Jean-Kévin");
        ModLoader.AddAchievementDesc(killBotlennonAch, "Tas de ferraille", "Tuer Botlennon");
        ModLoader.AddAchievementDesc(getBouseAch, "Élément naturel", "Crafter de la bouse");
        ModLoader.AddAchievementDesc(brushTeethAch, "Hygiène dentaire", "Se brosser les dents");
        ModLoader.AddAchievementDesc(getHairPotionAch, "Crâne rasé", "Crafter une lotion capillaire");
        ModLoader.AddAchievementDesc(getStampCollectionAch, "Philatéliste", "Crafter une collection de timbres");
        ModLoader.AddAchievementDesc(getMagabondChopAch, "Repas avarié", "Obtenir une côtelette de magabond");
        ModLoader.AddAchievementDesc(getCobbleTieAch, "Bonjour patron", "Crafter une cravate en cobble");
        ModLoader.AddAchievementDesc(getBambooSwordAch, "Assassin au naturel", "Crafter une épée en bambou");
        
        //pour le onTickInGame()
        ModLoader.SetInGameHook(this, true, false);
    }
	
	public void TakenFromCrafting(EntityPlayer entityplayer, ItemStack itemstack)
    {
        if(itemstack.itemID == magabondRecord.shiftedIndex)
        	entityplayer.triggerAchievement(craftMagabondRecAch);
        else if(itemstack.itemID == bouse.blockID)
			entityplayer.triggerAchievement(getBouseAch);
        else if(itemstack.itemID == hairPotion.shiftedIndex)
        	entityplayer.triggerAchievement(getHairPotionAch);
        else if(itemstack.itemID == bambooSword.shiftedIndex)
        	entityplayer.triggerAchievement(getBambooSwordAch);
    }
	
	public void OnItemPickup(EntityPlayer entityplayer, ItemStack itemstack) 
	{
		if(itemstack.itemID == fantaGlasses.shiftedIndex)
			entityplayer.triggerAchievement(getFantaGlassesAch);
		else if(itemstack.itemID == magabondChop.shiftedIndex)
        	entityplayer.triggerAchievement(getMagabondChopAch);
	}
	
	public boolean OnTickInGame(Minecraft minecraft) 
	{
		minecraft.thePlayer.triggerAchievement(installModAch);
		return true;
	}
	
	public void AddRenderer(Map map)
    {
        map.put(EntityBob.class, new RenderBiped(new ModelBiped(), 0.5F));
        map.put(EntityFanta.class, new RenderBiped(new ModelBiped(), 0.5F));
        map.put(EntityJeanKevin.class, new RenderBiped(new ModelBiped(), 0.5F));
        map.put(EntityBotlennon.class, new RenderBiped(new ModelBiped(), 0.5F));
    }
	
	public Properties loadProperties(String location) throws FileNotFoundException, IOException 
	{
		Properties props = new Properties();
		props.load(new FileInputStream(location));
		return props;
	}
	
	public void saveProperties(Properties props, String fileLocation, String comments) throws IOException 
	{
		OutputStream out = new FileOutputStream(fileLocation);
		props.store(out, comments);
		out.flush();
		out.close();
	}
	
	public void createPropsFile(String location) throws IOException
	{
		props = new Properties();
		
		props.setProperty("fanta.spawn", "true");
		props.setProperty("boblennon.spawn", "true");
		props.setProperty("jeankevin.spawn", "true");
		props.setProperty("botlennon.spawn", "true");
		
		props.setProperty("fanta.spawn.rate", "9");
		props.setProperty("boblennon.spawn.rate", "9");
		props.setProperty("jeankevin.spawn.rate", "10");
		props.setProperty("botlennon.spawn.rate", "9");
		
		props.setProperty("boblennon.pyromaniac", "false");
		props.setProperty("boblennon.pyromaniac.rate", "40");
		props.setProperty("boblennon.pyromaniac.fire.wood", "true");
		props.setProperty("boblennon.pyromaniac.fire.plants", "true");
		props.setProperty("boblennon.pyromaniac.fire.tnt", "true");
		props.setProperty("boblennon.pyromaniac.fire.wool", "true");
		props.setProperty("boblennon.pyromaniac.fire.leaves", "true");
		
		saveProperties(props, propsLocation, null);
	}
	
	public static Boolean getBooleanProp(String prop)
	{
		return Boolean.parseBoolean((String)props.get(prop));
	}
	
	public static String getStringProp(String prop)
	{
		return (String)props.get(prop);
	}
	
	public static Integer getIntegerProp(String prop)
	{
		return Integer.parseInt((String)props.get(prop));
	}
	
	//déclaration des objets
    public static Item fantaGlasses;
    public static Item cobbleTie;
    public static Item toothBrush;
    public static Item stampCollection;
    public static Item bambooSword;
    public static Item hairPotion;
    public static Item magabondChop;
    public static Item magabondRecord;
    
    //déclaration des achievements
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
    
    //déclaration des blocs
    public static Block bouse;
    
    public static Properties props;
    public static String propsLocation;
}