package com.nmmoc7.phoenixlib.data.dim.biome.feature;

/**
 * @author DustW
 */
public class FillLayerFeatureJsonBuilder extends BaseFeature<FillLayerFeatureJsonBuilder> {
    public FillLayerFeatureJsonBuilder() {
        super("fill_layer");
    }

    /** height:[需要测试] 要填充的层。 */
    public FillLayerFeatureJsonBuilder height(int height) {
        config.addProperty("height", height);
        return this;
    }

    /** state:[需要测试] 用于填充的方块。 */
    public FillLayerFeatureJsonBuilder state(String name, String... states) {
        return addBlockState(config, "state", name, states);
    }
}
