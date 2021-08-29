package com.nmmoc7.phoenixlib.data.dim.biome.feature;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import javax.annotation.Nullable;

/**
 * @author DustW
 */
public class TreeFeatureJsonBuilder extends BaseFeature<TreeFeatureJsonBuilder> {
    public TreeFeatureJsonBuilder() {
        super("tree");
    }

    /**
     * trunk_provider:[需要测试] 作为树干的方块。注意当树干放置器为"fancy_trunk_placer"时，须使用含xyz标签的方块，如原木。
     */
    public TreeFeatureJsonBuilder trunk_provider(JsonObject blockstateProvider) {
        config.add("trunk_provider", blockstateProvider);
        return this;
    }

    /**
     * leaves_provider:[需要测试] 作为树叶的方块。
     */
    public TreeFeatureJsonBuilder leaves_provider(JsonObject blockstateProvider) {
        config.add("leaves_provider", blockstateProvider);
        return this;
    }

    public FoliagePlacerBuilder foliage_placer() {
        return new FoliagePlacerBuilder();
    }

    class FoliagePlacerBuilder {
        JsonObject json = new JsonObject();

        /**
         * {@link UniformInt}
         * radius:[需要测试]
         *      均匀分布整数，其中minBase为0，maxBase为8，maxSpread为8。
         */
        public FoliagePlacerBuilder radius(JsonObject radius) {
            json.add("radius", radius);
            return this;
        }

        /**
         * {@link UniformInt}
         * offset:[需要测试]
         *      均匀分布整数，其中minBase为0，maxBase为8，maxSpread为8。
         */
        public FoliagePlacerBuilder offset(JsonObject offset) {
            json.add("offset", offset);
            return this;
        }

        FPType type;

        public FoliagePlacerBuilder type(FPType type) {
            this.type = type;
            json.addProperty("type", type.toString());
            return this;
        }

        /**
         *  type:[需要测试]树叶放置器的类型，必须为下列值之一：
         *  "blob_foliage_placer",
         *  "spruce_foliage_placer",
         *  "pine_foliage_placer",
         *  "acacia_foliage_placer",
         *  "bush_foliage_placer",
         *  "fancy_foliage_placer",
         *  "jungle_foliage_placer",
         *  "mega_pine_foliage_placer", 或
         *  "dark_oak_foliage_placer"。
         *
         * 如果type是blob_foliage_placer, bush_foliage_placer, fancy_foliage_placer, 或jungle_foliage_placer：
         *      height:[需要测试] 影响树叶的高度与宽度，单位是方块。 取值必须为0到16（含0和16）。
         * 如果type是spruce_foliage_placer：
         *      trunk_height:[需要测试]
         *          均匀分布整数，其中minBase为0，maxBase为16，maxSpread为8。
         * 如果type是pine_foliage_placer：
         *      height:[需要测试]
         *          均匀分布整数，其中minBase为0，maxBase为16，maxSpread为8。
         * 如果type是mega_pine_foliage_placer：
         *      crown_height:[需要测试]
         *          均匀分布整数，其中minBase为0，maxBase为16，maxSpread为8。
         */
        public FoliagePlacerBuilder height(@Nullable Integer height, @Nullable JsonObject uniformInt) {
            if (
                    type == FPType.blob_foliage_placer
                    || type == FPType.bush_foliage_placer
                    || type == FPType.fancy_foliage_placer
                    || type == FPType.jungle_foliage_placer
            ) {
                json.addProperty("height", height);
            }
            else if (type == FPType.spruce_foliage_placer) {
                json.add("trunk_height", uniformInt);
            }
            else if (type == FPType.pine_foliage_placer) {
                json.add("height", uniformInt);
            }
            else if (type == FPType.mega_pine_foliage_placer) {
                json.add("crown_height", uniformInt);
            }

            return this;
        }

        public TreeFeatureJsonBuilder build() {
            TreeFeatureJsonBuilder.this.config.add("foliage_placer", json);
            return TreeFeatureJsonBuilder.this;
        }
    }

    /**
     * trunk_placer:[需要测试] 控制树干的生成方式。
     *      type:[需要测试] 树干放置器的类型，必须为下列值之一：
     *      "straight_trunk_placer",
     *      "forking_trunk_placer",
     *      "giant_trunk_placer",
     *      "mega_jungle_trunk_placer",
     *      "dark_oak_trunk_placer"，或
     *      "fancy_trunk_placer"。
     *      base_height:[需要测试] 取值必须为0到32（含0和32）。     基础
     *      height_rand_a:[需要测试] 取值必须为0到24（含0和24）。   水平
     *      height_rand_b:[需要测试] 取值必须为0到24（含0和24）。   竖直
     */
    public TreeFeatureJsonBuilder trunk_placer(TPType type, int base_height, int height_rand_a, int height_rand_b) {
        JsonObject trunk_placer = new JsonObject();
        trunk_placer.addProperty("type", type.toString());
        trunk_placer.addProperty("base_height", base_height);
        trunk_placer.addProperty("height_rand_a", height_rand_a);
        trunk_placer.addProperty("height_rand_b", height_rand_b);
        config.add("trunk_placer", trunk_placer);
        return this;
    }

    /**
     *  minimum_size:[需要测试]
     *      min_clipped_height (可选):[需要测试] 取值必须为0到80（含0和80）。                        最小剪裁高度
     *      type:[需要测试] 必须为"two_layers_feature_size"或"three_layers_feature_size"。
     *      如果type是two_layers_feature_size：
     *          limit      (可选):[需要测试] 取值必须为0到81（含0和81）。若不填写，默认值为1。           限制
     *          lower_size (可选):[需要测试] 取值必须为0到16（含0和16）。若不填写，默认值为0。          下部尺寸
     *          upper_size (可选):[需要测试] 取值必须为0到16（含0和16）。若不填写，默认值为1。          上部尺寸
     *      如果type是three_layers_feature_size：
     *          limit       (可选):[需要测试] 取值必须为0到80（含0和80）。若不填写，默认值为1。          限制
     *          upper_limit (可选):[需要测试] 取值必须为0到80（含0和80）。若不填写，默认值为1。         上部限制
     *          lower_size  (可选):[需要测试] 取值必须为0到16（含0和16）。若不填写，默认值为0。         下部尺寸
     *          middle_size (可选):[需要测试] 取值必须为0到16（含0和16）。若不填写，默认值为1。         中部尺寸
     *          upper_size  (可选):[需要测试] 取值必须为0到16（含0和16）。若不填写，默认值为1。         上部尺寸
     */
    public TreeFeatureJsonBuilder minimum_size(MSType type,
                                               @Nullable Integer min_clipped_height,

                                               @Nullable Integer limit,
                                               @Nullable Integer lower_size,
                                               @Nullable Integer upper_size,

                                               @Nullable Integer upper_limit,
                                               @Nullable Integer middle_size) {

        JsonObject minimum_size = new JsonObject();
        minimum_size.addProperty("min_clipped_height", min_clipped_height);
        minimum_size.addProperty("type", type.toString());

        if (type == MSType.two_layers_feature_size) {
            if (limit      != null) minimum_size.addProperty("limit", limit     );
            if (lower_size != null) minimum_size.addProperty("lower_size", lower_size);
            if (upper_size != null) minimum_size.addProperty("upper_size", upper_size);
        }
        else {
            if (limit       != null) minimum_size.addProperty("limit", limit      );
            if (upper_limit != null) minimum_size.addProperty("upper_limit", upper_limit);
            if (lower_size  != null) minimum_size.addProperty("lower_size", lower_size );
            if (middle_size != null) minimum_size.addProperty("middle_size", middle_size);
            if (upper_size  != null) minimum_size.addProperty("upper_size", upper_size );
        }

        return this;
    }

    public DecoratorsBuilder decorators() {
        return new DecoratorsBuilder();
    }

    /**
     *  decorators:[需要测试] 树木除枝叶外额外的装饰。
     *  一个装饰器。
     *  type: 要使用的装饰器的类型。必须为下列值之一：
     *  "trunk_vine",
     *  "leave_vine",
     *  "cocoa",
     *  "beehive"，或
     *  "alter_ground"。
     * 如果type是cocoa或beehive：
     *  probability:[需要测试] 取值必须为0到1的浮点数（含0和1）。
     * 如果type是alter_ground：
     *  provider:[需要测试] 用于替换地面的方块。
     * 方块状态声明（见自定义世界生成#数据种类）。
     */
    class DecoratorsBuilder {
        JsonArray decorators = new JsonArray();

        public DecoratorsBuilder addDecorator(DType type, @Nullable Float probability, @Nullable JsonObject provider) {
            JsonObject decorator = new JsonObject();
            decorator.addProperty("type", type.toString());

            if (type == DType.cocoa || type == DType.beehive) {
                decorator.addProperty("probability", probability);
            }
            else if (type == DType.alter_ground) {
                decorator.add("provider", provider);
            }

            decorators.add(decorator);

            return this;
        }

        public TreeFeatureJsonBuilder build() {
            TreeFeatureJsonBuilder.this.config.add("decorators", decorators);
            return TreeFeatureJsonBuilder.this;
        }
    }

    /** max_water_depth (可选):[需要测试] 要生成该树所允许的最大水深。 若不填写，默认值为0。 */
    public TreeFeatureJsonBuilder max_water_depth(int max_water_depth) {
        config.addProperty("max_water_depth", max_water_depth);
        return this;
    }

    /** ignore_vines (可选):[需要测试] 若不填写，默认值为假。    忽略藤蔓 */
    public TreeFeatureJsonBuilder ignore_vines(boolean ignore_vines) {
        config.addProperty("ignore_vines", ignore_vines);
        return this;
    }

    /**
     *  heightmap:[需要测试] 必须是
     *  "WORLD_SURFACE_WG"（地表面（世界生成））、
     *  "WORLD_SURFACE"（地表面）、
     *  "OCEAN_FLOOR_WG"（海床面（世界生成））、
     *  "OCEAN_FLOOR"（海床面）、
     *  "MOTION_BLOCKING"（阻挡实体运动面）或
     *  "MOTION_BLOCKING_NO_LEAVES"（阻挡实体运动面（不含树叶））中的一个。
     */
    public TreeFeatureJsonBuilder heightmap(Heightmap heightmap) {
        config.addProperty("heightmap", heightmap.toString());
        return this;
    }

    public enum Heightmap {
        WORLD_SURFACE_WG,
        WORLD_SURFACE,
        OCEAN_FLOOR_WG,
        OCEAN_FLOOR,
        MOTION_BLOCKING,
        MOTION_BLOCKING_NO_LEAVES
    }

    public enum DType {
        /** 树干藤蔓 */
        trunk_vine,
        /** 树叶藤蔓 */
        leave_vine,
        /** 可可豆 */
        cocoa,
        /** 蜂窝 */
        beehive,
        /** 替换地面方块 */
        alter_ground
    }

    public enum MSType {
        two_layers_feature_size,
        three_layers_feature_size
    }

    public enum TPType {
        /** 竖直型 */
        straight_trunk_placer,
        /** 单分叉型 */
        forking_trunk_placer,
        /** 2x2竖直型 */
        giant_trunk_placer,
        /** 大丛林型 */
        mega_jungle_trunk_placer,
        /** 深橡木型 */
        dark_oak_trunk_placer,
        /** 多分叉型 */
        fancy_trunk_placer
    }

    public enum FPType {
        /** 桦树 / 橡木 */
        blob_foliage_placer,
        /** 云杉 */
        spruce_foliage_placer,
        /** 稀疏云杉 */
        pine_foliage_placer,
        /** 金合欢 */
        acacia_foliage_placer,
        /** 金字塔 */
        bush_foliage_placer,
        /** 球形 */
        fancy_foliage_placer,
        /** 丛林 */
        jungle_foliage_placer,
        /** 双层稀疏云杉 */
        mega_pine_foliage_placer,
        /** 黑橡木 */
        dark_oak_foliage_placer
    }
}
