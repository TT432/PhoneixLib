package com.nmmoc7.phoenixlib;

import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Phoenix.MOD_ID)
public class Phoenix {
    public static final String MOD_ID = "phoenixlib";
    public static final Logger LOGGER = LogManager.getLogger();

    public Phoenix() {
    }
}
