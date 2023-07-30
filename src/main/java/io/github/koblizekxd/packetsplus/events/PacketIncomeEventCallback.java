package io.github.koblizekxd.packetsplus.events;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.network.packet.Packet;
import net.minecraft.util.ActionResult;

public interface PacketIncomeEventCallback {
    Event<PacketIncomeEventCallback> EVENT_PRE = createBackend();
    Event<PacketIncomeEventCallback> EVENT_POST = createBackend();
    static Event<PacketIncomeEventCallback> createBackend() {
        return EventFactory.createArrayBacked(
                PacketIncomeEventCallback.class,
                callbacks -> packet -> {
                    for (PacketIncomeEventCallback callback : callbacks) {
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
