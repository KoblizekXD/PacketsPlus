package io.github.koblizekxd.packetsplus.events;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.network.packet.Packet;
import net.minecraft.util.ActionResult;

/**
 * Base event class for incoming packets.
 * This event will be invoked <strong>only</strong> when client receives packet.
 * To listen for this callback see <a href="https://fabricmc.net/wiki/tutorial:callbacks">this</a> tutorial on
 * Fabric Wiki
 * @see PacketInEventCallback#EVENT_PRE
 * @see PacketInEventCallback#EVENT_POST
 */
public interface PacketInEventCallback {
    /**
     * Invokes when packet is received, but was not yet processed by Minecraft
     */
    Event<PacketInEventCallback> EVENT_PRE = createBackend();
    /**
     * Invokes when packet is received and Minecraft has processed it
     */
    Event<PacketInEventCallback> EVENT_POST = createBackend();
    static Event<PacketInEventCallback> createBackend() {
        return EventFactory.createArrayBacked(
                PacketInEventCallback.class,
                callbacks -> packet -> {
                    for (PacketInEventCallback callback : callbacks) {
                        ActionResult result = callback.interact(packet);
                        if (result != ActionResult.PASS)
                            return result;
                    }
                    return ActionResult.PASS;
                }
        );
    }
    ActionResult interact(Packet<?> packet);
}
