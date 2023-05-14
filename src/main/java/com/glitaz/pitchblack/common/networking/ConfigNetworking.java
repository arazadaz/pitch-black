package com.glitaz.pitchblack.common.networking;

import com.glitaz.pitchblack.PitchBlack;
import com.glitaz.pitchblack.common.config.PrimaryConfig;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.List;

public class ConfigNetworking {

	public static Identifier GAME_PACKET_SET_PRIMARY_CONFIG_S2C = new Identifier(PitchBlack.MODID, "gamepacketsetprimaryconfigs2c");

	public static Identifier GAME_PACKET_UPDATE_PRIMARY_CONFIG_C2S = new Identifier(PitchBlack.MODID, "gamepacketupdateprimaryconfigc2s");



	public static void setClientPrimaryConfig(MinecraftClient client, ClientPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender){
		boolean darkOverworld = buf.readBoolean();
		boolean darkDefault = buf.readBoolean();
		boolean darkNether = buf.readBoolean();
		double darkNetherFogEffective = buf.readDouble();
		double darkNetherFogConfigured = buf.readDouble();
		boolean darkEnd = buf.readBoolean();
		double darkEndFogEffective = buf.readDouble();
		double darkEndFogConfigured = buf.readDouble();
		boolean darkSkyless = buf.readBoolean();
		boolean blockLightOnly = buf.readBoolean();
		boolean ignoreMoonPhase = buf.readBoolean();
		boolean isPlayerShaderblacklisted = buf.readBoolean();

		client.execute(() ->{
			PitchBlack.darkOverworld = darkOverworld;
			PitchBlack.darkDefault = darkDefault;
			PitchBlack.darkNether = darkNether;
			PitchBlack.darkNetherFogEffective = darkNetherFogEffective;
			PitchBlack.darkNetherFogConfigured = darkNetherFogConfigured;
			PitchBlack.darkEnd = darkEnd;
			PitchBlack.darkEndFogEffective = darkEndFogEffective;
			PitchBlack.darkEndFogConfigured = darkEndFogConfigured;
			PitchBlack.darkSkyless = darkSkyless;
			PitchBlack.blockLightOnly = blockLightOnly;
			PitchBlack.ignoreMoonPhase = ignoreMoonPhase;
			PitchBlack.isPlayerShaderBlacklisted = isPlayerShaderblacklisted;

			if(blackListedMods(isPlayerShaderblacklisted)){
				handler.getConnection().disconnect(Text.of("Blacklisted mods or shaders."));
			}
		});
	}

	public static void updateClientPrimaryConfig(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender){
		boolean blockLightOnly = buf.readBoolean();
		boolean ignoreMoonPhase = buf.readBoolean();
		boolean darkOverworld = buf.readBoolean();
		boolean darkNether = buf.readBoolean();
		boolean darkEnd = buf.readBoolean();
		boolean darkDefault = buf.readBoolean();
		boolean darkSkyless = buf.readBoolean();

		server.execute(() ->{
			PitchBlack.darkOverworld = darkOverworld;
			PitchBlack.darkDefault = darkDefault;
			PitchBlack.darkNether = darkNether;
			PitchBlack.darkEnd = darkEnd;
			PitchBlack.darkSkyless = darkSkyless;
			PitchBlack.blockLightOnly = blockLightOnly;
			PitchBlack.ignoreMoonPhase = ignoreMoonPhase;
		});
		PrimaryConfig.saveConfig();

		buf = PacketByteBufs.create();
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
		//Unneeded for in game config update, but this is the playerShaderBlacklist value
		buf.writeString("");

		Packet<?> packet = ServerPlayNetworking.createS2CPacket(ConfigNetworking.GAME_PACKET_SET_PRIMARY_CONFIG_S2C, buf);

		server.getPlayerManager().sendToAll(packet);

//		ServerPlayNetworking.send((ServerPlayerEntity) handler.getPlayer(), ConfigNetworking.GAME_PACKET_SET_PRIMARY_CONFIG_S2C, buf);

	}

	private static boolean blackListedMods(boolean isPlayerShaderBlacklisted){
		if(FabricLoader.getInstance().isModLoaded("cateyes")){
			return true;
		}
		if(isPlayerShaderBlacklisted && FabricLoader.getInstance().isModLoaded("iris")){
			return true;
		}
		return false;
	}
}
