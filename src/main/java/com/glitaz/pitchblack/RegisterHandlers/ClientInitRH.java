package com.glitaz.pitchblack.RegisterHandlers;

import com.glitaz.pitchblack.common.networking.ConfigNetworking;
import com.glitaz.pitchblack.common.networking.ModVerify;
import net.fabricmc.fabric.api.client.networking.v1.ClientLoginNetworking;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerLoginNetworking;


public class ClientInitRH {

	private static void registerClientNetworkConstants() {

		ClientPlayNetworking.registerGlobalReceiver(ConfigNetworking.GAME_PACKET_SET_PRIMARY_CONFIG_S2C, ConfigNetworking::setClientPrimaryConfig);
		ClientLoginNetworking.registerGlobalReceiver(ModVerify.GAME_PACKET_VERSION_CHECK, ModVerify::onServerRequest);

	}


	public static void registerAll(){
		registerClientNetworkConstants();
	}

}
