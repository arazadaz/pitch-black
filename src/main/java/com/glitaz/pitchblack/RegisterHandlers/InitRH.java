package com.glitaz.pitchblack.RegisterHandlers;

import com.glitaz.pitchblack.common.events.PlayerJoinCallback;
import com.glitaz.pitchblack.common.networking.ConfigNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;


public class InitRH {

	private static void registerEvents(){
		PlayerJoinCallback.register();
	}


	private static void registerServerNetworkConstants() {

		ServerPlayNetworking.registerGlobalReceiver(ConfigNetworking.GAME_PACKET_UPDATE_PRIMARY_CONFIG_C2S, ConfigNetworking::updateClientPrimaryConfig);

	}


	public static void registerAll() {
		registerEvents();
		registerServerNetworkConstants();
	}

}
