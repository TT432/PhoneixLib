package com.nmmoc7.phoenixlib.client.gif;

import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.util.math.vector.Matrix4f;

/**
 * @author DustW
 */
public class TextureRenderer {
    public static void render(TextureRenderType renderType, IRenderTypeBuffer source, Matrix4f matrix, int color, int light) {
        render(renderType, source, matrix, color, light, true, false);
    }

    public static void render(TextureRenderType renderType, IRenderTypeBuffer source, Matrix4f matrix, int color, int light, boolean renderBack, boolean renderInGui) {
        final IVertexBuilder builder = source.getBuffer(renderType);

        if (renderInGui) {
            renderInGui(builder, matrix, color, light);
        }
        else {
            renderInWorld(builder, matrix, color, light, renderBack);
        }
    }

    public static void renderInWorld(IVertexBuilder builder, Matrix4f matrix, int color, int light, boolean renderBack) {
        int alpha = color >>> 24;
        if (alpha > 0) {
            int red = (color >> 16) & 255, green = (color >> 8) & 255, blue = color & 255;

            builder.pos(matrix, 0F, 1F / 256F, 1F).color(red, green, blue, alpha).tex(0F, 1F).lightmap(light).endVertex();
            builder.pos(matrix, 1F, 1F / 256F, 1F).color(red, green, blue, alpha).tex(1F, 1F).lightmap(light).endVertex();
            builder.pos(matrix, 1F, 1F / 256F, 0F).color(red, green, blue, alpha).tex(1F, 0F).lightmap(light).endVertex();
            builder.pos(matrix, 0F, 1F / 256F, 0F).color(red, green, blue, alpha).tex(0F, 0F).lightmap(light).endVertex();

            if (renderBack) {
                builder.pos(matrix, 0F, -1F / 256F, 0F).color(red, green, blue, alpha).tex(0F, 0F).lightmap(light).endVertex();
                builder.pos(matrix, 1F, -1F / 256F, 0F).color(red, green, blue, alpha).tex(1F, 0F).lightmap(light).endVertex();
                builder.pos(matrix, 1F, -1F / 256F, 1F).color(red, green, blue, alpha).tex(1F, 1F).lightmap(light).endVertex();
                builder.pos(matrix, 0F, -1F / 256F, 1F).color(red, green, blue, alpha).tex(0F, 1F).lightmap(light).endVertex();
            }
        }
    }

    public static void renderInGui(IVertexBuilder builder, Matrix4f matrix, int color, int light) {
        int alpha = color >>> 24;
        if (alpha > 0) {
            int red = (color >> 16) & 255, green = (color >> 8) & 255, blue = color & 255;

            builder.pos(matrix, 0F, 1F, 1F / 256F).color(red, green, blue, alpha).tex(0F, 1F).lightmap(light).endVertex();
            builder.pos(matrix, 1F, 1F, 1F / 256F).color(red, green, blue, alpha).tex(1F, 1F).lightmap(light).endVertex();
            builder.pos(matrix, 1F, 0F, 1F / 256F).color(red, green, blue, alpha).tex(1F, 0F).lightmap(light).endVertex();
            builder.pos(matrix, 0F, 0F, 1F / 256F).color(red, green, blue, alpha).tex(0F, 0F).lightmap(light).endVertex();
        }
    }
}
