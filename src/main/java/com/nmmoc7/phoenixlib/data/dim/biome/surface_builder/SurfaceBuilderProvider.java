package com.nmmoc7.phoenixlib.data.dim.biome.surface_builder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DirectoryCache;
import net.minecraft.data.IDataProvider;
import net.minecraft.util.ResourceLocation;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

/**
 * @author DustW
 */
public class SurfaceBuilderProvider implements IDataProvider {
    private static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().disableHtmlEscaping().create();
    private final DataGenerator generator;
    private static final HashMap<String, JsonObject> SURFACE_BUILDERS = new HashMap<>();
    String modId;

    public SurfaceBuilderProvider(String modId, DataGenerator generator) {
        this.generator = generator;
        this.modId = modId;
    }

    @Override
    public void act(DirectoryCache cache) throws IOException {
        addSurfaceBuilders();

        Path output = generator.getOutputFolder();

        for (Map.Entry<String, JsonObject> json : SURFACE_BUILDERS.entrySet()) {
            IDataProvider.save(GSON, cache, json.getValue(), getPath(output, new ResourceLocation(modId, json.getKey())));
        }
    }

    public static void addSurfaceBuilders() {

    }

    public static void addSurfaceBuilder(String name, JsonObject dimJson) {
        SURFACE_BUILDERS.put(name, dimJson);
    }

    private static Path getPath(Path path, ResourceLocation resourceLocation) {
        return path.resolve("data/" + resourceLocation.getNamespace() + "/worldgen/configured_surface_builder/" + resourceLocation.getPath() + ".json");
    }

    @Override
    public String getName() {
        return modId + ".Surface Builder Provider";
    }
}
