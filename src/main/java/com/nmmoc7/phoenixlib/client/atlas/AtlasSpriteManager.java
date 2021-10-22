package com.nmmoc7.phoenixlib.client.atlas;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.model.ModelBakery;
import net.minecraft.client.renderer.model.RenderMaterial;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.Set;

/**
 * @author DustW
 */
public abstract class AtlasSpriteManager {
    private final ResourceLocation ATLAS;
    private final ArrayList<ResourceLocation> TEXTURES;

    public AtlasSpriteManager(ResourceLocation atlasName) {
        ATLAS = atlasName;
        TEXTURES = new ArrayList<>();
        register();
        makeAtlas();
    }

    private void makeAtlas() {
        Set<RenderMaterial> material = ModelBakery.LOCATIONS_BUILTIN_TEXTURES;
        TEXTURES.forEach(texture -> material.add(new RenderMaterial(ATLAS, texture)));
    }

    public final TextureAtlasSprite getSprite(ResourceLocation texture) {
        return Minecraft.getInstance()
                .getModelManager()
                .getAtlasTexture(ATLAS)
                .getSprite(texture);
    }

    /** 省略开头的textures和结尾的.png */
    protected final void add(ResourceLocation texture) {
        TEXTURES.add(texture);
    }

    /** 在这个方法内注册所有贴图 */
    protected abstract void register();
}
