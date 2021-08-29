package com.nmmoc7.phoenixlib.data.dim.biome.feature;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

/**
 * @author DustW
 */
public class RandomSelectorFeatureJsonBuilder extends BaseFeature<RandomSelectorFeatureJsonBuilder> {
    public RandomSelectorFeatureJsonBuilder() {
        super("random_selector");
    }

    /** features:[需要测试] 一个可供从中随机选择的其他地物的列表 */
    JsonArray features = new JsonArray();

    /** feature: 该设置过的地物。 */
    public RandomSelectorFeatureJsonBuilder addFeature(String feature, float chance) {
        JsonObject featureJ = new JsonObject();
        featureJ.addProperty("feature", feature);
        featureJ.addProperty("chance", chance);
        features.add(featureJ);
        return this;
    }

    public RandomSelectorFeatureJsonBuilder addFeature(JsonObject feature, float chance) {
        JsonObject featureJ = new JsonObject();
        featureJ.add("feature", feature);
        featureJ.addProperty("chance", chance);
        features.add(featureJ);
        return this;
    }

    public RandomSelectorFeatureJsonBuilder defaultFeature(String feature) {
        config.addProperty("default", feature);
        return this;
    }

    public RandomSelectorFeatureJsonBuilder defaultFeature(JsonObject feature) {
        config.add("default", feature);
        return this;
    }

    @Override
    public JsonObject build() {
        config.add("features", features);
        return super.build();
    }
}
