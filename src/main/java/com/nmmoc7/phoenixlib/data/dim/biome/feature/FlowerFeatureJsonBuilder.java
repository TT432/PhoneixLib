package com.nmmoc7.phoenixlib.data.dim.biome.feature;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import javax.annotation.Nullable;

/**
 * @author DustW
 */
public class FlowerFeatureJsonBuilder extends BaseFeature<FlowerFeatureJsonBuilder> {
    public FlowerFeatureJsonBuilder() {
        super("flower");
    }

    protected FlowerFeatureJsonBuilder(String type) {
        super(type);
    }

    /**
     *  state_provider:[需要测试] 要使用的方块。
     * 方块状态声明（见自定义世界生成#数据种类）。
     */
    public FlowerFeatureJsonBuilder state_provider(JsonObject state_provider) {
        config.add("state_provider", state_provider);
        return this;
    }

    /**
     *  block_placer:[需要测试]
     *  type:[需要测试] 要使用的方块放置器的类型。必须为下列值之一：
     *  "simple_block_placer",
     *  "double_plant_placer"，或
     *  "column_placer"。
     *      如果type是column_placer：
     *       min_size:[需要测试]
     *       extra_size:[需要测试]
     */
    public FlowerFeatureJsonBuilder block_placer(BPType type, @Nullable Integer min_size, @Nullable Integer extra_size) {
        JsonObject block_placer = new JsonObject();
        block_placer.addProperty("type", type.toString());

        if (type == BPType.column_placer) {
            block_placer.addProperty("min_size", min_size);
            block_placer.addProperty("extra_size", extra_size);
        }

        config.add("block_placer", block_placer);
        return this;
    }

    /**
     *  whitelist:[需要测试]
     *  一个方块状态。
     *  Name: 该方块的命名空间ID。
     *  Properties: 方块状态。
     *  state: 一个方块状态键及对应值。
     *  blacklist:[需要测试]
     *  一个方块状态。
     *  Name: 该方块的命名空间ID。
     *  Properties: 方块状态。
     *  state: 一个方块状态键及对应值。
     */
    JsonArray whitelist = new JsonArray();

    public FlowerFeatureJsonBuilder addWhitelist(String name, String... states) {
        return addBlockState(whitelist, name, states);
    }

    JsonArray blacklist = new JsonArray();

    public FlowerFeatureJsonBuilder addBlacklist(String name, String... states) {
        return addBlockState(blacklist, name, states);
    }

    /**
     *  tries (可选):[需要测试] 若不填写，默认值为128。
     *  xspread (可选):[需要测试] 若不填写，默认值为7。
     *  yspread (可选):[需要测试] 若不填写，默认值为3。
     *  zspread (可选):[需要测试] 若不填写，默认值为7。
     *  can_replace (可选):[需要测试] 若不填写，默认值为假。
     *  project (可选):[需要测试] 若不填写，默认值为真。
     *  need_water (可选):[需要测试] 若不填写，默认值为假。
     */
    public FlowerFeatureJsonBuilder tries(int tries) {
        config.addProperty("tries", tries);
        return this;
    }

    public FlowerFeatureJsonBuilder xspread(int xspread) {
        config.addProperty("xspread", xspread);
        return this;
    }

    public FlowerFeatureJsonBuilder yspread(int yspread) {
        config.addProperty("yspread", yspread);
        return this;
    }

    public FlowerFeatureJsonBuilder zspread(int zspread) {
        config.addProperty("zspread", zspread);
        return this;
    }

    public FlowerFeatureJsonBuilder can_replace(boolean can_replace) {
        config.addProperty("can_replace", can_replace);
        return this;
    }

    public FlowerFeatureJsonBuilder project(boolean project) {
        config.addProperty("project", project);
        return this;
    }

    public FlowerFeatureJsonBuilder need_water(boolean need_water) {
        config.addProperty("need_water", need_water);
        return this;
    }

    @Override
    public JsonObject build() {
        config.add("whitelist", whitelist);
        config.add("blacklist", blacklist);
        return super.build();
    }

    public enum BPType {
        simple_block_placer,
        double_plant_placer,
        column_placer
    }
}
