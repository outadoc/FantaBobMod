package fr.outadoc.FantaBobMod.client;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.event.ForgeSubscribe;
import fr.outadoc.FantaBobMod.common.FantaBobMod;

public class FTMEventSound 
{
    @ForgeSubscribe
    public void onSound(SoundLoadEvent event)
    {
        try
        {
            event.manager.soundPoolSounds.addSound("fantabob/bob1.ogg", FantaBobMod.class.getResource("/mods/FantaBobMod/sounds/mobs/bob1.ogg"));
            event.manager.soundPoolSounds.addSound("fantabob/bob2.ogg", FantaBobMod.class.getResource("/mods/FantaBobMod/sounds/mobs/bob2.ogg"));
            event.manager.soundPoolSounds.addSound("fantabob/bob3.ogg", FantaBobMod.class.getResource("/mods/FantaBobMod/sounds/mobs/bob3.ogg"));
            event.manager.soundPoolSounds.addSound("fantabob/bob4.ogg", FantaBobMod.class.getResource("/mods/FantaBobMod/sounds/mobs/bob4.ogg"));
            event.manager.soundPoolSounds.addSound("fantabob/bob5.ogg", FantaBobMod.class.getResource("/mods/FantaBobMod/sounds/mobs/bob5.ogg"));
            event.manager.soundPoolSounds.addSound("fantabob/bob6.ogg", FantaBobMod.class.getResource("/mods/FantaBobMod/sounds/mobs/bob6.ogg"));
            event.manager.soundPoolSounds.addSound("fantabob/bob7.ogg", FantaBobMod.class.getResource("/mods/FantaBobMod/sounds/mobs/bob7.ogg"));
            event.manager.soundPoolSounds.addSound("fantabob/bob8.ogg", FantaBobMod.class.getResource("/mods/FantaBobMod/sounds/mobs/bob8.ogg"));
            event.manager.soundPoolSounds.addSound("fantabob/bob9.ogg", FantaBobMod.class.getResource("/mods/FantaBobMod/sounds/mobs/bob9.ogg"));
            event.manager.soundPoolSounds.addSound("fantabob/bob10.ogg", FantaBobMod.class.getResource("/mods/FantaBobMod/sounds/mobs/bob10.ogg"));
            event.manager.soundPoolSounds.addSound("fantabob/bob11.ogg", FantaBobMod.class.getResource("/mods/FantaBobMod/sounds/mobs/bob11.ogg"));
            event.manager.soundPoolSounds.addSound("fantabob/bobburn1.ogg", FantaBobMod.class.getResource("/mods/FantaBobMod/sounds/mobs/bobburn1.ogg"));
            event.manager.soundPoolSounds.addSound("fantabob/bobburn2.ogg", FantaBobMod.class.getResource("/mods/FantaBobMod/sounds/mobs/bobburn2.ogg"));
            event.manager.soundPoolSounds.addSound("fantabob/bobdeath1.ogg", FantaBobMod.class.getResource("/mods/FantaBobMod/sounds/mobs/bobdeath1.ogg"));
            event.manager.soundPoolSounds.addSound("fantabob/bobdeath2.ogg", FantaBobMod.class.getResource("/mods/FantaBobMod/sounds/mobs/bobdeath2.ogg"));
            event.manager.soundPoolSounds.addSound("fantabob/bobdeath3.ogg", FantaBobMod.class.getResource("/mods/FantaBobMod/sounds/mobs/bobdeath3.ogg"));
            event.manager.soundPoolSounds.addSound("fantabob/bobdeath4.ogg", FantaBobMod.class.getResource("/mods/FantaBobMod/sounds/mobs/bobdeath4.ogg"));
            event.manager.soundPoolSounds.addSound("fantabob/bobdeath5.ogg", FantaBobMod.class.getResource("/mods/FantaBobMod/sounds/mobs/bobdeath5.ogg"));
            event.manager.soundPoolSounds.addSound("fantabob/bobdeath6.ogg", FantaBobMod.class.getResource("/mods/FantaBobMod/sounds/mobs/bobdeath6.ogg"));
            event.manager.soundPoolSounds.addSound("fantabob/bobhurt1.ogg", FantaBobMod.class.getResource("/mods/FantaBobMod/sounds/mobs/bobhurt1.ogg"));
            event.manager.soundPoolSounds.addSound("fantabob/bobhurt2.ogg", FantaBobMod.class.getResource("/mods/FantaBobMod/sounds/mobs/bobhurt2.ogg"));
            event.manager.soundPoolSounds.addSound("fantabob/bobhurt3.ogg", FantaBobMod.class.getResource("/mods/FantaBobMod/sounds/mobs/bobhurt3.ogg"));
            event.manager.soundPoolSounds.addSound("fantabob/bobhurt4.ogg", FantaBobMod.class.getResource("/mods/FantaBobMod/sounds/mobs/bobhurt4.ogg"));
            event.manager.soundPoolSounds.addSound("fantabob/bobhurt5.ogg", FantaBobMod.class.getResource("/mods/FantaBobMod/sounds/mobs/bobhurt5.ogg"));
            
            event.manager.soundPoolSounds.addSound("fantabob/botlennon1.ogg", FantaBobMod.class.getResource("/mods/FantaBobMod/sounds/mobs/botlennon1.ogg"));
            event.manager.soundPoolSounds.addSound("fantabob/botlennon2.ogg", FantaBobMod.class.getResource("/mods/FantaBobMod/sounds/mobs/botlennon2.ogg"));
            event.manager.soundPoolSounds.addSound("fantabob/botlennon3.ogg", FantaBobMod.class.getResource("/mods/FantaBobMod/sounds/mobs/botlennon3.ogg"));
            event.manager.soundPoolSounds.addSound("fantabob/botlennon4.ogg", FantaBobMod.class.getResource("/mods/FantaBobMod/sounds/mobs/botlennon4.ogg"));
            
            event.manager.soundPoolSounds.addSound("fantabob/fanta1.ogg", FantaBobMod.class.getResource("/mods/FantaBobMod/sounds/mobs/fanta1.ogg"));
            event.manager.soundPoolSounds.addSound("fantabob/fanta2.ogg", FantaBobMod.class.getResource("/mods/FantaBobMod/sounds/mobs/fanta2.ogg"));
            event.manager.soundPoolSounds.addSound("fantabob/fanta3.ogg", FantaBobMod.class.getResource("/mods/FantaBobMod/sounds/mobs/fanta3.ogg"));
            event.manager.soundPoolSounds.addSound("fantabob/fanta4.ogg", FantaBobMod.class.getResource("/mods/FantaBobMod/sounds/mobs/fanta4.ogg"));
            event.manager.soundPoolSounds.addSound("fantabob/fanta5.ogg", FantaBobMod.class.getResource("/mods/FantaBobMod/sounds/mobs/fanta5.ogg"));
            event.manager.soundPoolSounds.addSound("fantabob/fantadeath1.ogg", FantaBobMod.class.getResource("/mods/FantaBobMod/sounds/mobs/fantadeath1.ogg"));
            event.manager.soundPoolSounds.addSound("fantabob/fantadeath2.ogg", FantaBobMod.class.getResource("/mods/FantaBobMod/sounds/mobs/fantadeath2.ogg"));
            event.manager.soundPoolSounds.addSound("fantabob/fantadeath3.ogg", FantaBobMod.class.getResource("/mods/FantaBobMod/sounds/mobs/fantadeath3.ogg"));
            event.manager.soundPoolSounds.addSound("fantabob/fantadeath4.ogg", FantaBobMod.class.getResource("/mods/FantaBobMod/sounds/mobs/fantadeath4.ogg"));
            event.manager.soundPoolSounds.addSound("fantabob/fantahurt1.ogg", FantaBobMod.class.getResource("/mods/FantaBobMod/sounds/mobs/fantahurt1.ogg"));
            event.manager.soundPoolSounds.addSound("fantabob/fantahurt2.ogg", FantaBobMod.class.getResource("/mods/FantaBobMod/sounds/mobs/fantahurt2.ogg"));
            
            event.manager.soundPoolSounds.addSound("fantabob/kevin1.ogg", FantaBobMod.class.getResource("/mods/FantaBobMod/sounds/mobs/kevin1.ogg"));
            event.manager.soundPoolSounds.addSound("fantabob/kevin2.ogg", FantaBobMod.class.getResource("/mods/FantaBobMod/sounds/mobs/kevin2.ogg"));
            event.manager.soundPoolSounds.addSound("fantabob/kevin3.ogg", FantaBobMod.class.getResource("/mods/FantaBobMod/sounds/mobs/kevin3.ogg"));
            event.manager.soundPoolSounds.addSound("fantabob/kevindeath.ogg", FantaBobMod.class.getResource("/mods/FantaBobMod/sounds/mobs/kevindeath.ogg"));
            event.manager.soundPoolSounds.addSound("fantabob/kevinhurt1.ogg", FantaBobMod.class.getResource("/mods/FantaBobMod/sounds/mobs/kevinhurt1.ogg"));
            
            event.manager.soundPoolSounds.addSound("fantabob/toothbrush.ogg", FantaBobMod.class.getResource("/mods/FantaBobMod/sounds/items/toothbrush.ogg"));
            
            event.manager.soundPoolSounds.addSound("step/bouse1.ogg", FantaBobMod.class.getResource("/mods/FantaBobMod/sounds/step/bouse1.ogg"));
            event.manager.soundPoolSounds.addSound("step/bouse2.ogg", FantaBobMod.class.getResource("/mods/FantaBobMod/sounds/step/bouse2.ogg"));
            
            File Magabond = new File(Minecraft.getMinecraftDir() + "/resources/mod/streaming/Magabond.ogg");
            
            if (!Magabond.exists())
            {
            	downloadsounds("http://dl.mcnanotech.fr/robin4002/mods/FantaBobMod/Magabond.ogg");
            }
            
            FantaBobMod.FBMlog.info("sound loaded");
        }
        
        catch (Exception e)
        {
            System.err.println("Failed to register sounds.");
        }
    }

	private void downloadsounds(String url) throws Exception
    {
		String fAddress = url;
		String localFileName = "Magabond.ogg";
		String destinationDir = Minecraft.getMinecraftDir() + "/resources/mod/streaming";
		OutputStream outStream = null;
		URLConnection uCon = null;
		InputStream is = null;
		File tmp = new File(destinationDir);

		if(!tmp.exists())
		{
			tmp.mkdir();
		}

		try
		{
			URL Url;
			byte[] buf;
			int ByteRead,ByteWritten=0;
			Url = new URL(fAddress);
			outStream = new BufferedOutputStream(new FileOutputStream(destinationDir+"/"+localFileName));

			uCon = Url.openConnection();
			is = uCon.getInputStream();
			buf = new byte[1024]; while ((ByteRead = is.read(buf)) != -1)
			{
				outStream.write(buf, 0, ByteRead);
				ByteWritten += ByteRead;
			}
			
			is.close();
			outStream.close();
		}
		
		catch (Exception e)
		{ 
			e.printStackTrace(); 
		}
    }
}
