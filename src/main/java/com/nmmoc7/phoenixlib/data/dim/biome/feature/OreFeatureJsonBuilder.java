package com.nmmoc7.phoenixlib.data.dim.biome.feature;

import com.google.gson.JsonObject;

import javax.annotation.Nullable;

/**
 * @author DustW
 */
public class OreFeatureJsonBuilder extends BaseFeature<OreFeatureJsonBuilder> {
    public OreFeatureJsonBuilder() {
        super("ore");
    }

    protected OreFeatureJsonBuilder(String type) {
        super(type);
    }

    /** 测试该方块是否能被替换 */
    public TargetBuilder target() {
        return new TargetBuilder();
    }

    class TargetBuilder {
        JsonObject target = new JsonObject();
        PredicateType predicate_type;

        public TargetBuilder predicate_type(PredicateType predicate_type) {
            this.predicate_type = predicate_type;
            target.addProperty("predicate_type", predicate_type.toString());
            return this;
        }

        public TargetBuilder blockOrTag(String blockOrTag, @Nullable Float probability) {
            if (predicate_type == PredicateType.block_match || predicate_type == PredicateType.random_block_match) {
                target.addProperty("block", blockOrTag);

                if (predicate_type == PredicateType.random_block_match) {
                    target.addProperty("probability", probability);
                }
            }
            else if (predicate_type == PredicateType.tag_match) {
                target.addProperty("tag", blockOrTag);
            }

            return this;
        }

        public TargetBuilder blockstate(String name, @Nullable Float probability, String... states) {
            if (predicate_type == PredicateType.block_match) {
                target.add("block_state", stateJson(name, states));
            }
            else if(predicate_type == PredicateType.random_blockstate_match) {
                target.add("block_state", stateJson(name, states));
                target.addProperty("probability", probability);
            }

            return this;
        }

        public OreFeatureJsonBuilder build() {
            config.add("target", target);
            return OreFeatureJsonBuilder.this;
        }
    }

    /** 作为矿石生成的方块状态 */
    public OreFeatureJsonBuilder state(String name, String states) {
        return addBlockState(config, "state", name, states);
    }

    /**  size:[需要测试] 矿脉的大小。取值必须为0到64（含0和64）。 */
    public OreFeatureJsonBuilder size(int size) {
        config.addProperty("size", size);
        return this;
    }

    public enum PredicateType {
        /** 总是true */
        always_true,
        /** block匹配 */
        block_match,
        /** blockstate匹配 */
        blockstate_match,
        /** tag匹配 */
        tag_match,
        /** 随机block匹配 */
        random_block_match,
        /** 随机blockstate匹配 */
        random_blockstate_match
    }
}
