package com.nmmoc7.phoenixlib.data.dim.biome.feature;

import com.google.gson.JsonObject;

/**
 * @author DustW
 */
public class DeltaFeatureJsonBuilder extends BaseFeature<DeltaFeatureJsonBuilder> {
    public DeltaFeatureJsonBuilder() {
        super("delta_feature");
    }

    /** contents:[需要测试] 组成三角洲的方块。 */
    public DeltaFeatureJsonBuilder contents(String name, String... states) {
        return addBlockState(config, "contents", name, states);
    }

    /** rim:[需要测试] 三角洲边缘的方块。 */
    public DeltaFeatureJsonBuilder rim(String name, String... states) {
        return addBlockState(config, "rim", name, states);
    }

    /** {@link com.nmmoc7.gui_test.data.dim.biome.feature.BaseFeature.UniformInt}
     *
     * 匀分布整数，其中minBase为0，maxBase为8，maxSpread为8。*/
    public DeltaFeatureJsonBuilder size(JsonObject size) {
        config.add("size", size);
        return this;
    }

    /** {@link com.nmmoc7.gui_test.data.dim.biome.feature.BaseFeature.UniformInt}
     *
     * 均匀分布整数，其中minBase为0，maxBase为8，maxSpread为8。*/
    public DeltaFeatureJsonBuilder rim_size(JsonObject rim_size) {
        config.add("rim_size", rim_size);
        return this;
    }
}
