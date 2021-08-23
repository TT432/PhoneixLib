package com.nmmoc7.phoenixlib.client.gif;

import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.sun.imageio.plugins.gif.GIFImageReader;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.texture.NativeImage;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Matrix4f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.ImageOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class GIFInstance {
    public final int maxFrame;
    final ResourceLocation file;
    int[] frameList;
    GIFRenderType[] gifRenderTypes;

    GIFInstance(ResourceLocation file) {
        this.file = file;
        maxFrame = getMaxFrame();
        frameList = new int[maxFrame];
        gifRenderTypes = new GIFRenderType[maxFrame];

        GIFImageReader reader = (GIFImageReader) ImageIO.getImageReadersByFormatName("gif").next();
        ImageInputStream in = GIFUtils.getGIFFile(file);
        reader.setInput(in);

        for (int i = 0; i < maxFrame; i++) {
            try {
                ByteArrayOutputStream bs = new ByteArrayOutputStream();
                ImageOutputStream iOut = ImageIO.createImageOutputStream(bs);
                ImageIO.write(reader.read(i), "png", iOut);
                frameList[i] = loadTexture(bs.toByteArray());
                gifRenderTypes[i] = new GIFRenderType(frameList[i]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void render(int frame, IRenderTypeBuffer source, Matrix4f matrix, int color, int light) {
        int alpha = color >>> 24;
        if (alpha > 0) {
            int red = (color >> 16) & 255, green = (color >> 8) & 255, blue = color & 255;
            final IVertexBuilder builder = source.getBuffer(gifRenderTypes[frame]);

            builder.pos(matrix, 0F, 1F / 256F, 1F)
                    .color(red, green, blue, alpha).tex(0F, 1F).lightmap(light).endVertex();
            builder.pos(matrix, 1F, 1F / 256F, 1F)
                    .color(red, green, blue, alpha).tex(1F, 1F).lightmap(light).endVertex();
            builder.pos(matrix, 1F, 1F / 256F, 0F)
                    .color(red, green, blue, alpha).tex(1F, 0F).lightmap(light).endVertex();
            builder.pos(matrix, 0F, 1F / 256F, 0F)
                    .color(red, green, blue, alpha).tex(0F, 0F).lightmap(light).endVertex();

            builder.pos(matrix, 0F, -1F / 256F, 0F)
                    .color(red, green, blue, alpha).tex(0F, 0F).lightmap(light).endVertex();
            builder.pos(matrix, 1F, -1F / 256F, 0F)
                    .color(red, green, blue, alpha).tex(1F, 0F).lightmap(light).endVertex();
            builder.pos(matrix, 1F, -1F / 256F, 1F)
                    .color(red, green, blue, alpha).tex(1F, 1F).lightmap(light).endVertex();
            builder.pos(matrix, 0F, -1F / 256F, 1F)
                    .color(red, green, blue, alpha).tex(0F, 1F).lightmap(light).endVertex();
        }
    }

    private Integer getMaxFrame() {
        try {
            GIFImageReader reader = (GIFImageReader) ImageIO.getImageReadersByFormatName("gif").next();
            ImageInputStream in = GIFUtils.getGIFFile(file);
            reader.setInput(in, false, false);
            int maxFrame = reader.getNumImages(true);
            in.close();
            return maxFrame;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private int loadTexture(byte[] frame) throws IOException {
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
