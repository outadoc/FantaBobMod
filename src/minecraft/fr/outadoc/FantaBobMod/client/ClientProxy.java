package fr.outadoc.FantaBobMod.client;

import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import fr.outadoc.FantaBobMod.common.CommonProxy;
import fr.outadoc.FantaBobMod.common.EntityBob;
import fr.outadoc.FantaBobMod.common.EntityBotlennon;
import fr.outadoc.FantaBobMod.common.EntityFanta;
import fr.outadoc.FantaBobMod.common.EntityJeanKevin;

public class ClientProxy extends CommonProxy
{
	@Override
	public void addEntityRender()
	{
		RenderingRegistry.registerEntityRenderingHandler(EntityBob.class, new RenderBiped(new ModelBiped(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityFanta.class, new RenderBiped(new ModelBiped(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityJeanKevin.class, new RenderBiped(new ModelBiped(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityBotlennon.class, new RenderBiped(new ModelBiped(), 0.5F));
	}

}
