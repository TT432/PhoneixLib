package com.nmmoc7.phoenixlib.data.dim.biome.feature;

import com.google.gson.JsonObject;

/**
 * @author DustW
 */
public class DecoratedFeatureJsonBuilder extends BaseFeature<DecoratedFeatureJsonBuilder> {
    public DecoratedFeatureJsonBuilder() {
        super("decorated");
    }

    public DecoratedFeatureJsonBuilder feature(String feature) {
        config.addProperty("feature", feature);
        return this;
    }

    public DecoratedFeatureJsonBuilder feature(JsonObject feature) {
        config.add("feature", feature);
        return this;
    }

    protected static class BaseDecoratorBuilder {
        JsonObject json = new JsonObject();
        JsonObject config = new JsonObject();

        public BaseDecoratorBuilder(String type) {
            json.addProperty("type", type);
        }

        public JsonObject build() {
            json.add("config", config);
            return json;
        }
    }

    public enum DecoratorType {
        nope,

        chance,
        lava_lake,
        water_lake,

        count,
        fire,
        glowstone,
        count_multilayer,

        count_noise,

        count_noise_biased,

        count_extra,

        range,
        range_biased,
        range_very_biased,

        depth_average,

        carving_mask,

        decorated,

        square,
        heightmap,
        heightmap_spread_double,
        top_solid_heightmap,
        heightmap_world_surface,
        spread_32_above,
        magma,
        emerald_ore,
        end_gateway,
        dark_oak_tree,
        iceberg,
        end_island;

        DecoratorType() {

        }
    }
}
