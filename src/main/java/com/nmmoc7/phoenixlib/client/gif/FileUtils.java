package com.nmmoc7.phoenixlib.client.gif;

import net.minecraft.client.Minecraft;
import net.minecraft.resources.IResource;
import net.minecraft.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import java.io.IOException;

public class FileUtils {
    public static ImageInputStream getImageFile(ResourceLocation file) {
        IResourceManager a = Minecraft.getInstance().getResourceManager();
        try {
            IResource b = a.getResource(file);
            return ImageIO.createImageInputStream(b.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
