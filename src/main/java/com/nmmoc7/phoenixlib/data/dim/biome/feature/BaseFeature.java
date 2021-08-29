package com.nmmoc7.phoenixlib.data.dim.biome.feature;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.Arrays;

/**
 * @author DustW
 */
public class BaseFeature<T extends BaseFeature<T>> {
    private final JsonObject json = new JsonObject();
    protected JsonObject config = new JsonObject();

    public BaseFeature(String type) {
        json.addProperty("type", type);
    }

    public BaseFeature(NoConfigType type) {
        json.addProperty("type", type.toString());
    }

    public enum NoConfigType {
        no_op,
        chorus_plant,
        void_start_platform,
        desert_well,
        fossil,
        ice_spike,
        glowstone_blob,
        freeze_top_layer,
        vines,
        monster_room,
        blue_ice,
        end_island,
        kelp,
        coral_tree,
        coral_mushroom,
        coral_claw,
        weeping_vines,
        twisting_vines,
        bonus_chest,
        basalt_pillar,
        decorated_flower
    }

    public JsonObject build() {
        json.add("config", config);
        return json;
    }

    protected static JsonObject stateJson(String name, String... states) {
        JsonObject temp = new JsonObject();
        temp.addProperty("Name", name);
        JsonObject properties = new JsonObject();
        Arrays.asList(states).forEach(state -> properties.addProperty(state.split(":")[0], state.split(":")[1]));
        temp.add("Properties", properties);
        return temp;
    }

    protected T addBlockState(JsonArray jsonArray, String name, String... states) {
        JsonObject temp = stateJson(name, states);
        jsonArray.add(temp);
        return (T) this;
    }

    protected T addBlockState(JsonObject jsonObject, String nameKey, String name, String... states) {
        JsonObject temp = stateJson(name, states);
        jsonObject.add(nameKey, temp);
        return (T) this;
    }

    /**
     *  Blockstate Provider（方块状态声明）:[需要测试] 此数据结构用于为生成方块状态定义一个更高级的设置。
     *  type: 所使用的声明的类型。必须是
     *  "simple_state_provider"、
     *  "weighted_state_provider"、
     *  "plain_flower_provider"、
     *  "forest_flower_provider"或
     *  "rotated_block_provider"中的一个。
     *      如果type是simple_state_provider或rotated_block_provider：
     *       state: 一个方块状态
     *          Name: 所使用的方块的命名空间ID。
     *          Properties: 方块状态
     *             state: 一个方块状态的键值对。
     *      如果type是weighted_state_provider：
     *       entries: 可供选择的方块状态的列表。
     *          一个方块状态和对应的权重。
     *          weight: 该方块状态被选取的权重；更高的值增加被选取的频率。
     *          data: 一个方块状态
     *          Name: 所使用的方块的命名空间ID。
     *          Properties: 方块状态
     *             state: 一个方块状态的键值对。
     */
    public static class BlockStateProviderBuilder {
        JsonObject json = new JsonObject();
        Type type;

        public BlockStateProviderBuilder(Type type) {
            this.type = type;
            json.addProperty("type", type.toString());
        }

        private void assertSR() {
            if (type == Type.simple_state_provider || type == Type.rotated_block_provider) {
                return;
            }

            throw new IllegalStateException("blockstate provider类型不对");
        }

        /**
         * "Name": "minecraft:stone",
         * 				"Properties": {}
         */
        public BlockStateProviderBuilder addState(String name, String... properties) {
            assertSR();

            json.add("state", stateJson(name, properties));
            return this;
        }

        private void assertW() {
            if (type == Type.weighted_state_provider) {
                return;
            }

            throw new IllegalStateException("blockstate provider类型不对");
        }

        JsonArray entries = new JsonArray();

        public BlockStateProviderBuilder addState(int weight, String name, String... properties) {
            assertW();

            JsonObject entry = new JsonObject();
            entry.addProperty("weight", weight);
            entry.add("data", stateJson(name, properties));
            entries.add(entry);
            return this;
        }

        public JsonObject build() {
            if (type == Type.weighted_state_provider) {
                json.add("entries", entries);
            }

            return json;
        }

        public enum Type {
            simple_state_provider,
            weighted_state_provider,
            plain_flower_provider,
            forest_flower_provider,
            rotated_block_provider;
        }
    }

    /**
     *  Uniform Int（均匀分布整数）:
     *  [需要测试] 此数据结构代表一个在base到base + spread之间（含）的随机生成的整数，
     *  在上述区间内每个整数被抽取到的概率相同。spread必须大于等于0。
     *  额外的，不同的配置选项会在此处加上更多限制条件；这些限制条件会用minBase、maxBase和maxSpread表达。
     *  这些限制条件的边界都是取得到的。
     *  若没有声明这些限制条件，那么任意的base和非负的spread都是可接受的。
     *  或者，整个复合标签都可以被一个整型数据代替，这相当于将base设为该整数
     *  ，将spread设为0，效果上等同于声明一个常量。此时，若minBase和maxBase存在，它们依然生效。
     *
     *      base: 可生成的最小数字。
     *      spread: 生成随机数字的闭区间的大小。
     */
    public static class UniformInt {
        JsonObject json = new JsonObject();

        public UniformInt(int base, int spread) {
            json.addProperty("base", base);
            json.addProperty("spread", spread);
        }

        public JsonObject build() {
            return json;
        }
    }
}
