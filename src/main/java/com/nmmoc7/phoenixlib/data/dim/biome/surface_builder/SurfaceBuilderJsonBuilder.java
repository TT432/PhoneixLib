package com.nmmoc7.phoenixlib.data.dim.biome.surface_builder;

import com.google.gson.JsonObject;

import java.util.Arrays;

/**
 * @author DustW
 */
public class SurfaceBuilderJsonBuilder {
    JsonObject json = new JsonObject();

    public SurfaceBuilderJsonBuilder(Type type) {
        json.addProperty("type", type.toString());
    }

    JsonObject config = new JsonObject();

    /**
     *  config: 地表生成器的配置。
     *      top_material:[需要测试] 地形最高层所使用的方块。
     *          Name: 所使用的方块的命名空间ID。
     *          Properties: 方块状态
     *              state: 一个方块状态的键值对。
     *      under_material:[需要测试] 与地形最高层直接接触的下一层所使用的方块。
     *          Name: 所使用的方块的命名空间ID。
     *          Properties: 方块状态
     *              state: 一个方块状态的键值对。
     *      underwater_material:[需要测试] 水体底部所使用的方块。
     *          Name: 所使用的方块的命名空间ID。
     *          Properties: 方块状态
     *              state: 一个方块状态的键值对。
     */
    public SurfaceBuilderJsonBuilder top_material(String name, String... state) {
        return addConfig("top_material", name, state);
    }

    public SurfaceBuilderJsonBuilder under_material(String name, String... state) {
        return addConfig("under_material", name, state);
    }

    public SurfaceBuilderJsonBuilder underwater_material(String name, String... state) {
        return addConfig("underwater_material", name, state);
    }

    private SurfaceBuilderJsonBuilder addConfig(String type, String name, String... state) {
        JsonObject typeJ = new JsonObject();
        JsonObject properties = new JsonObject();
        Arrays.stream(state).forEach(s -> properties.addProperty(s.split(":")[0], s.split(":")[1]));
        typeJ.addProperty("Name", name);
        typeJ.add("Properties", properties);
        config.add(type, typeJ);
        return this;
    }

    public JsonObject build() {
        json.add("config", config);
        return json;
    }

    public enum Type {
        /** 默认 */
        Default("default"),
        /** 山地 */
        mountain,
        /** 破碎的热带草原 */
        shattered_savanna,
        /**  */
        gravelly_mountain,
        /**  */
        giant_tree_taiga,
        /** 沼泽 */
        swamp,
        /** 恶地 */
        badlands,
        /** 繁茂的恶地 */
        wooded_badlands,
        /** 被风蚀的恶地 */
        eroded_badlands,
        /** 冻洋 */
        frozen_ocean,
        /** 下界 */
        nether,
        /** 绯红、诡异森林 */
        nether_forest,
        /** 灵魂沙峡谷 */
        soul_sand_valley,
        /** 玄武岩三角洲 */
        basalt_deltas,
        /** 无 */
        nope;

        String cname;
        Type(String name) {
            this.cname = name;
        }

        Type() {

        }

        @Override
        public String toString() {
            if (cname != null) {
                return cname;
            }
            return super.toString();
        }
    }
}
