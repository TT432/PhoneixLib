package com.nmmoc7.phoenixlib.data.dim.biome.feature;

import com.google.gson.JsonObject;

/**
 * @author DustW
 */
public class HugeRedMushroomFeatureJsonBuilder extends BaseFeature<HugeRedMushroomFeatureJsonBuilder> {
    public HugeRedMushroomFeatureJsonBuilder() {
        super("huge_red_mushroom");
    }

    protected HugeRedMushroomFeatureJsonBuilder(String type) {
        super(type);
    }

    /***
     *  cap_provider:[需要测试] 组成蘑菇伞盖的方块。
     *      方块状态声明（见自定义世界生成#数据种类）。
     */
    public HugeRedMushroomFeatureJsonBuilder cap_provider(JsonObject cap_provider) {
        config.add("cap_provider", cap_provider);
        return this;
    }

    /**
     *  stem_provider:[需要测试] 组成蘑菇伞柄的方块。
     *      方块状态声明（见自定义世界生成#数据种类）。
     */
    public HugeRedMushroomFeatureJsonBuilder stem_provider(JsonObject stem_provider) {
        config.add("stem_provider", stem_provider);
        return this;
    }

    /**
     *  foliage_radius (可选):[需要测试] 菌盖的大小，若不填写，默认值为2。
     */
    public HugeRedMushroomFeatureJsonBuilder foliage_radius(int foliage_radius) {
        config.addProperty("foliage_radius", foliage_radius);
        return this;
    }
}
