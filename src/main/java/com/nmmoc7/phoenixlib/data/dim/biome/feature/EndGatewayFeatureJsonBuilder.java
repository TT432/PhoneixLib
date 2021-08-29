package com.nmmoc7.phoenixlib.data.dim.biome.feature;

import com.google.gson.JsonArray;

/**
 * @author DustW
 */
public class EndGatewayFeatureJsonBuilder extends BaseFeature<EndGatewayFeatureJsonBuilder> {
    public EndGatewayFeatureJsonBuilder() {
        super("end_gateway");
    }

    /**
     *  exit (可选):[需要测试] 该末地折跃门要把实体传送到的位置。
     *      该位置的x坐标。
     *      该位置的y坐标。
     *      该位置的z坐标。
     */
    public EndGatewayFeatureJsonBuilder exit(int x, int y, int z) {
        JsonArray exit = new JsonArray();
        exit.add(x);
        exit.add(y);
        exit.add(z);
        config.add("exit", exit);
        return this;
    }

    /** exact:[需要测试]
     *
     * 精准传送 */
    public EndGatewayFeatureJsonBuilder exact(boolean exact) {
        config.addProperty("exact", exact);
        return this;
    }
}
