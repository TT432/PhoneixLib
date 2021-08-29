package com.nmmoc7.phoenixlib.data.dim.biome.feature;

import com.google.gson.JsonObject;

/**
 * @author DustW
 */
public class SeaPickleFeatureJsonBuilder extends BaseFeature<SeaPickleFeatureJsonBuilder> {
    public SeaPickleFeatureJsonBuilder() {
        super("sea_pickle");
    }

    /** 均匀分布整数，其中minBase为-10，maxBase为128，maxSpread为128。
     *
     * {@link com.nmmoc7.phoenixlib.data.dim.biome.feature.BaseFeature.UniformInt }*/
    public SeaPickleFeatureJsonBuilder count(JsonObject count) {
        config.add("count", count);
        return this;
    }
}
