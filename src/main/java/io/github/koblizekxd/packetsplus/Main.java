package io.github.koblizekxd.packetsplus;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.Version;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("Packets+");
    public static final Version VERSION = FabricLoader.getInstance().getModContainer("packetsplus")
            .get().getMetadata().getVersion();
    /**
     * Runs the mod initializer.
     */
    @Override
    public void onInitialize() {

    }
}
