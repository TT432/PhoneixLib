package com.nmmoc7.phoenixlib.client.gif;

import com.sun.imageio.plugins.gif.GIFImageReader;
import com.sun.imageio.plugins.png.PNGImageReader;
import net.minecraft.util.ResourceLocation;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import java.io.IOException;

/**
 * @author DustW
 */
public class PngInstance {
    final ResourceLocation file;
    int texture;

    public PngInstance(ResourceLocation file) {
        this.file = file;

        PNGImageReader reader = (PNGImageReader) ImageIO.getImageReadersByFormatName("png").next();
        ImageInputStream in = FileUtils.getImageFile(file);
        reader.setInput(in);

        try {
            this.texture = TexturesUtils.loadTexture(reader.read(0));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public TextureRenderType getRenderType() {
        return new TextureRenderType(texture);
    }
}
