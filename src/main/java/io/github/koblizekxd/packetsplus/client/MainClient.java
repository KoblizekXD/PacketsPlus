package io.github.koblizekxd.packetsplus.client;

import io.github.koblizekxd.packetsplus.Main;
import net.fabricmc.api.ClientModInitializer;

public class MainClient implements ClientModInitializer {
    /**
     * Runs the mod initializer on the client environment.
     */
    @Override
    public void onInitializeClient() {
        Main.LOGGER.info("");
        Main.LOGGER.info("Using Packets+ networking library(v{}-client)", Main.VERSION.getFriendlyString());
        Main.LOGGER.info("");
    }
}
