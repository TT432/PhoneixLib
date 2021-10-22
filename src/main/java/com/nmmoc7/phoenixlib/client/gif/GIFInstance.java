package com.nmmoc7.phoenixlib.client.gif;

import com.sun.imageio.plugins.gif.GIFImageReader;
import net.minecraft.util.ResourceLocation;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import java.io.IOException;

/**
 * @author DustW
 */
public class GIFInstance {
    public final int maxFrame;
    int[] frames;
    final ResourceLocation file;

    public GIFInstance(ResourceLocation file) {
        this.file = file;
        maxFrame = getMaxFrame();
        frames = new int[maxFrame];

        GIFImageReader reader = (GIFImageReader) ImageIO.getImageReadersByFormatName("gif").next();
        ImageInputStream in = FileUtils.getImageFile(file);
        reader.setInput(in);

        try {
            for (int i = 0; i < maxFrame; i++) {
                frames[i] = TexturesUtils.loadTexture(reader.read(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public TextureRenderType getFrame(int index) {
        return TexturesUtils.createRenderType(frames[index]);
    }

    public int getMaxFrame() {
        if (maxFrame == 0) {
            loadMaxFrame();
        }

        return maxFrame;
    }

    private int loadMaxFrame() {
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
