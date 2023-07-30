package io.github.koblizekxd.packetsplus.server;

import io.github.koblizekxd.packetsplus.Main;
import net.fabricmc.api.DedicatedServerModInitializer;
import org.apache.logging.log4j.core.pattern.AnsiEscape;
import org.apache.logging.log4j.core.pattern.JAnsiTextRenderer;
import org.apache.logging.log4j.core.tools.picocli.CommandLine;

public class MainServer implements DedicatedServerModInitializer {
    @Override
    public void onInitializeServer() {
        Main.LOGGER.info("");
        Main.LOGGER.info("Using Packets+ networking library(v1.0-server)");
        Main.LOGGER.warn("!Server version does not have any functionality!");
        Main.LOGGER.info("");
    }
}
