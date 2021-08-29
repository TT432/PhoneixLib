package com.nmmoc7.phoenixlib.data.dim.generator.noise.biome_source;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

/**
 * @author DustW
 */
public class BiomeSourceJsonBuilder {
    Type type;
    JsonObject json = new JsonObject();

    /**
     *  FPType（字符串）：生物群系生成类型。参数位于“biome_source”复合标签中。
     *  type的一些可选值包括minecraft:vanilla_layered、
     *  minecraft:fixed、minecraft:checkerboard、minecraft:multi_noise和minecraft:the_end。[需要更多信息]
     */
    public BiomeSourceJsonBuilder(Type type) {
        this.type = type;
        json.addProperty("type", type.name);
    }

    enum Type {
        /** 原版分层 */
        VANILLA_LAYERED("minecraft:vanilla_layered"),
        /** 固定 */
        FIXED("minecraft:fixed"),
        /** 棋盘 */
        CHECKERBOARD("minecraft:checkerboard"),
        /** 多重噪声 */
        MULTI_NOISE("minecraft:multi_noise"),
        /** 末地 */
        THE_END("minecraft:the_end");

        String name;
        Type(String name) {
            this.name = name;
        }
    }

    // vanilla_layered start
    // vanilla_layered：原版中主世界默认的生物群系生成方式以及巨型生物群系的生成方式。

    private void assertVanillaLayered() {
        if (type != Type.VANILLA_LAYERED) {
            throw new IllegalStateException("该选项仅为 vanilla_layered 时可用");
        }
    }

    /** large_biomes（布尔值）：决定生物群系的规模。值为true时生物群系将拥有“巨型生物群系”世界类型中的规模。 */
    public BiomeSourceJsonBuilder large_biomes(boolean large_biomes) {
        assertVanillaLayered();
        json.addProperty("large_biomes", large_biomes);
        return this;
    }

    /** legacy_biome_init_layer（布尔值）：决定世界是否为default_1_1。[需要测试] */
    public BiomeSourceJsonBuilder legacy_biome_init_layer(boolean legacy_biome_init_layer) {
        assertVanillaLayered();
        json.addProperty("legacy_biome_init_layer", legacy_biome_init_layer);
        return this;
    }

    // vanilla_layered end

    // multi_noise start
    // 原版中下界的生物群系生成方式。[需要测试]

    private void assertMultiNoise() {
        if (type != Type.MULTI_NOISE) {
            throw new IllegalStateException("该选项仅为 multi_noise 时可用");
        }
    }

    public NoiseBiomesBuilder biomes_n() {
        assertMultiNoise();

        return new NoiseBiomesBuilder();
    }

    class NoiseBiomesBuilder {
        JsonArray biomes = new JsonArray();

        /**
         * ：生物群系及其属性
         *  biome（字符串）：生物群系。
         *  parameters：代表生物群系应该被放置的最佳条件。这些值不会影响生物群系内的地形生成，而是影响游戏在何处安放这些生物群系。海拔（altitude）、怪奇度（weirdness）、湿度（humidity）和温度（temperature）都会在由种子生成的单独的轮廓图（contoured map）中表现。在任一特定点，拥有与每个噪声图上对应点最相似的参数的生物群系会被选择例如，如果某一点的温度为-1，温度为0.2，海拔为0.2，怪奇度为0，那么则会选择桦木森林丘陵（温度-0.1、湿度0.2、海拔0.25、怪奇度0）而不是沙漠（温度0.5、湿度-0.5、海拔0、怪奇度0）。
         *  altitude（单精度浮点数，范围：-2.0到2.0）：用于使类似生物群系彼此接近。[需要测试]
         *  weirdness（单精度浮点数，范围：-2.0到2.0）：定义生物群系与其他生物群系邻近时的“怪异”（weird）程度。
         *  offset（单精度浮点数，范围：0.0到1.0）：类似于其他参数，但是offset在任何地方都是0，因此将这个参数设为靠近0的值会使这个生物群系占更多优势，而其他的生物群系平等。
         *  temperature（单精度浮点数）：用于使类似地生物群系彼此接近。这与生物群系中列举的温度值不相同，不会影响雨雪或者树叶、草的颜色。
         *  humidity（单精度浮点数）：用于使类似生物群系彼此接近。[需要测试]
         */
        public NoiseBiomesBuilder addBiome(String biome, float altitude, float weirdness, float offset, float temperature, float humidity) {
            JsonObject biomeJ = new JsonObject();
            JsonObject parameters = new JsonObject();
            parameters.addProperty("altitude", altitude);
            parameters.addProperty("weirdness", weirdness);
            parameters.addProperty("offset", offset);
            parameters.addProperty("temperature", temperature);
            parameters.addProperty("humidity", humidity);
            biomeJ.addProperty("biome", biome);
            biomeJ.add("parameters", parameters);
            biomes.add(biomeJ);
            return this;
        }

        public BiomeSourceJsonBuilder build() {
            json.add("biomes", biomes);
            return BiomeSourceJsonBuilder.this;
        }
    }

    private BiomeSourceJsonBuilder addNoise(String name, float main, float... others) {
        assertMultiNoise();

        JsonObject noise = new JsonObject();
        noise.addProperty("firstOctave", main);
        JsonArray amplitudes = new JsonArray();

        for (float other: others) {
            amplitudes.add(other);
        }

        noise.add("amplitudes", amplitudes);
        json.add(name, noise);
        return this;
    }

    /**
     * 高度
     * altitude_noise：[需要测试]
     *  firstOctave：主倍频的频率指数。[需要测试]
     *  amplitudes：[需要测试]
     *  其中一个振幅。
     */
    public BiomeSourceJsonBuilder altitude_noise(float main, float... others) {
        return addNoise("altitude_noise", main, others);
    }

    /**
     *  奇异度
     *  weirdness_noise：[需要测试]
     *  firstOctave：主倍频的频率指数。[需要测试]
     *  amplitudes：[需要测试]
     *  其中一个振幅。
     */
    public BiomeSourceJsonBuilder weirdness_noise(float main, float... others) {
        return addNoise("weirdness_noise", main, others);
    }

    /**
     * 温度
     *  temperature_noise：[需要测试]
     *  firstOctave：主倍频的频率指数。[需要测试]
     *  amplitudes：[需要测试]
     *  其中一个振幅。
     */
    public BiomeSourceJsonBuilder temperature_noise(float main, float... others) {
        return addNoise("temperature_noise", main, others);
    }

    /**
     * 湿度
     *  humidity_noise：[需要测试]
     *  firstOctave：主倍频的频率指数。[需要测试]
     *  amplitudes：[需要测试]
     *  其中一个振幅。
     */
    public BiomeSourceJsonBuilder humidity_noise(float main, float... others) {
        return addNoise("humidity_noise", main, others);
    }

    // multi_noise end

    // the_end start
    // the_end：原版中末路之地的生物群系生成方式。以生物群系minecraft:the_end（末地）为中心，周围环绕着其他生物群系。[需要测试]
    // the_end end

    // fixed start
    //fixed：单生物群系维度。

    private void assertFixed() {
        if (type != Type.FIXED) {
            throw new IllegalStateException("该选项仅为 fixed 时可用");
        }
    }

    /** biome：该维度所使用的生物群系。 */
    public BiomeSourceJsonBuilder addFixedBiome(String biome) {
        assertFixed();
        json.addProperty("biome", biome);
        return this;
    }

    // fixed end

    // checkerboard start

    private void assertCheckerboard() {
        if (type != Type.MULTI_NOISE) {
            throw new IllegalStateException("该选项仅为 multi_noise 时可用");
        }
    }

    public CheckerboardBiomeBuilder biomes_c() {
        assertCheckerboard();
        return new CheckerboardBiomeBuilder();
    }

    class CheckerboardBiomeBuilder {
        JsonArray biomes = new JsonArray();

        /**
         * biomes：沿着对角线重复的生物群系列表（可以超过3个）。
         */
        public CheckerboardBiomeBuilder addBiome(String biome) {
            biomes.add(biome);
            return this;
        }

        public BiomeSourceJsonBuilder build() {
            json.add("biomes", biomes);
            return BiomeSourceJsonBuilder.this;
        }
    }

    /**
     * scale：确定指数规模上正方形单元的大小。
     */
    public BiomeSourceJsonBuilder scale(int scale) {
        assertCheckerboard();
        json.addProperty("scale", scale);
        return this;
    }

    // checkerboard end

    public JsonObject build() {
        return json;
    }
}
