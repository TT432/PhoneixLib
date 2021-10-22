package com.nmmoc7.phoenixlib.client.gif;

import com.sun.imageio.plugins.png.PNGImageReader;
import net.minecraft.client.renderer.texture.NativeImage;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.*;

public class TexturesUtils {
    public static TextureRenderType createRenderType(int texture) {
        return new TextureRenderType(texture);
    }

    public static int loadTexture(BufferedImage texture) throws IOException {
        ByteArrayOutputStream bs = new ByteArrayOutputStream();
        ImageOutputStream iOut = ImageIO.createImageOutputStream(bs);
        ImageIO.write(texture, "png", iOut);
        return TexturesUtils.loadTexture(bs.toByteArray());
    }

    public static int loadTexture(byte[] frame) throws IOException {
        NativeImage image = NativeImage.read(new ByteArrayInputStream(frame));

        int texture = TextureUtil.generateTextureId();

        TextureUtil.prepareImage(texture, 0, image.getWidth(), image.getHeight());
        image.uploadTextureSub(0, 0, 0,
                0, 0,
                image.getWidth(), image.getHeight(),
                false, false,
                false, true);

        return texture;
    }
}
