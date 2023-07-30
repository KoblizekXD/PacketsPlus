package io.github.koblizekxd.packetsplus.mixin;

import io.github.koblizekxd.packetsplus.events.PacketInEventCallback;
import io.github.koblizekxd.packetsplus.events.PacketOutEventCallback;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.PacketCallbacks;
import net.minecraft.network.listener.PacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.util.ActionResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientConnection.class)
public class ClientConnectionMixin {
    @Inject(method = "handlePacket", at = @At("HEAD"), cancellable = true)
    private static <T extends PacketListener> void handlePacketPre(Packet<T> packet, PacketListener listener, CallbackInfo ci) {
        ActionResult result = PacketInEventCallback.EVENT_PRE.invoker().interact(packet);
        if(result == ActionResult.FAIL) ci.cancel();
    }
    @Inject(method = "handlePacket", at = @At("RETURN"), cancellable = true)
    private static <T extends PacketListener> void handlePacketPost(Packet<T> packet, PacketListener listener, CallbackInfo ci) {
        ActionResult result = PacketInEventCallback.EVENT_POST.invoker().interact(packet);
        if(result == ActionResult.FAIL) ci.cancel();
    }
    @Inject(method = "send(Lnet/minecraft/network/packet/Packet;Lnet/minecraft/network/PacketCallbacks;)V", at = @At("HEAD"), cancellable = true)
    public void send(Packet<?> packet, PacketCallbacks callbacks, CallbackInfo ci) {
        ActionResult result = PacketOutEventCallback.EVENT.invoker().interact(packet);
        if(result == ActionResult.FAIL) ci.cancel();
    }
}
