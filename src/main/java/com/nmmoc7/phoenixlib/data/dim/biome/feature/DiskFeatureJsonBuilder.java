package com.nmmoc7.phoenixlib.data.dim.biome.feature;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

/**
 * @author DustW
 */
public class DiskFeatureJsonBuilder extends BaseFeature<DiskFeatureJsonBuilder> {
    public DiskFeatureJsonBuilder() {
        super("disk");
    }

    protected DiskFeatureJsonBuilder(String type) {
        super(type);
    }

    /**
     *  state:[需要测试]
     *      一个方块状态。
     *      Name: 该方块的命名空间ID。
     */
    public DiskFeatureJsonBuilder state(String name) {
        JsonObject state = new JsonObject();
        state.addProperty("Name", name);
        config.add("state", state);
        return this;
    }

    /**
     * {@link com.nmmoc7.phoenixlib.data.dim.biome.feature.BaseFeature.UniformInt}
     *  radius:[需要测试]
     *      均匀分布整数，其中minBase为0，maxBase为4，maxSpread为4。
     */
    public DiskFeatureJsonBuilder radius(JsonObject radius) {
        config.add("radius", radius);
        return this;
    }

    /** half_height:[需要测试] 取值必须为0到4（含0和4）。 */
    public DiskFeatureJsonBuilder half_height(int half_height) {
        config.addProperty("half_height", half_height);
        return this;
    }

    /**
     *  targets:[需要测试]
     *      一个方块状态。
     *      Name: 该方块的命名空间ID。
     *      Properties: 方块状态。
     *          state: 一个方块状态键及对应值。
     */
    JsonArray targets = new JsonArray();

    public DiskFeatureJsonBuilder addTarget(String name, String... state) {
        addBlockState(targets, name, state);
        return this;
    }

    @Override
    public JsonObject build() {
        config.add("targets", targets);
        return super.build();
    }
}
