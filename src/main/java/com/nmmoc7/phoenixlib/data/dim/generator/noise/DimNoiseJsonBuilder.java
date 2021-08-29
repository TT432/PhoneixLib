package com.nmmoc7.phoenixlib.data.dim.generator.noise;

import com.google.gson.JsonObject;

/**
 * @author DustW
 */
public class DimNoiseJsonBuilder {
    JsonObject json;

    public DimNoiseJsonBuilder() {
        json.addProperty("type", "minecraft:noise");
    }

    /** seed：用于生成该维度的种子。通常情况下，这个值理应与世界种子一致，但也可以与之不同，不同时该维度就会使用这个值作为种子生成，而不会使用世界种子。[需要测试]
     * 可选 */
    public DimNoiseJsonBuilder seed(long seed) {
        json.addProperty("seed", seed);
        return this;
    }

    /** settings：地形生成器中使用的噪声设置。可以设为一个字符串以使用worldgen/noise_settings中的预设，
     * 或者可以设为一个包括自定义选项列表的复合标签。见自定义世界生成#噪声设置以获取详细信息。 */
    public DimNoiseJsonBuilder settings(String settings) {
        json.addProperty("settings", settings);
        return this;
    }

    /** json builder {@link com.nmmoc7.phoenixlib.data.dim.generator.noise.noise_setting.NoiseSettingJsonBuilder NoiseSettingJsonBuilder} */
    public DimNoiseJsonBuilder settings(JsonObject settings) {
        json.add("settings", settings);
        return this;
    }

    public DimNoiseJsonBuilder settings(settings settings) {
        json.addProperty("settings", settings.name);
        return this;
    }

    public enum settings {
        /** 主世界 */
        OVERWORLD("minecraft:overworld"),
        AMPLIFIED("minecraft:amplified"),
        NETHER("minecraft:nether"),
        CAVES("minecraft:caves"),
        END("minecraft:end"),
        FLOATING_ISLANDS("minecraft:floating_islands");

        String name;
        settings(String name) {
            this.name = name;
        }
    }

    /** json builder {@link com.nmmoc7.phoenixlib.data.dim.generator.noise.biome_source.BiomeSourceJsonBuilder BiomeSourceJsonBuilder} */
    public DimNoiseJsonBuilder biome_source(JsonObject biome_source) {
        json.add("biome_source", biome_source);
        return this;
    }

    public JsonObject build() {
        return json;
    }
}
