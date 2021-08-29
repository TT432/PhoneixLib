package com.nmmoc7.phoenixlib.data.dim.biome.feature;

/**
 * @author DustW
 */
public class EmeraldOreFeatureJsonBuilder extends BaseFeature<EmeraldOreFeatureJsonBuilder> {
    public EmeraldOreFeatureJsonBuilder() {
        super("emerald_ore");
    }

    /** 被替换的方块的命名空间ID */
    public EmeraldOreFeatureJsonBuilder addTarget(String name, String... states) {
        return addBlockState(config, "target", name, states);
    }

    /** 代替绿宝石生成的方块的命名空间ID。 */
    public EmeraldOreFeatureJsonBuilder addState(String name, String... states) {
        return addBlockState(config, "state", name, states);
    }
}
