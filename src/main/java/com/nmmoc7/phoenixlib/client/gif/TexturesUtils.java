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
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class TexturesUtils {
    public static TextureRenderType getPngRenderType(ResourceLocation file) throws IOException {
        PNGImageReader reader = (PNGImageReader) ImageIO.getImageReadersByFormatName("png").next();
        ImageInputStream in = FileUtils.getImageFile(file);
        reader.setInput(in);
        return createRenderType(reader.read(0));
    }

    public static TextureRenderType createRenderType(BufferedImage texture) throws IOException {
        ByteArrayOutputStream bs = new ByteArrayOutputStream();
        ImageOutputStream iOut = ImageIO.createImageOutputStream(bs);
        ImageIO.write(texture, "png", iOut);
        int frame = TexturesUtils.loadTexture(bs.toByteArray());
        return new TextureRenderType(frame);
    }

    public static int loadTexture(byte[] frame) throws IOException {
        NativeImage image = NativeImage.read(new ByteArrayInputStream(frame));

        int texture = TextureUtil.generateTextureId();

        TextureUtil.prepareImage(NativeImage.PixelFormatGLCode.RGBA,
                texture, 2, image.getWidth(), image.getHeight());

        image.uploadTextureSub(0, 0, 0,
                0, 0,
                image.getWidth(), image.getHeight(),
                true, true);

        GL30.glGenerateMipmap(GL11.GL_TEXTURE_2D);

        return texture;
    }
}
