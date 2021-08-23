package com.nmmoc7.phoenixlib.client.gif;

import com.nmmoc7.phoenixlib.event.client.GIFRegisterEvent;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import java.util.HashMap;
import java.util.Map;

@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class GIFLoader {
    public static final GIFLoader INSTANCE = new GIFLoader();
    private final Map<ResourceLocation, GIFInstance> gifs = new HashMap<>();

    private GIFLoader() {}

    public void addGIF(GIFInstance gif) {
        gifs.put(gif.file, gif);
    }

    public GIFInstance getGIF(ResourceLocation name) {
        return gifs.get(name);
    }

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            MinecraftForge.EVENT_BUS.post(new GIFRegisterEvent());
        });
    }
}
