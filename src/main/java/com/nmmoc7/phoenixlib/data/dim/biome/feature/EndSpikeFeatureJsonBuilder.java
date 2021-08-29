package com.nmmoc7.phoenixlib.data.dim.biome.feature;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import javax.annotation.Nullable;

/**
 * @author DustW
 */
public class EndSpikeFeatureJsonBuilder extends BaseFeature<EndSpikeFeatureJsonBuilder> {
    public EndSpikeFeatureJsonBuilder() {
        super("end_spike");
    }

    /** crystal_invulnerable (可选):[需要测试] 若不填写，默认值为假。
     *
     * 水晶无敌 */
    public EndSpikeFeatureJsonBuilder crystal_invulnerable(boolean crystal_invulnerable) {
        config.addProperty("crystal_invulnerable", crystal_invulnerable);
        return this;
    }

    JsonArray spikes = new JsonArray();

    /**
     *  spikes: 每一处冰刺单独的设置。
     *      一处冰刺。
     *      centerX (可选):[需要测试] 若不填写，默认值为0。
     *      centerZ (可选):[需要测试] 若不填写，默认值为0。
     *      radius (可选):[需要测试] 冰刺的半径。若不填写，默认值为0。
     *      height (可选):[需要测试] 冰刺的高度。若不填写，默认值为0。
     *      guarded (可选):[需要测试] 是否在末影水晶周围生成铁笼。若不填写，默认值为假。
     */
    public EndSpikeFeatureJsonBuilder addSpike(
            @Nullable Integer centerX,
            @Nullable Integer centerZ,
            @Nullable Integer radius ,
            @Nullable Integer height ,
            @Nullable Boolean guarded) {

        JsonObject spike = new JsonObject();
        if (centerX != null) spike.addProperty("centerX", centerX);
        if (centerZ != null) spike.addProperty("centerZ", centerZ);
        if (radius  != null) spike.addProperty("radius", radius );
        if (height  != null) spike.addProperty("height", height );
        if (guarded != null) spike.addProperty("guarded", guarded);
        spikes.add(spike);
        return this;
    }

    /**
     *  crystal_beam_target (可选):[需要测试] 该水晶光柱指向的方块位置。
     *      该位置的x坐标。
     *      该位置的y坐标。
     *      该位置的z坐标。
     */
    public EndSpikeFeatureJsonBuilder crystal_beam_target(int x, int y, int z) {
        JsonArray crystal_beam_target = new JsonArray();
        crystal_beam_target.add(x);
        crystal_beam_target.add(y);
        crystal_beam_target.add(z);
        config.add("crystal_beam_target", crystal_beam_target);
        return this;
    }

    @Override
    public JsonObject build() {
        config.add("spikes", spikes);
        return super.build();
    }
}
