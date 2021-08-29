package com.nmmoc7.phoenixlib.data.dim;

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
public class DimProvider implements IDataProvider {
    private static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().disableHtmlEscaping().create();
    private final DataGenerator generator;
    private static final HashMap<String, JsonObject> DIM_JSONS = new HashMap<>();
    String modId;

    public DimProvider(String modId, DataGenerator generator) {
        this.generator = generator;
        this.modId = modId;
    }

    @Override
    public void act(DirectoryCache cache) throws IOException {
        addDimensionTypes();

        Path output = generator.getOutputFolder();

        for (Map.Entry<String, JsonObject> json: DIM_JSONS.entrySet()) {
            IDataProvider.save(GSON, cache, json.getValue(), getPath(output, new ResourceLocation(modId, json.getKey())));
        }
    }

    public static void addDimensionTypes() {

    }

    public static void addDimension(String name, JsonObject dimJson) {
        DIM_JSONS.put(name, dimJson);
    }

    private static Path getPath(Path path, ResourceLocation resourceLocation) {
        return path.resolve("data/" + resourceLocation.getNamespace() + "/dimension/" + resourceLocation.getPath() + ".json");
    }

    @Override
    public String getName() {
        return modId + ".Dimension Provider";
    }
}
