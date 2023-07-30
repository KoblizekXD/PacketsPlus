package io.github.koblizekxd.packetsplus.events;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.network.packet.Packet;
import net.minecraft.util.ActionResult;

public interface PacketOutEventCallback {
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
