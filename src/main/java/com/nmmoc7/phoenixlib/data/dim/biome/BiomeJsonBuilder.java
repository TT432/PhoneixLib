package com.nmmoc7.phoenixlib.data.dim.biome;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.Arrays;

/**
 * @author DustW
 */
public class BiomeJsonBuilder {
    JsonObject json;

    /** precipitation: 生物群系的降雨量，可以是 "none"（不下雨）、"rain"（下雨）或"snow"（下雪）。 */
    public BiomeJsonBuilder precipitation(precipitation precipitation) {
        json.addProperty("precipitation", precipitation.name);
        return this;
    }

    public enum precipitation {
        /** 不下雨 */
        NONE("none"),
        /** 下雨 */
        RAIN("rain"),
        /** 下雪 */
        SNOW("snow");

        String name;
        precipitation(String name) {
            this.name = name;
        }
    }

    /** category:[需要测试] 可以是"none"、"taiga"、"extreme_hills"、"jungle"、"mesa"、"plains"、"savanna"、
     * "icy"、"the_end"、"beach"、"forest"、"ocean"、"desert"、"river"、"swamp"、"mushroom"或"nether"。
     *
     * 看着像是生物群系的tag一类的东西*/
    public BiomeJsonBuilder category(category category) {
        json.addProperty("category", category.toString());
        return this;
    }

    public enum category {
        none,
        taiga,
        extreme_hills,
        jungle,
        mesa,
        plains,
        savanna,
        icy,
        the_end,
        beach,
        forest,
        ocean,
        desert,
        river,
        swamp,
        mushroom,
        nether
    }

    /** depth:[需要测试]用于产生地形噪声。深度为正的生物群系被认为是陆地，深度为负的生物群系被视为海洋。 */
    public BiomeJsonBuilder depth(float depth) {
        json.addProperty("depth", depth);
        return this;
    }

    /** scale:[需要测试]用于产生地形噪声。竖直方向上拉伸地形。值越小，地形越平整。 */
    public BiomeJsonBuilder scale(float scale) {
        json.addProperty("scale", scale);
        return this;
    }

    /** temperature:[需要测试]用于控制某些游戏功能，例如草和树叶的颜色，以及雪傀儡是否受到伤害。
     *
     * 温度一样的设定吧*/
    public BiomeJsonBuilder temperature(float temperature) {
        json.addProperty("temperature", temperature);
        return this;
    }

    /** temperature_modifier:[需要测试] 可为none或frozen。
     *
     * 可选项 */
    public BiomeJsonBuilder temperature_modifier(temperature_modifier temperature_modifier) {
        json.addProperty("temperature_modifier", temperature_modifier.name);
        return this;
    }

    public enum temperature_modifier {
        NONE("none"),
        FROZEN("frozen");

        String name;
        temperature_modifier(String name) {
            this.name = name;
        }
    }

    /** downfall:[需要测试]控制草和树叶的颜色，大于0.85的值还可以使火焰更快地燃烧。
     *
     * 降水量,空气湿度吧可能*/
    public BiomeJsonBuilder downfall(float downfall) {
        json.addProperty("downfall", downfall);
        return this;
    }

    JsonObject effects = new JsonObject();
    private void a() {
        json.add("effects", effects);
    }

    /** sky_color:此生物群系中用于天空的十六进制转十进制RGB颜色值。 */
    public BiomeJsonBuilder sky_color(int sky_color) {
        effects.addProperty("sky_color", sky_color);
        return this;
    }

    /** fog_color:用于迷雾的十六进制转十进制RGB颜色值。 */
    public BiomeJsonBuilder fog_color(int fog_color) {
        effects.addProperty("fog_color", fog_color);
        return this;
    }

    /** foliage_color: 用于树叶和藤蔓的十六进制转十进制RGB颜色值。
     * 若不写，颜色将由湿度（humidity）和温度（temperature）决定。 */
    public BiomeJsonBuilder foliage_color(int foliage_color) {
        effects.addProperty("foliage_color", foliage_color);
        return this;
    }

    /** grass_color: 用于草方块、草、高草丛、蕨、大型蕨和甘蔗的十六进制转十进制RGB颜色值。
     * 若不写，颜色将由湿度（humidity）和温度（temperature）决定。 */
    public BiomeJsonBuilder grass_color(int grass_color) {
        effects.addProperty("grass_color", grass_color);
        return this;
    }

    /** water_color:用于水的十六进制转十进制RGB颜色值。 */
    public BiomeJsonBuilder water_color(int water_color) {
        effects.addProperty("water_color", water_color);
        return this;
    }

    /** water_fog_color:用于水下迷雾的十六进制转十进制RGB颜色值。 */
    public BiomeJsonBuilder water_fog_color(int water_fog_color) {
        effects.addProperty("water_fog_color", water_fog_color);
        return this;
    }

    /**
     * probability:[需要测试]控制粒子产生的频率。
     * options:控制要使用的粒子。
     *   type:粒子类型的命名空间ID。
     */
    public BiomeJsonBuilder particle(float probability, String type) {
        JsonObject particle = new JsonObject();
        particle.addProperty("probability", probability);
        JsonObject options = new JsonObject();
        options.addProperty("type", type);
        particle.add("options", options);
        effects.add("particle", particle);
        return this;
    }

    /** ambient_sound（可选）:[需要测试] 用于环境音效的声音事件。 */
    public BiomeJsonBuilder ambient_sound(String ambient_sound) {
        effects.addProperty("ambient_sound", ambient_sound);
        return this;
    }

    /**
     * mood_sound（可选）:[需要测试] 氛围音效所使用的设置。
     *   sound: 要使用的声音事件。
     *   tick_delay:[需要测试]   延迟···什么玩意？
     *   block_search_extent:[需要测试]决定了氛围音效播放位置的正方体范围的大小。此正方体范围以玩家为中心，棱长为2 * block_search_extent + 1。
     *   offset:[需要测试]       偏移···什么玩意？
     */
    public BiomeJsonBuilder mood_sound(String sound, int tick_delay, int block_search_extent, double offset) {
        JsonObject mood_sound = new JsonObject();
        mood_sound.addProperty("String sound", sound);
        mood_sound.addProperty("int tick_delay", tick_delay);
        mood_sound.addProperty("int block_search_extent", block_search_extent);
        mood_sound.addProperty("double offset", offset);
        effects.add("mood_sound", mood_sound);
        return this;
    }

    /**
     * additions_sound（可选）:[需要测试] 附加音效所使用的设置。
     *   sound:要使用的声音事件。
     *   tick_chance:[需要测试]       每tick播放的概率
     */
    public BiomeJsonBuilder additions_sound(String sound, double tick_chance) {
        JsonObject additions_sound = new JsonObject();
        additions_sound.addProperty("sound", sound);
        additions_sound.addProperty("tick_chance", tick_chance);
        effects.add("additions_sound", additions_sound);
        return this;
    }

    /**
     *  music（可选）:[需要测试]应在生物群系中播放的特定音乐。
     *    sound:要使用的声音事件。
     *    min_delay:[需要测试]
     *    max_delay:[需要测试]
     *    replace_current_music:[需要测试]是否替换已经播放的音乐。
     */
    public BiomeJsonBuilder music(String sound, int min_delay, int max_delay, boolean replace_current_music) {
        JsonObject music = new JsonObject();
        music.addProperty("sound", sound);
        music.addProperty("min_delay", min_delay);
        music.addProperty("max_delay", max_delay);
        music.addProperty("replace_current_music", replace_current_music);
        effects.add("music", music);
        return this;
    }

    /** surface_builder: 所使用的配置好的地表生成器的命名空间ID。 */
    public BiomeJsonBuilder surface_builder(String surface_builder) {
        effects.addProperty("surface_builder", surface_builder);
        return this;
    }

    JsonObject carvers = new JsonObject();
    private void b() {
        json.add("carvers", carvers);
    }

    /**
     *  carvers: 要使用的地形雕刻器。
     *    air: 列表内的地形雕刻器将填充空气。
     *      : 所使用的配置好的地形雕刻器的命名空间ID。
     *    liquid: 列表内的地形雕刻器将填充液体。
     *      : 所使用的配置好的地形雕刻器的命名空间ID。
     */
    public BiomeJsonBuilder air(String... air) {
        JsonArray airs = new JsonArray();
        Arrays.stream(air).forEach(airs::add);
        carvers.add("air", airs);
        return this;
    }

    public BiomeJsonBuilder liquid(String... liquid) {
        JsonArray liquids = new JsonArray();
        Arrays.stream(liquid).forEach(liquids::add);
        carvers.add("liquid", liquids);
        return this;
    }

    private void c() {
    }

    /**
     *  features:[需要测试] 一个由10项地物组成的列表。这10项各自对应一个不同种类的地物；但是，此处需要更多测试来确定该列表的顺序（即这个列表的顺序是否正确）是否对世界生成的运作有影响。地物所在的列表的下标也被用来生成地物种子的一部分，所以将地物移动到另一个列表中无疑将对生成产生影响。每个列表中的每个元素都是一个配置好的地物的命名空间ID。可以是一个空列表。
     *      : 内部地物类型RAW_GENERATION（原始生成）。默认不用于任何地物。
     *      : 内部地物类型LAKES（湖）。默认用于普通的湖和熔岩湖。
     *      : 内部地物类型LOCAL_MODIFICATIONS（局部修改）。默认用于针叶林生成的苔石和冰山。
     *      : 内部地物类型UNDERGROUND_STRUCTURES（地下结构）。默认用于主世界的地牢和化石。
     *      : 内部地物类型SURFACE_STRUCTURES（地表结构）。默认用于沙漠的水井和蓝冰堆。
     *      : 内部地物类型STRONGHOLDS（要塞）。默认不用于任何地物。
     *      : 内部地物类型UNDERGROUND_ORES（地下矿石）。默认用于主世界的矿脉、主世界的泥土/沙砾/石头变种的堆（Patch），以及湖中生成的沙子/沙砾/黏土的圆盘状结构（Disk）。
     *      : 内部地物类型UNDERGROUND_DECORATION（地下装饰）。默认用于被虫蚀的方块的矿脉、下界的沙砾矿脉、下界的黑石矿脉，以及其他所有的下界矿脉。
     *      : 内部地物类型VEGETAL_DECORATION（植被装饰）。默认用于树、竹子、仙人掌、海带，以及其他的地表/海底植被和熔岩/水的喷泉。
     *      : 内部地物类型TOP_LAYER_MODIFICATION（顶层修改）。默认用于地表的冰冻现象。
     */
    public BiomeJsonBuilder addFeatures(String... features) {
        JsonArray featuresJ = new JsonArray();
        JsonArray a = new JsonArray();
        Arrays.stream(features).forEach(a::add);
        featuresJ.add(a);
        json.add("features", featuresJ);
        return this;
    }

    JsonArray starts = new JsonArray();
    private void d() {
        json.add("starts", starts);
    }

    /**
     *  starts:[需要测试] 该生物群系所生成的结构。
     *      所使用的配置好的结构特征的命名空间ID。
     */
    public BiomeJsonBuilder addStarts(String start) {
        starts.add(start);
        return this;
    }

    /**
     *  spawners: 实体生成设置。
     *  <生物种类>: 合法的生物种类有monster、creature、ambient、water_creature、water_ambient和misc。
     *  一个刷怪数据的复合标签的列表，列表的每一项都对应一种应该在该生物群系生成的生物。
     *    单个生物的刷怪数据。
     *      type: 该生物的命名空间ID。
     *      weight:[需要测试] 该生物的生成权重，更高的值使该生物更多地取代其他生物的生成。
     *      minCount:生物在生成时的最低数量。
     *      maxCount:生物在生成时的最高数量。
     */

    JsonObject spawners = new JsonObject();
    private void e() {
        json.add("spawners", spawners);
    }

    public BiomeJsonBuilder addSpawner(SpawnerType type, SpawnerData... data) {
        Arrays.stream(data).forEach(d -> d.write(type.write(spawners)));
        return this;
    }

    public static class SpawnerData {
        String type;
        int weight;
        int minCount;
        int maxCount;

        public SpawnerData(String type, int weight, int minCount, int maxCount) {
            this.type = type;
            this.weight = weight;
            this.maxCount = maxCount;
            this.minCount = minCount;
        }

        public void write(JsonArray json) {
            JsonObject temp = new JsonObject();
            temp.addProperty("type", this.type);
            temp.addProperty("weight", weight);
            temp.addProperty("minCount", minCount);
            temp.addProperty("maxCount", maxCount);
            json.add(temp);
        }
    }

    public enum SpawnerType {
        monster,
        creature,
        ambient,
        water_creature,
        misc;

        public JsonArray write(JsonObject json) {
            JsonArray temp = new JsonArray();
            json.add(this.name(), temp);
            return temp;
        }
    }

    /**
     *  player_spawn_friendly:定义该生物群系是否对新玩家友好。
     *  对于平原、森林等常规环境为true，在海洋、沙漠及雪山等恶劣环境下被设置为false.
     *  该值被用于防止世界重生点被放置到一个过于困难而使新玩家无法起步的环境中。
     */
    public BiomeJsonBuilder player_spawn_friendly(boolean player_spawn_friendly) {
        json.addProperty("player_spawn_friendly", player_spawn_friendly);
        return this;
    }

    /**  parent（可选）:[需要测试] 该生物群系的父生物群系的命名空间ID。 */
    public BiomeJsonBuilder parent(String parent) {
        json.addProperty("parent", parent);
        return this;
    }

    JsonObject spawn_costs = new JsonObject();
    private void f() {
        json.add("spawn_costs", spawn_costs);
    }

    /**
     *  spawn_costs:[需要测试] 生成代价，实体ID列表。以一套类似于模拟真空中的点电荷系统的机制控制该生物群系内生成生物的数量与密度。
     *    <实体ID>:生成的实体。[需要测试]
     *      energy_budget:电势预算。生成此实体时能损失得起的最大电势差。在宏观意义上看，此值越小生成的越少。[需要测试]
     *      charge:此次生成放置的点电荷的电荷量。
     *        每次生成都在之前放置的点电荷的基础上试图将一个点电荷从无穷远处移动到目标点，计算电势差并比较以决定是否生成。
     *        在宏观意义上看，此值越大生成的越少。
     */
    public BiomeJsonBuilder addSpawnCosts(String id, double energy_budget, double charge) {
        JsonObject idJ = new JsonObject();
        idJ.addProperty("energy_budget", energy_budget);
        idJ.addProperty("charge", charge);
        spawn_costs.add(id, idJ);
        return this;
    }

    public JsonObject build() {
        a();
        b();
        c();
        d();
        e();
        f();
        return json;
    }
}
