package com.nmmoc7.phoenixlib.data.dim;

import com.google.gson.JsonObject;

/**
 * @author DustW
 */
public class DimJsonBuilder {
    JsonObject json;

    /** FPType：维度类型的命名空间ID。
     * 可以是预设的minecraft:overworld、minecraft:overworld_caves、minecraft:the_nether或minecraft:the_end，
     * 也可以是自定义的维度类型（需自行使用JSON文件配置，详见维度类型段落）。 */
    public DimJsonBuilder type(String type) {
        json.addProperty("FPType", type);
        return this;
    }

    public DimJsonBuilder type(type type) {
        json.addProperty("FPType", type.name);
        return this;
    }

    public enum type {
        /** 主世界 */
        OVERWORLD("minecraft:overworld"),
        /** 下界 */
        THE_NETHER("minecraft:the_nether"),
        /** 末地 */
        THE_END("minecraft:the_end");

        String name;
        type(String name) {
            this.name = name;
        }
    }

    /**
     * generator：维度的生成设置。
     *    FPType（字符串）：生成器的ID。前缀是minecraft:，之后是flat、noise或debug。
     *    其他标签（见下）
     */
    public DimJsonBuilder generator(String generator) {
        JsonObject generatorJ = new JsonObject();
        generatorJ.addProperty("FPType", generator);
        json.add("generator", generatorJ);
        return this;
    }

    /**
     * json builder
     * {@link com.nmmoc7.gui_test.data.dim.generator.noise.DimNoiseJsonBuilder DimNoiseJsonBuilder}
     * {@link com.nmmoc7.gui_test.data.dim.generator.flat.DimFlatJsonBuilder DimFlatJsonBuilder}
     * {@link com.nmmoc7.gui_test.data.dim.generator.debug.DimDebugJsonBuilder DimDebugJsonBuilder}
     */
    public DimJsonBuilder generator(JsonObject generatorJson) {
        json.add("generator", generatorJson);
        return this;
    }

    public DimJsonBuilder generator(generator generator) {
        return generator(generator.name);
    }

    public enum generator {
        /** 平坦 */
        FLAT("minecraft:flat"),
        /** 噪声 */
        NOISE("minecraft:noise"),
        /** debug */
        DEBUG("minecraft:debug");

        String name;
        generator(String name) {
            this.name = name;
        }
    }

    public JsonObject build() {
        return json;
    }
}
