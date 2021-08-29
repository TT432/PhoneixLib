package com.nmmoc7.phoenixlib.data.dim.biome.feature;

import com.google.gson.JsonArray;

/**
 * @author DustW
 */
public class SimpleBlockFeatureJsonBuilder extends BaseFeature<SimpleBlockFeatureJsonBuilder> {
    public SimpleBlockFeatureJsonBuilder() {
        super("simple_block");
    }

    /** to_place:[需要测试] 要放置的方块。 */
    public SimpleBlockFeatureJsonBuilder to_place(String name, String... state) {
        return addBlockState(config, "to_place", name, state);
    }

    /** place_on:[需要测试] 被覆盖的方块。 */
    public PlaceOnBuilder place_on() {
        return new PlaceOnBuilder();
    }

    class PlaceOnBuilder {
        JsonArray json = new JsonArray();

        public PlaceOnBuilder add(String name, String... states) {
            json.add(stateJson(name, states));
            return this;
        }

        public SimpleBlockFeatureJsonBuilder build() {
            return build("place_on");
        }

        protected SimpleBlockFeatureJsonBuilder build(String name) {
            config.add(name, json);
            return SimpleBlockFeatureJsonBuilder.this;
        }
    }

    public PlaceInBuilder place_in() {
        return new PlaceInBuilder();
    }

    class PlaceInBuilder extends PlaceOnBuilder {
        @Override
        public SimpleBlockFeatureJsonBuilder build() {
            return build("place_in");
        }
    }

    public PlaceUnderBuilder place_under() {
        return new PlaceUnderBuilder();
    }

    class PlaceUnderBuilder extends PlaceOnBuilder {
        @Override
        public SimpleBlockFeatureJsonBuilder build() {
            return build("place_under");
        }
    }
}
