package com.nmmoc7.phoenixlib.data.dim.biome.feature;

import com.google.gson.JsonArray;

/**
 * @author DustW
 */
public class SpringFeatureJsonBuilder extends BaseFeature<SpringFeatureJsonBuilder> {
    public SpringFeatureJsonBuilder() {
        super("spring_feature");
    }

    /**
     *  state:[需要测试] 生成泉水所使用的液体。
     *      Name: 该液体的命名空间ID。
     *      Properties: 液体的方块状态。
     *          state: 液体的方块状态键及对应值。
     */
    public SpringFeatureJsonBuilder state(String name, String... states) {
        return addBlockState(config, "state", name, states);
    }

    /** requires_block_below (可选):[需要测试] 若不填写，默认值为真。 */
    public SpringFeatureJsonBuilder requires_block_below(boolean requires_block_below) {
        config.addProperty("requires_block_below", requires_block_below);
        return this;
    }

    /** rock_count (可选):[需要测试] 若不填写，默认值为4。 */
    public SpringFeatureJsonBuilder rock_count(int rock_count) {
        config.addProperty("rock_count", rock_count);
        return this;
    }

    /** hole_count (可选):[需要测试] 若不填写，默认值为1。 */
    public SpringFeatureJsonBuilder hole_count(int hole_count) {
        config.addProperty("hole_count", hole_count);
        return this;
    }

    /**
     *  valid_blocks:[需要测试] 有效方块的列表。
     *      : 该方块的命名空间ID。
     */
    public ValidBlocksBuilder valid_blocks() {
        return new ValidBlocksBuilder();
    }

    class ValidBlocksBuilder {
        JsonArray valid_blocks = new JsonArray();

        public ValidBlocksBuilder add(String name) {
            valid_blocks.add(name);
            return this;
        }

        public SpringFeatureJsonBuilder build() {
            config.add("valid_blocks", valid_blocks);
            return SpringFeatureJsonBuilder.this;
        }
    }
}
