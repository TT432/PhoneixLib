package com.nmmoc7.phoenixlib.data.dim.generator.debug;

import com.google.gson.JsonObject;

/**
 * @author DustW
 */
public class DimDebugJsonBuilder {
    JsonObject json;

    public DimDebugJsonBuilder() {
        json.addProperty("type", "minecraft:debug");
    }

    public JsonObject build() {
        return json;
    }
}
