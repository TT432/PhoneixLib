package com.nmmoc7.phoenixlib.event.client;

import com.nmmoc7.phoenixlib.client.gif.GIFInstance;
import com.nmmoc7.phoenixlib.client.gif.GIFLoader;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.fml.event.lifecycle.IModBusEvent;

/**
 * run in Render Thread
 */
public class GIFRegisterEvent extends Event implements IModBusEvent {
    public void register(GIFInstance gif) {
        GIFLoader.INSTANCE.addGIF(gif);
    }

    public void registerAll(GIFInstance... gif) {
        for (GIFInstance gifInstance : gif) {
            GIFLoader.INSTANCE.addGIF(gifInstance);
        }
    }
}
