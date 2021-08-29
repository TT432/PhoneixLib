package com.nmmoc7.phoenixlib.data.dim.biome.feature;

import com.google.gson.JsonObject;

/**
 * @author DustW
 */
public class BlockPileFeatureJsonBuilder extends BaseFeature<BlockPileFeatureJsonBuilder> {
    public BlockPileFeatureJsonBuilder() {
        super("block_pile");
    }

    protected BlockPileFeatureJsonBuilder(String type) {
        super(type);
    }

    /**
     *  state_provider:[需要测试] 要使用的方块。
     * 方块状态声明（见自定义世界生成#数据种类）。
     */
    public BlockPileFeatureJsonBuilder state_provider(JsonObject state_provider) {
        config.add("state_provider", state_provider);
        return this;
    }
}
