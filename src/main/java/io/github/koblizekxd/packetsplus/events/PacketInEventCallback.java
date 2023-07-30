package io.github.koblizekxd.packetsplus.events;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.network.packet.Packet;
import net.minecraft.util.ActionResult;

public interface PacketInEventCallback {
    Event<PacketInEventCallback> EVENT_PRE = createBackend();
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
