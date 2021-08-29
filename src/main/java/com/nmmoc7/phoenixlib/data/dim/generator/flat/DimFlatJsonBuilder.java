package com.nmmoc7.phoenixlib.data.dim.generator.flat;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.block.Block;

/**
 * @author DustW
 */
public class DimFlatJsonBuilder {
    JsonObject json;

    public DimFlatJsonBuilder() {
        json.addProperty("type", "minecraft:flat");
    }

    JsonObject settings = new JsonObject();
    JsonArray layers = new JsonArray();

    /**
     *  layers：层设置。
     * ：一个超平坦的层。此列表自上而下解释。
     *  height：该层方块所占的层数。
     *  block：该层使用的方块。
     */
    public DimFlatJsonBuilder addLayer(Block block, int height) {
        return addLayer(block.getRegistryName().toString(), height);
    }

    public DimFlatJsonBuilder addLayer(String name, int height) {
        JsonObject layer = new JsonObject();
        layer.addProperty("block", name);
        layer.addProperty("height", height);
        layers.add(layer);
        return this;
    }

    /**  biome：该维度使用的唯一的生物群系。*/
    public DimFlatJsonBuilder biome(String biome) {
        settings.addProperty("biome", biome);
        return this;
    }

    /** lakes（可选）：是否生成湖。如果设为true，则水湖和熔岩湖常会生成，即使在生物群系中湖通常不生成。熔岩湖生成时会被主世界不同种类的石头和矿物包围。*/
    public DimFlatJsonBuilder lakes(boolean lakes) {
        settings.addProperty("lakes", lakes);
        return this;
    }

    /** features （可选）：是否生成生物群系特有的装饰性结构，比如主世界的树木、花、草、仙人掌，下界的火/灵魂火、菌类、菌索等等。 */
    public DimFlatJsonBuilder features(boolean features) {
        settings.addProperty("features", features);
        return this;
    }

    JsonObject structures = new JsonObject();

    /**
     *  structures：生成结构的设置。
     *  stronghold：设置要塞如何生成。在structures标签下的值似乎是占位符；这些值才是真正控制要塞生成的值。
     *  distance：控制要塞距离多远。[需要测试]
     *  count：要生成多少要塞。[需要测试]
     *  spread：扩散度[需要测试]
     *
     *  可选
     */
    public DimFlatJsonBuilder stronghold(int distance, int count, int spread) {
        JsonObject stronghold = new JsonObject();
        stronghold.addProperty("distance", distance);
        stronghold.addProperty("count", count);
        stronghold.addProperty("spread", spread);
        structures.add("stronghold", stronghold);
        return this;
    }

    JsonObject structuresIn = new JsonObject();

    /**
     *  <命名空间ID>：该字段的键应该为一个结构的命名空间ID，Java版数据值§结构处有一个它们的列表。若不列出任何结构，那么所有结构皆不会生成。
     *  spacing：区块中两次这种结构的放置尝试的平均距离。[注 1]
     *  separation：区块中两次这种结构的放置尝试的最小距离，必须低于spacing。
     *  salt：[需要测试]帮助进行随机化的数字；参见盐（密码学）。
     *
     *  可选
     */
    public DimFlatJsonBuilder addStructures(String name, int spacing, int separation, int salt) {
        JsonObject structure = new JsonObject();
        structure.addProperty("spacing", spacing);
        structure.addProperty("separation", separation);
        structure.addProperty("salt", salt);
        structuresIn.add("name", structure);
        return this;
    }

    public JsonObject build() {
        structures.add("structures", structuresIn);
        settings.add("structures", structures);
        settings.add("layers", layers);
        json.add("settings", settings);
        return json;
    }
}
