package com.nmmoc7.phoenixlib.data.dim.biome.feature;

/**
 * @author DustW
 */
public class SeagrassFeatureJsonBuilder extends BaseFeature<SeagrassFeatureJsonBuilder> {
    public SeagrassFeatureJsonBuilder() {
        super("seagrass");
    }

    protected SeagrassFeatureJsonBuilder(String type) {
        super(type);
    }

    public SeagrassFeatureJsonBuilder probability(float probability) {
        config.addProperty("probability", probability);
        return this;
    }
}
