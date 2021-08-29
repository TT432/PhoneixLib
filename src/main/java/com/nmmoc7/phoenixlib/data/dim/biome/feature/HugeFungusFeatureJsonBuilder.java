package com.nmmoc7.phoenixlib.data.dim.biome.feature;

/**
 * @author DustW
 */
public class HugeFungusFeatureJsonBuilder extends BaseFeature<HugeFungusFeatureJsonBuilder> {
    public HugeFungusFeatureJsonBuilder() {
        super("huge_fungus");
    }

    /** valid_base_block:[需要测试] 被覆盖的方块。 */
    public HugeFungusFeatureJsonBuilder valid_base_block(String name, String... states) {
        return addBlockState(config, "valid_base_block", name, states);
    }

    /** stem_state:[需要测试] 组成菌柄的方块 */
    public HugeFungusFeatureJsonBuilder stem_state(String name, String... states) {
        return addBlockState(config, "stem_state", name, states);
    }

    /** hat_state:[需要测试] 组成菌盖的方块。 */
    public HugeFungusFeatureJsonBuilder hat_state(String name, String... states) {
        return addBlockState(config, "hat_state", name, states);
    }

    /** decor_state:[需要测试] 用于点缀的方块。 */
    public HugeFungusFeatureJsonBuilder decor_state(String name, String... states) {
        return addBlockState(config, "decor_state", name, states);
    }

    /** planted (可选):[需要测试] 若不填写，默认值为假。 */
    public HugeFungusFeatureJsonBuilder planted(boolean planted) {
        config.addProperty("planted", planted);
        return this;
    }
}
