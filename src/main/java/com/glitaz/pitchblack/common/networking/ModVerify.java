package com.glitaz.pitchblack.common.networking;

import com.glitaz.pitchblack.PitchBlack;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerLoginNetworking;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientLoginNetworkHandler;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.listener.ServerLoginPacketListener;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class ModVerify {

    public static Identifier GAME_PACKET_VERSION_CHECK = new Identifier(PitchBlack.MODID, "gamepacketversioncheck");


    /**
     * Handle the VERSION_CHECK response
     */
    public static void onClientResponse(MinecraftServer server, ServerLoginPacketListener listener, boolean understood, PacketByteBuf buf, ServerLoginNetworking.LoginSynchronizer loginSynchronizer, PacketSender packetSender)
    {
        //client did not respond in time or doesn't use the correct version, disconnect client
        if(!understood || buf.readInt() != PitchBlack.VERSION) {
            listener.getConnection().disconnect(PitchBlack.INCORRECT_VERSION);
        }
    }

    public static CompletableFuture<PacketByteBuf> onServerRequest(MinecraftClient client, ClientLoginNetworkHandler handler, PacketByteBuf buf , Consumer<GenericFutureListener<? extends Future<? super Void>>> genericFutureListenerConsumer)
    {
        //VERSION_CHECK request received from server, send back own version
        buf = PacketByteBufs.create();
        buf.writeInt(PitchBlack.VERSION);

        return CompletableFuture.completedFuture(buf);

    }


}
