package com.glitaz.pitchblack;


import com.glitaz.pitchblack.RegisterHandlers.ServerInitRH;
import net.fabricmc.api.DedicatedServerModInitializer;



public class PitchBlackServer implements DedicatedServerModInitializer {

		@Override
		public void onInitializeServer() {
			ServerInitRH.registerAll();
		}

}
