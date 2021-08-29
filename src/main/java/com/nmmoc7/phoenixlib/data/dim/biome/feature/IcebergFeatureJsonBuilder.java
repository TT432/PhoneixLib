package com.nmmoc7.phoenixlib.data.dim.biome.feature;

import com.google.gson.JsonObject;

/**
 * @author DustW
 */
public class IcebergFeatureJsonBuilder extends BaseFeature<IcebergFeatureJsonBuilder> {
    public IcebergFeatureJsonBuilder() {
        super("iceberg");
    }

    protected IcebergFeatureJsonBuilder(String type) {
        super(type);
    }

    /**
     *  state:[需要测试]
     *      一个方块状态。
     *      Name: 该方块的命名空间ID。
     */
    public IcebergFeatureJsonBuilder state(String name) {
        JsonObject state = new JsonObject();
        state.addProperty("Name", name);
        config.add("state", state);
        return this;
    }
}
