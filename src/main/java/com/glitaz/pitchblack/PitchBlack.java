package com.glitaz.pitchblack;

/*
 * This file is part of Pitch Black and is licensed to the project under
 * terms that are compatible with the GNU Lesser General Public License.
 * See the NOTICE file distributed with this work for additional information
 * regarding copyright ownership and licensing.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */


import com.glitaz.pitchblack.RegisterHandlers.InitRH;
import com.glitaz.pitchblack.common.config.PrimaryConfig;
import net.fabricmc.api.ModInitializer;
import net.minecraft.text.Text;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;


public class PitchBlack implements ModInitializer {

	public static final Text INCORRECT_VERSION = Text.literal(String.format("Missing correct Pitch Black mod version"));
	public static final int VERSION = 101;

	public static boolean darkOverworld;
	public static boolean darkDefault;
	public static boolean darkNether;
	public static double darkNetherFogEffective;
	public static double darkNetherFogConfigured;
	public static boolean darkEnd;
	public static double darkEndFogEffective;
	public static double darkEndFogConfigured;
	public static boolean darkSkyless;
	public static boolean blockLightOnly;
	public static boolean ignoreMoonPhase;

	public static List<String> playerShaderBlacklist;
	public static boolean isPlayerShaderBlacklisted;

	public static String MODID = "pitchblack";
	public static Logger LOG = LogManager.getLogger(MODID);


	@Override
	public void onInitialize() {
		PrimaryConfig.initConfig();
		InitRH.registerAll();
	}



}





