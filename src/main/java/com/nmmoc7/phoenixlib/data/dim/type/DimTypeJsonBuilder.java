package com.nmmoc7.phoenixlib.data.dim.type;

import com.google.gson.JsonObject;

/**
 * @author DustW
 */
public class DimTypeJsonBuilder {
    public JsonObject json = new JsonObject();

    /** ultrawarm：维度是否表现得类似于原版的下界（水会蒸发，海绵会变干）。这也会使得熔岩流动更快、扩散更远。*/
    public DimTypeJsonBuilder ultrawarm(boolean ultrawarm) {
        json.addProperty("ultrawarm", ultrawarm);
        return this;
    }

    /** natural：为false时，此维度中的指南针会随机转动，且无法用床睡觉或是重置重生点（即使bed_works为true）。为true时，此维中的下界传送门方块会生成僵尸猪灵。*/
    public DimTypeJsonBuilder natural(boolean natural) {
        json.addProperty("natural", natural);
        return this;
    }

    /** coordinate_scale: 传送到该维度时的坐标缩放值。*/
    public DimTypeJsonBuilder coordinate_scale(float coordinate_scale) {
        json.addProperty("coordinate_scale", coordinate_scale);
        return this;
    }

    /** has_skylight：该维度是否有天空光照。*/
    public DimTypeJsonBuilder has_skylight(boolean has_skylight) {
        json.addProperty("has_skylight", has_skylight);
        return this;
    }

    /** has_ceiling：该维度是否拥有一个基岩天花板。注意这仅仅是逻辑上是否拥有一个天花板。维度是否真的有一个天花板与此无关。*/
    public DimTypeJsonBuilder has_ceiling(boolean has_ceiling) {
        json.addProperty("has_ceiling", has_ceiling);
        return this;
    }

    /** ambient_light：该维度拥有多少环境光照（在演示文件中，默认是0.5；需要测试上下界及精确的效果[需要测试]）。 */
    public DimTypeJsonBuilder ambient_light(float ambient_light) {
        json.addProperty("ambient_light", ambient_light);
        return this;
    }

    /** fixed_time（可选）：可以是false或0至24000间的任意整数。如果将它设为一个数字，游戏内的昼夜时间将会固定在这个指定值上。
     * 但是，（至少在某些世界中[需要测试]）false被当做0来解释，因而会产生一个固定不动的日出。要保持正常的昼夜循环，需要将此属性保留为未定义。*/
    public DimTypeJsonBuilder fixed_time(float fixed_time) {
        json.addProperty("fixed_time", fixed_time);
        return this;
    }

    public DimTypeJsonBuilder fixed_time(boolean fixed_time) {
        json.addProperty("fixed_time", fixed_time);
        return this;
    }

    /** piglin_safe：猪灵和疣猪兽是否不会僵尸化。 */
    public DimTypeJsonBuilder piglin_safe(boolean piglin_safe) {
        json.addProperty("piglin_safe", piglin_safe);
        return this;
    }

    /** bed_works：玩家试图使用床时，其是否不会爆炸。*/
    public DimTypeJsonBuilder bed_works(boolean bed_works) {
        json.addProperty("bed_works", bed_works);
        return this;
    }

    /** respawn_anchor_works：玩家试图使用重生锚时，其是否不会爆炸。*/
    public DimTypeJsonBuilder respawn_anchor_works(boolean respawn_anchor_works) {
        json.addProperty("respawn_anchor_works", respawn_anchor_works);
        return this;
    }

    /** has_raids：带有不祥之兆的玩家是否可以触发袭击。*/
    public DimTypeJsonBuilder has_raids(boolean has_raids) {
        json.addProperty("has_raids", has_raids);
        return this;
    }

    /** logical_height：玩家使用紫颂果或下界传送门可以到达的最大高度。不会影响超过该限制高度的既有传送门。一般不能高于世界建筑限制高度。*/
    public DimTypeJsonBuilder logical_height(int logical_height) {
        json.addProperty("logical_height", logical_height);
        return this;
    }

    /** infiniburn：该维度中火可以在哪些方块上永久燃烧。应当填写一个方块标签的命名空间ID。*/
    public DimTypeJsonBuilder infiniburn(String infiniburn) {
        json.addProperty("infiniburn", infiniburn);
        return this;
    }

    /** effects（可选）：可以是"minecraft:overworld"、"minecraft:the_nether"或"minecraft:the_end"，用于确定该维度的天空效果。
     * 设为overworld（主世界）会使维度的天空中出现云、太阳、星星和月亮；
     * 设为the_nether（下界）会使维度中有浓厚的迷雾阻挡视野，效果与原版的下界类似；
     * 设为the_end（末地）会使维度拥有类似于末路之地的，黑暗的、斑驳的天空，并无视各生物群系自带的天空颜色与迷雾颜色。若未定义，则默认为"minecraft:overworld"。
     */
    public DimTypeJsonBuilder effects(effects effects) {
        json.addProperty("effects", effects.name);
        return this;
    }

    public enum effects {
        /** 主世界 */
        OVERWORLD("minecraft:overworld"),
        /** 下界 */
        THE_NETHER("minecraft:the_nether"),
        /** 末地 */
        THE_END("minecraft:the_end");

        String name;
        effects(String name) {
            this.name = name;
        }
    }

    public JsonObject build() {
        return json;
    }
}
