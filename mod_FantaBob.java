package net.minecraft.src;

import java.util.Map;

public class mod_FantaBob extends BaseMod
{
    public String Version()
    {
        return "1.7.2";
    } 
    
	public mod_FantaBob()
    {
		//enregistrement des mobs
		ModLoader.RegisterEntityID(EntityBob.class, "Boblennon", ModLoader.getUniqueEntityId());
        ModLoader.RegisterEntityID(EntityFanta.class, "TheFantasio974", ModLoader.getUniqueEntityId());
        ModLoader.RegisterEntityID(EntityJeanKevin.class, "JeanKevin", ModLoader.getUniqueEntityId());
        ModLoader.RegisterEntityID(EntityBotlennon.class, "Botlennon", ModLoader.getUniqueEntityId());

        //paramètrage du spawn des mobs
        ModLoader.AddSpawn(EntityBob.class, 9, EnumCreatureType.creature);
        ModLoader.AddSpawn(EntityFanta.class, 9, EnumCreatureType.creature);
        ModLoader.AddSpawn(EntityJeanKevin.class, 10, EnumCreatureType.creature);
        ModLoader.AddSpawn(EntityBotlennon.class, 9, EnumCreatureType.monster);
        
        //ajout des armures personnalisées
        ModLoader.AddArmor("fantabob");
        ModLoader.AddArmor("fantabob2");
        
        //instanciation des objets
        fantaGlasses = (new ItemArmor(144, 3, 5, 0))
        	.setItemName("fantaGlasses")
        	.setIconIndex(ModLoader.addOverride("/gui/items.png", "/fantabob/glasses.png"));
        cobbleTie = (new ItemArmor(145, 3, 5, 1))
        	.setItemName("cobbleTie")
        	.setIconIndex(ModLoader.addOverride("/gui/items.png", "/fantabob/cobble_tie.png"));
        toothBrush = (new ItemObsidianToothBrush(402))
        	.setItemName("toothBrush")
        	.setIconIndex(ModLoader.addOverride("/gui/items.png", "/fantabob/obsi_toothbrush.png"));
        stampCollection = (new ItemStampCollection(403))
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
        
        //enregistrement des objets
        ModLoader.RegisterEntityID(ItemObsidianToothBrush.class, "ToothBrush", ModLoader.getUniqueEntityId());
        ModLoader.RegisterEntityID(ItemStampCollection.class, "StampCollection", ModLoader.getUniqueEntityId());
        
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
            {"###", "# #", Character.valueOf('#'), Block.cloth});
        ModLoader.AddRecipe(new ItemStack(bouse, 3), new Object[]
            {"X", "#", Character.valueOf('#'), Block.dirt, Character.valueOf('X'), Item.bucketWater});
        
        //ajout du nom des blocs/objets
        ModLoader.AddName(fantaGlasses, "Lunettes de Fantasio");
        ModLoader.AddName(cobbleTie, "Cravate en cobble");
        ModLoader.AddName(toothBrush, "Brosse à dents en obsidienne");
        ModLoader.AddName(stampCollection, "Collection de timbres");
        ModLoader.AddName(bambooSword, "Épée en bambou");
        ModLoader.AddName(hairPotion, "Lotion capillaire de Papy Lennon");
        ModLoader.AddName(bouse, "Bouse");
    }
	
	public void AddRenderer(Map map)
    {
        map.put(EntityBob.class, new RenderBiped(new ModelBiped(), 0.5F));
        map.put(EntityFanta.class, new RenderBiped(new ModelBiped(), 0.5F));
        map.put(EntityJeanKevin.class, new RenderBiped(new ModelBiped(), 0.5F));
        map.put(EntityBotlennon.class, new RenderBiped(new ModelBiped(), 0.5F));
    }
	
	//déclaration des objets
    public static Item fantaGlasses;
    public static Item cobbleTie;
    public static Item toothBrush;
    public static Item stampCollection;
    public static Item bambooSword;
    public static Item hairPotion;
    public static Block bouse;
}