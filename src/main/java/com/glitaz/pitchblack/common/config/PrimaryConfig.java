package com.glitaz.pitchblack.common.config;

import com.glitaz.pitchblack.PitchBlack;
import net.minecraft.util.math.MathHelper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class PrimaryConfig {

	private static String playerShaderBlacklist;

	public static void initConfig() {
		final File configFile = getConfigFile();
		final Properties properties = new Properties();

		if (configFile.exists()) {
			try (FileInputStream stream = new FileInputStream(configFile)) {
				properties.load(stream);
			} catch (final IOException e) {
				PitchBlack.LOG.warn("[Pitch Black] Could not read property file '" + configFile.getAbsolutePath() + "'", e);
			}
		}

		PitchBlack.ignoreMoonPhase = properties.computeIfAbsent("ignore_moon_phase", (a) -> "false").equals("true");
		PitchBlack.blockLightOnly = properties.computeIfAbsent("only_affect_block_light", (a) -> "false").equals("true");
		PitchBlack.darkOverworld = properties.computeIfAbsent("dark_overworld", (a) -> "true").equals("true");
		PitchBlack.darkDefault = properties.computeIfAbsent("dark_default", (a) -> "true").equals("true");
		PitchBlack.darkNether = properties.computeIfAbsent("dark_nether", (a) -> "true").equals("true");
		PitchBlack.darkEnd = properties.computeIfAbsent("dark_end", (a) -> "true").equals("true");
		PitchBlack.darkSkyless = properties.computeIfAbsent("dark_skyless", (a) -> "true").equals("true");
		playerShaderBlacklist = properties.computeIfAbsent("player_shader_blacklist", (a) -> "[]").toString();

		try {
			PitchBlack.darkNetherFogConfigured = Double.parseDouble(properties.computeIfAbsent("dark_nether_fog", (a) -> "0.5").toString());
			PitchBlack.darkNetherFogConfigured = MathHelper.clamp(PitchBlack.darkNetherFogConfigured, 0.0, 1.0);
		} catch (final Exception e) {
			PitchBlack.darkNetherFogConfigured = 0.5;
			PitchBlack.LOG.warn("[PitchBlack] Invalid configuration value for 'dark_nether_fog'. Using default value.");
		}

		try {
			PitchBlack.darkEndFogConfigured = Double.parseDouble(properties.computeIfAbsent("dark_end_fog", (a) -> "0.0").toString());
			PitchBlack.darkEndFogConfigured = MathHelper.clamp(PitchBlack.darkEndFogConfigured, 0.0, 1.0);
		} catch (final Exception e) {
			PitchBlack.darkEndFogConfigured = 0.0;
			PitchBlack.LOG.warn("[PitchBlack] Invalid configuration value for 'dark_end_fog'. Using default value.");
		}

		computeConfigValues();

		saveConfig();
	}


	private static File getConfigFile() {
		final File configDir = Platform.configDirectory().toFile();

		if (!configDir.exists()) {
			PitchBlack.LOG.warn("[Pitch Black] Could not access configuration directory: " + configDir.getAbsolutePath());
		}

		return new File(configDir, "pitchblack.properties");
	}

	public static void saveConfig() {
		final File configFile = getConfigFile();
		final Properties properties = new Properties();

		properties.put("only_affect_block_light", Boolean.toString(PitchBlack.blockLightOnly));
		properties.put("ignore_moon_phase", Boolean.toString(PitchBlack.ignoreMoonPhase));
		properties.put("dark_overworld", Boolean.toString(PitchBlack.darkOverworld));
		properties.put("dark_default", Boolean.toString(PitchBlack.darkDefault));
		properties.put("dark_nether", Boolean.toString(PitchBlack.darkNether));
		properties.put("dark_nether_fog", Double.toString(PitchBlack.darkNetherFogConfigured));
		properties.put("dark_end", Boolean.toString(PitchBlack.darkEnd));
		properties.put("dark_end_fog", Double.toString(PitchBlack.darkEndFogConfigured));
		properties.put("dark_skyless", Boolean.toString(PitchBlack.darkSkyless));
		properties.put("player_shader_blacklist", PitchBlack.playerShaderBlacklist.toString());

		try (FileOutputStream stream = new FileOutputStream(configFile)) {
			properties.store(stream, "Pitch Black properties file");
		} catch (final IOException e) {
			PitchBlack.LOG.warn("[PitchBlack] Could not store property file '" + configFile.getAbsolutePath() + "'", e);
		}
	}


	private static void computeConfigValues() {
		PitchBlack.darkNetherFogEffective = PitchBlack.darkNether ? PitchBlack.darkNetherFogConfigured : 1.0;
		PitchBlack.darkEndFogEffective = PitchBlack.darkEnd ? PitchBlack.darkEndFogConfigured : 1.0;

		PitchBlack.playerShaderBlacklist = List.of(PrimaryConfig.playerShaderBlacklist.substring(1, PrimaryConfig.playerShaderBlacklist.length() - 1).split(", "));
	}

}
