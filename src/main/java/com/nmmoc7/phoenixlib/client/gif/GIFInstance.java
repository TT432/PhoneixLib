package com.nmmoc7.phoenixlib.client.gif;

import com.sun.imageio.plugins.gif.GIFImageReader;
import net.minecraft.util.ResourceLocation;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import java.io.IOException;

public class GIFInstance {
    public final int maxFrame;
    final ResourceLocation file;
    TextureRenderType[] gifRenderTypes;

    public GIFInstance(ResourceLocation file) {
        this.file = file;
        maxFrame = getMaxFrame();
        gifRenderTypes = new TextureRenderType[maxFrame];

        GIFImageReader reader = (GIFImageReader) ImageIO.getImageReadersByFormatName("gif").next();
        ImageInputStream in = FileUtils.getImageFile(file);
        reader.setInput(in);

        try {
            for (int i = 0; i < maxFrame; i++) {
                gifRenderTypes[i] = TexturesUtils.createRenderType(reader.read(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int getMaxFrame() {
        try {
            GIFImageReader reader = (GIFImageReader) ImageIO.getImageReadersByFormatName("gif").next();
            ImageInputStream in = FileUtils.getImageFile(file);
            reader.setInput(in, false, false);
            int maxFrame = reader.getNumImages(true);
            in.close();
            return maxFrame;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return 0;
    }
}
