package com.glitaz.pitchblack.common.events;

import com.glitaz.pitchblack.PitchBlack;
import com.glitaz.pitchblack.common.networking.ConfigNetworking;
import com.glitaz.pitchblack.common.networking.ModVerify;
import net.fabricmc.fabric.api.networking.v1.*;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.listener.ServerLoginPacketListener;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;



public class PlayerJoinCallback {

	public static void register(){
		ServerPlayConnectionEvents.JOIN.register(
				(handler, sender, server) -> {

					PacketByteBuf buf = PacketByteBufs.create();
					buf.writeBoolean(PitchBlack.darkOverworld);
					buf.writeBoolean(PitchBlack.darkDefault);
					buf.writeBoolean(PitchBlack.darkNether);
					buf.writeDouble(PitchBlack.darkNetherFogEffective);
					buf.writeDouble(PitchBlack.darkNetherFogConfigured);
					buf.writeBoolean(PitchBlack.darkEnd);
					buf.writeDouble(PitchBlack.darkEndFogEffective);
					buf.writeDouble(PitchBlack.darkEndFogConfigured);
					buf.writeBoolean(PitchBlack.darkSkyless);
					buf.writeBoolean(PitchBlack.blockLightOnly);
					buf.writeBoolean(PitchBlack.ignoreMoonPhase);
					boolean isPlayerShaderblacklisted = (PitchBlack.playerShaderBlacklist.contains(handler.getPlayer().getDisplayName().getString())) ? true : false;
					buf.writeBoolean(isPlayerShaderblacklisted);
					ServerPlayNetworking.send((ServerPlayerEntity) handler.getPlayer(), ConfigNetworking.GAME_PACKET_SET_PRIMARY_CONFIG_S2C, buf);

				});
	}


	public static void registerDS(){
		ServerLoginConnectionEvents.QUERY_START.register(PlayerJoinCallback::onLoginStart);
	}
	/**
	 * On login start, send VERSION_CHECK request
	 */
	private static void onLoginStart(ServerLoginPacketListener serverLoginPacketListener, MinecraftServer server, PacketSender sender, ServerLoginNetworking.LoginSynchronizer sync)
	{
		//request the client to send its Pitch Black version number
		sender.sendPacket(ModVerify.GAME_PACKET_VERSION_CHECK, PacketByteBufs.empty());
	}


}

