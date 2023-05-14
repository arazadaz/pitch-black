package com.glitaz.pitchblack.RegisterHandlers;


import com.glitaz.pitchblack.common.events.PlayerJoinCallback;
import com.glitaz.pitchblack.common.networking.ModVerify;
import net.fabricmc.fabric.api.networking.v1.ServerLoginNetworking;

public class ServerInitRH {

	private static void registerEvents(){
		PlayerJoinCallback.registerDS();
	}

	private static void registerNetworkConstants(){
		ServerLoginNetworking.registerGlobalReceiver(ModVerify.GAME_PACKET_VERSION_CHECK, ModVerify::onClientResponse);
	}

	public static void registerAll(){
		registerEvents();
		registerNetworkConstants();
	}



}
