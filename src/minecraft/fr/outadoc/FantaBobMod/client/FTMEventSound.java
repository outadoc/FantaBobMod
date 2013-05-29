package fr.outadoc.FantaBobMod.client;

import fr.outadoc.FantaBobMod.common.FantaBobMod;
import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.event.ForgeSubscribe;

public class FTMEventSound 
{
    @ForgeSubscribe
    public void onSound(SoundLoadEvent event)
    {
        try
        {
            event.manager.soundPoolSounds.addSound("bob1.ogg", FantaBobMod.class.getResource("/mods/FantaBobMod/Sounds/fantabob/bob1.ogg"));
            
        }
        catch (Exception e)
        {
            System.err.println("Failed to register one or more sounds.");
        }
    }

}
