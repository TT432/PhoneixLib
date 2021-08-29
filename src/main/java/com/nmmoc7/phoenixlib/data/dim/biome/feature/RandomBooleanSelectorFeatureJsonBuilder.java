package com.nmmoc7.phoenixlib.data.dim.biome.feature;

import com.google.gson.JsonObject;

/**
 * @author DustW
 */
public class RandomBooleanSelectorFeatureJsonBuilder extends BaseFeature<RandomBooleanSelectorFeatureJsonBuilder> {
    public RandomBooleanSelectorFeatureJsonBuilder() {
        super("random_boolean_selector");
    }

    public RandomBooleanSelectorFeatureJsonBuilder feature_true(String feature) {
        config.addProperty("feature_true", feature);
        return this;
    }

    public RandomBooleanSelectorFeatureJsonBuilder feature_true(JsonObject feature) {
        config.add("feature_true", feature);
        return this;
    }

    public RandomBooleanSelectorFeatureJsonBuilder feature_false(String feature) {
        config.addProperty("feature_false", feature);
        return this;
    }

    public RandomBooleanSelectorFeatureJsonBuilder feature_false(JsonObject feature) {
        config.add("feature_false", feature);
        return this;
    }
}
