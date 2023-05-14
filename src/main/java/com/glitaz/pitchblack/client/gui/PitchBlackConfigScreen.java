/*
 * This file is part of True Darkness and is licensed to the project under
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

package com.glitaz.pitchblack.client.gui;

import com.glitaz.pitchblack.PitchBlack;
import com.glitaz.pitchblack.common.config.PrimaryConfig;
import com.glitaz.pitchblack.common.networking.ConfigNetworking;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.CheckboxWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.ScreenTexts;
import net.minecraft.text.Text;

public class PitchBlackConfigScreen extends Screen {
	protected final Screen parent;

	protected CheckboxWidget blockLightOnlyWidget;
	protected CheckboxWidget ignoreMoonPhaseWidget;
	protected CheckboxWidget darkOverworldWidget;
	protected CheckboxWidget darkNetherWidget;
	protected CheckboxWidget darkEndWidget;
	protected CheckboxWidget darkDefaultWidget;
	protected CheckboxWidget darkSkylessWidget;

	public PitchBlackConfigScreen(Screen parent) {
		super(Text.translatable("config.darkness.title"));
		this.parent = parent;
	}

	@Override
	public void removed() {
//		if(client.getServer().isDedicated() && client.player.hasPermissionLevel(4)) {
//			PrimaryConfig.saveConfig();
//		}
	}

	@Override
	public void closeScreen() {
		client.setScreen(parent);
	}

	@Override
	protected void init() {
		int i = 27;
		blockLightOnlyWidget = new CheckboxWidget(width / 2 - 100, i, 200, 20, Text.translatable("config.darkness.label.block_light_only"), PitchBlack.blockLightOnly) {
			@Override
			public void renderButton(MatrixStack matrices, int mouseX, int mouseY, float delta) {
				super.renderButton(matrices, mouseX, mouseY, delta);

				if (isHoveredOrFocused()) {
					PitchBlackConfigScreen.this.renderTooltip(matrices, Text.translatable("config.darkness.help.block_light_only"), mouseX, mouseY);
				}
			}
		};

		i += 27;

		ignoreMoonPhaseWidget = new CheckboxWidget(width / 2 - 100, i, 200, 20, Text.translatable("config.darkness.label.ignore_moon_phase"), PitchBlack.ignoreMoonPhase) {
			@Override
			public void renderButton(MatrixStack matrices, int mouseX, int mouseY, float delta) {
				super.renderButton(matrices, mouseX, mouseY, delta);

				if (isHoveredOrFocused()) {
					PitchBlackConfigScreen.this.renderTooltip(matrices, Text.translatable("config.darkness.help.ignore_moon_phase"), mouseX, mouseY);
				}
			}
		};

		i += 27;

		darkOverworldWidget = new CheckboxWidget(width / 2 - 100, i, 200, 20, Text.translatable("config.darkness.label.dark_overworld"), PitchBlack.darkOverworld) {
			@Override
			public void renderButton(MatrixStack matrices, int mouseX, int mouseY, float delta) {
				super.renderButton(matrices, mouseX, mouseY, delta);

				if (isHoveredOrFocused()) {
					PitchBlackConfigScreen.this.renderTooltip(matrices, Text.translatable("config.darkness.help.dark_overworld"), mouseX, mouseY);
				}
			}
		};

		i += 27;

		darkNetherWidget = new CheckboxWidget(width / 2 - 100, i, 200, 20, Text.translatable("config.darkness.label.dark_nether"), PitchBlack.darkNether) {
			@Override
			public void renderButton(MatrixStack matrices, int mouseX, int mouseY, float delta) {
				super.renderButton(matrices, mouseX, mouseY, delta);

				if (isHoveredOrFocused()) {
					PitchBlackConfigScreen.this.renderTooltip(matrices, Text.translatable("config.darkness.help.dark_nether"), mouseX, mouseY);
				}
			}
		};

		i += 27;

		darkEndWidget = new CheckboxWidget(width / 2 - 100, i, 200, 20, Text.translatable("config.darkness.label.dark_end"), PitchBlack.darkEnd) {
			@Override
			public void renderButton(MatrixStack matrices, int mouseX, int mouseY, float delta) {
				super.renderButton(matrices, mouseX, mouseY, delta);

				if (isHoveredOrFocused()) {
					PitchBlackConfigScreen.this.renderTooltip(matrices, Text.translatable("config.darkness.help.dark_end"), mouseX, mouseY);
				}
			}
		};

		i += 27;

		darkDefaultWidget = new CheckboxWidget(width / 2 - 100, i, 200, 20, Text.translatable("config.darkness.label.dark_default"), PitchBlack.darkDefault) {
			@Override
			public void renderButton(MatrixStack matrices, int mouseX, int mouseY, float delta) {
				super.renderButton(matrices, mouseX, mouseY, delta);

				if (isHoveredOrFocused()) {
					PitchBlackConfigScreen.this.renderTooltip(matrices, Text.translatable("config.darkness.help.dark_default"), mouseX, mouseY);
				}
			}
		};

		i += 27;

		darkSkylessWidget = new CheckboxWidget(width / 2 - 100, i, 200, 20, Text.translatable("config.darkness.label.dark_skyless"), PitchBlack.darkSkyless) {
			@Override
			public void renderButton(MatrixStack matrices, int mouseX, int mouseY, float delta) {
				super.renderButton(matrices, mouseX, mouseY, delta);

				if (isHoveredOrFocused()) {
					PitchBlackConfigScreen.this.renderTooltip(matrices, Text.translatable("config.darkness.help.dark_skyless"), mouseX, mouseY);
				}
			}
		};

		i += 27;

		addDrawableChild(blockLightOnlyWidget);
		addDrawableChild(ignoreMoonPhaseWidget);
		addDrawableChild(darkOverworldWidget);
		addDrawableChild(darkNetherWidget);
		addDrawableChild(darkEndWidget);
		addDrawableChild(darkDefaultWidget);
		addDrawableChild(darkSkylessWidget);

		addDrawableChild(new ButtonWidget(width / 2 - 100, height - 27, 200, 20, ScreenTexts.DONE, (buttonWidget) -> {

			if(client.isIntegratedServerRunning() ) {

				PacketByteBuf buf = PacketByteBufs.create();
				buf.writeBoolean(blockLightOnlyWidget.isChecked());
				buf.writeBoolean(ignoreMoonPhaseWidget.isChecked());
				buf.writeBoolean(darkOverworldWidget.isChecked());
				buf.writeBoolean(darkNetherWidget.isChecked());
				buf.writeBoolean(darkEndWidget.isChecked());
				buf.writeBoolean(darkDefaultWidget.isChecked());
				buf.writeBoolean(darkSkylessWidget.isChecked());
				ClientPlayNetworking.send(ConfigNetworking.GAME_PACKET_UPDATE_PRIMARY_CONFIG_C2S, buf);

				client.setScreen(parent);
			}else if(client.player != null){
				if(client.player.hasPermissionLevel(4)) {
					PacketByteBuf buf = PacketByteBufs.create();
					buf.writeBoolean(blockLightOnlyWidget.isChecked());
					buf.writeBoolean(ignoreMoonPhaseWidget.isChecked());
					buf.writeBoolean(darkOverworldWidget.isChecked());
					buf.writeBoolean(darkNetherWidget.isChecked());
					buf.writeBoolean(darkEndWidget.isChecked());
					buf.writeBoolean(darkDefaultWidget.isChecked());
					buf.writeBoolean(darkSkylessWidget.isChecked());
					ClientPlayNetworking.send(ConfigNetworking.GAME_PACKET_UPDATE_PRIMARY_CONFIG_C2S, buf);
					client.setScreen(parent);
				}
			}else{
				client.setScreen(parent);
			}
		}));
	}

	@Override
	public void render(MatrixStack matrixStack, int i, int j, float f) {
		renderBackground(matrixStack, 0);
		drawCenteredText(matrixStack, textRenderer, title, width / 2, 5, 16777215);

		super.render(matrixStack, i, j, f);
	}
}
