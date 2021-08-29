package com.nmmoc7.phoenixlib.data.dim.biome;

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
public class BiomeProvider implements IDataProvider {
    private static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().disableHtmlEscaping().create();
    private final DataGenerator generator;
    private static final HashMap<String, JsonObject> BIOME_JSONS = new HashMap<>();
    String modId;

    public BiomeProvider(String modId, DataGenerator generator) {
        this.generator = generator;
        this.modId = modId;
    }

    @Override
    public void act(DirectoryCache cache) throws IOException {
        addBiomes();

        Path output = generator.getOutputFolder();

        for (Map.Entry<String, JsonObject> json : BIOME_JSONS.entrySet()) {
            IDataProvider.save(GSON, cache, json.getValue(), getPath(output, new ResourceLocation(modId, json.getKey())));
        }
    }

    public static void addBiomes() {

    }

    public static void addBiome(String name, JsonObject dimJson) {
        BIOME_JSONS.put(name, dimJson);
    }

    private static Path getPath(Path path, ResourceLocation resourceLocation) {
        return path.resolve("data/" + resourceLocation.getNamespace() + "/worldgen/biome/" + resourceLocation.getPath() + ".json");
    }

    @Override
    public String getName() {
        return modId + ".Biome Provider";
    }
}
