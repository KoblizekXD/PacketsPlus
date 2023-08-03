package io.github.koblizekxd.packetsplus.events;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.network.packet.Packet;
import net.minecraft.util.ActionResult;

/**
 * Base event class for outgoing packets.
 * This event will be invoked <strong>only</strong> when client sends packet.
 * To listen for this callback see <a href="https://fabricmc.net/wiki/tutorial:callbacks">this</a> tutorial on
 * Fabric Wiki
 * @see PacketOutEventCallback#EVENT
 */
public interface PacketOutEventCallback {
    /**
     * Invokes when packet is sent to remote
     */
    Event<PacketOutEventCallback> EVENT = EventFactory.createArrayBacked(
            PacketOutEventCallback.class,
            callbacks -> packet -> {
                for (PacketOutEventCallback callback : callbacks) {
                    ActionResult result = callback.interact(packet);
                    if (result != ActionResult.PASS)
                        return result;
                }
                return ActionResult.PASS;
            }
    );
    ActionResult interact(Packet<?> packet);
}
