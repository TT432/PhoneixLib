package com.nmmoc7.phoenixlib.data.dim.generator.noise.noise_setting;

import com.google.gson.JsonObject;

/**
 * @author DustW
 */
public class NoiseSettingJsonBuilder {
    JsonObject json;

    /** bedrock_roof_position：世界高度上限处至基岩天花板的距离。正值会使基岩天花板出现在世界地形最高层的下方。如果超出了世界范围，则天花板不会生成。 */
    public NoiseSettingJsonBuilder bedrock_roof_position(int bedrock_roof_position) {
        json.addProperty("bedrock_roof_position", bedrock_roof_position);
        return this;
    }

    /** bedrock_floor_position：世界高度下限处至基岩地板的距离。正值会使基岩地板出现在世界地形最低层的上方。 */
    public NoiseSettingJsonBuilder bedrock_floor_position(int bedrock_floor_position) {
        json.addProperty("bedrock_floor_position", bedrock_floor_position);
        return this;
    }

    /** sea_level：此维度的海平面高度。数值应当在世界范围之内。 */
    public NoiseSettingJsonBuilder sea_level(int sea_level) {
        json.addProperty("sea_level", sea_level);
        return this;
    }

    /** disable_mob_generation：是否禁止生物随地形一起生成。 */
    public NoiseSettingJsonBuilder disable_mob_generation(boolean disable_mob_generation) {
        json.addProperty("disable_mob_generation", disable_mob_generation);
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
    public NoiseSettingJsonBuilder stronghold(int distance, int count, int spread) {
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
    public NoiseSettingJsonBuilder addStructures(String name, int spacing, int separation, int salt) {
        JsonObject structure = new JsonObject();
        structure.addProperty("spacing", spacing);
        structure.addProperty("separation", separation);
        structure.addProperty("salt", salt);
        structuresIn.add(name, structure);
        return this;
    }

    class NoiseBuilder {
        JsonObject noise = new JsonObject();

        /**
         *  height（整数）： 地形生成的总高度。通过竖直压缩整个世界来改变世界生成的最大高度（例如，当height=128时，陆地会从Y=32处从海中裸出）。
         *  此属性不影响海平面高度。地形生成的最高高度= min_y+ height，此值不能超过2032且必须是16的整数倍。
         *
         *  1.16.5应该是256吧？
         */
        public NoiseBuilder height(int height) {
            noise.addProperty("height", height);
            return this;
        }

        /**
         *  density_factor（双精度浮点数）：改变世界底部到顶部地形密度的梯度。
         *  正值会导致地形在下方变为实心，在高处形状则会缩小；负值会使地形在顶部变为实心，而在下方出现空旷的空间。该值的绝对值越大会使地形过渡越尖锐。
         */
        public NoiseBuilder density_factor(float density_factor) {
            noise.addProperty("density_factor", density_factor);
            return this;
        }

        /**  density_offset（双精度浮点数；值在-1到1之间）：影响平均陆地高度（非海平面）。[需要测试] */
        public NoiseBuilder density_offset(float density_offset) {
            noise.addProperty("density_offset", density_offset);
            return this;
        }

        /**  size_horizontal（整数）：改变陆块在X和Z轴上的比例，只接受1~4的值。生物群系不会因此改变。 */
        public NoiseBuilder size_horizontal(int size_horizontal) {
            noise.addProperty("size_horizontal", size_horizontal);
            return this;
        }

        /** size_vertical（整数）：改变陆块在Y轴上的比例，只接受1~4的值。1至15之间的值可以逐渐地增加山丘高度，
         * 大于20的值将会使得所有陆地高于通常的海平面63，比32更高的值会使得通常的陆地高度到达100+。 */
        public NoiseBuilder size_vertical(int size_vertical) {
            noise.addProperty("size_vertical", size_vertical);
            return this;
        }

        /** simplex_surface_noise（布尔值）：[需要测试]
         * 单纯形表面噪声 */
        public NoiseBuilder simplex_surface_noise(boolean simplex_surface_noise) {
            noise.addProperty("simplex_surface_noise", simplex_surface_noise);
            return this;
        }

        /** random_density_offset（布尔值；可选）：[需要测试]
         * 随机密度偏移*/
        public NoiseBuilder random_density_offset(boolean random_density_offset) {
            noise.addProperty("random_density_offset", random_density_offset);
            return this;
        }

        /**  island_noise_override（布尔值；可选）：使世界像末地一样在世界中心生成一个大型岛屿，在周围生成小型岛屿。 */
        public NoiseBuilder island_noise_override(boolean island_noise_override) {
            noise.addProperty("island_noise_override", island_noise_override);
            return this;
        }

        /**  amplified（布尔值；可选）：在放大化和正常地形生成之间切换。可与“vanilla_layered”类型以及任何维度（下界、末路之地和自定义）的巨型生物群系一起使用。 */
        public NoiseBuilder amplified(boolean amplified) {
            noise.addProperty("amplified", amplified);
            return this;
        }

        /**
         *  sampling：指定噪声算法的采样方式。
         *  xz_scale（双精度浮点数）：伸缩X和Z轴的噪声。更大的值会造成更复杂的水平形状。与旧版自定义中的“平面比例”选项（Coordinate Scale）的工作方式较为相似。
         *  xz_factor（双精度浮点数）：平滑水平方向上的噪声。
         *  y_scale（双精度浮点数）：伸缩Y轴的噪声。更大的值会造成更复杂的垂直形状。与旧版自定义中的“高度比例”选项（Height Scale）的工作方式较为相似。
         *  y_factor（双精度浮点数）：平滑垂直方向上的噪声。
         */
        public NoiseBuilder sampling(float xz_scale, float xz_factor, float y_scale, float y_factor) {
            JsonObject sampling = new JsonObject();
            sampling.addProperty("xz_scale", xz_scale);
            sampling.addProperty("xz_factor", xz_factor);
            sampling.addProperty("y_scale", y_scale);
            sampling.addProperty("y_factor", y_factor);
            noise.add("sampling", sampling);
            return this;
        }

        /**
         *  top_slide：世界地形的顶部曲线的设置。
         *  target（整数）：曲线的负对数曲率。负值会使影响区域内的山丘更加圆润，正值会使其更加平坦。绝对值越大效果越明显。
         *  size（整数）：定义了以世界地形顶部为中心的影响区域大小。size由以下公式计算size = <以方块为单位的高度> * 0.25 / size_vertical。
         *  offset（整数）：从世界地形顶部移动影响区域。offset使用和size相同的公式，故而offset = <以方块为单位的高度> * 0.25 / size_vertical。
         *      对于top_slide，正值向下移动，负值向上移动。
         */
        public NoiseBuilder top_slide(int target, int size, int offset) {
            JsonObject top_slide = new JsonObject();
            top_slide.addProperty("target", target);
            top_slide.addProperty("size", size);
            top_slide.addProperty("offset", offset);
            noise.add("top_slide", top_slide);
            return this;
        }

        /**
         *  bottom_slide：世界地形的底部曲线的设置。
         *  target（整数）： 曲线的负对数曲率。负值会移除基岩地板并使浮岛的底部更加圆润，正值会生成一个基岩地板。绝对值越大效果越明显。
         *  size（整数）：定义了以世界地形底部为中心的影响区域大小。和在top_slide中使用的公式相同。
         *  offset（整数）：从世界地形底部移动影响区域。和在top_slide中使用的公式相同。对于bottom_slide，正值向上移动，负值向下移动。
         */
        public NoiseBuilder bottom_slide(int target, int size, int offset) {
            JsonObject bottom_slide = new JsonObject();
            bottom_slide.addProperty("target", target);
            bottom_slide.addProperty("size", size);
            bottom_slide.addProperty("offset", offset);
            noise.add("bottom_slide", bottom_slide);
            return this;
        }

        public NoiseSettingJsonBuilder build() {
            json.add("noise", noise);
            return NoiseSettingJsonBuilder.this;
        }
    }

    /**
     *  default_block：该维度的近地表方块的命名空间ID；参见Java版数据值§方块。
     *  Name：方块的命名空间ID。
     */
    public NoiseSettingJsonBuilder default_block(String name) {
        JsonObject default_block = new JsonObject();
        default_block.addProperty("Name", name);
        json.add("default_block", default_block);
        return this;
    }

    /**
     *  default_fluid：用于生成海洋和湖的流体的命名空间ID；详见Java版数据值§流体。
     *  Name（字符串）：方块的命名空间ID。
     *  Properties：方块状态
     *  state（字符串）：一个方块状态的键值对。    好像1.16只有一个等级
     */
    public NoiseSettingJsonBuilder default_fluid(String name, int level) {
        JsonObject default_fluid = new JsonObject();
        default_fluid.addProperty("Name", name);
        JsonObject properties = new JsonObject();
        properties.addProperty("level", level);
        default_fluid.add("Properties", properties);
        json.add("default_fluid", default_fluid);
        return this;
    }

    public JsonObject build() {
        structures.add("structures", structuresIn);
        json.add("structures", structures);
        return json;
    }
}
