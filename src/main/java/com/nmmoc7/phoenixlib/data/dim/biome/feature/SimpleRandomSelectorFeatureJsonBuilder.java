package com.nmmoc7.phoenixlib.data.dim.biome.feature;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

/**
 * @author DustW
 */
public class SimpleRandomSelectorFeatureJsonBuilder extends BaseFeature<SimpleRandomSelectorFeatureJsonBuilder> {
    public SimpleRandomSelectorFeatureJsonBuilder() {
        super("simple_random_selector");
    }

    JsonArray features = new JsonArray();

    public SimpleRandomSelectorFeatureJsonBuilder addFeature(String feature) {
        features.add(feature);
        return this;
    }

    public SimpleRandomSelectorFeatureJsonBuilder addFeature(JsonObject feature) {
        features.add(feature);
        return this;
    }

    @Override
    public JsonObject build() {
        config.add("features", features);
        return super.build();
    }
}
