package com.nmmoc7.phoenixlib.data.dim.biome.feature;

import com.google.gson.JsonObject;

/**
 * @author DustW
 */
public class BasaltColumnsFeatureJsonBuilder extends BaseFeature<BasaltColumnsFeatureJsonBuilder> {
    public BasaltColumnsFeatureJsonBuilder() {
        super("basalt_columns");
    }

    /** {@link com.nmmoc7.phoenixlib.data.dim.biome.feature.BaseFeature.UniformInt} */
    public BasaltColumnsFeatureJsonBuilder reach(JsonObject reach) {
        config.add("reach", reach);
        return this;
    }

    /** {@link com.nmmoc7.phoenixlib.data.dim.biome.feature.BaseFeature.UniformInt} */
    public BasaltColumnsFeatureJsonBuilder height(JsonObject height) {
        config.add("height", height);
        return this;
    }
}
