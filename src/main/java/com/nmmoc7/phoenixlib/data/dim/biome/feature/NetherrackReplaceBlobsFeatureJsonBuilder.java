package com.nmmoc7.phoenixlib.data.dim.biome.feature;

import com.google.gson.JsonObject;

/**
 * @author DustW
 */
public class NetherrackReplaceBlobsFeatureJsonBuilder extends BaseFeature<NetherrackReplaceBlobsFeatureJsonBuilder> {
    public NetherrackReplaceBlobsFeatureJsonBuilder() {
        super("netherrack_replace_blobs");
    }

    public NetherrackReplaceBlobsFeatureJsonBuilder target(String name, String... states) {
        return addBlockState(config, "target", name, states);
    }

    public NetherrackReplaceBlobsFeatureJsonBuilder state(String name, String... states) {
        return addBlockState(config, "state", name, states);
    }

    /** {@link com.nmmoc7.phoenixlib.data.dim.biome.feature.BaseFeature.UniformInt} */
    public NetherrackReplaceBlobsFeatureJsonBuilder radius(JsonObject radius) {
        config.add("radius", radius);
        return this;
    }
}
