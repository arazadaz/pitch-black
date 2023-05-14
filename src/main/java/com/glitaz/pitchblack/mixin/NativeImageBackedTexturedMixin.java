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

package com.glitaz.pitchblack.mixin;


import com.glitaz.pitchblack.PitchBlack;
import com.glitaz.pitchblack.PitchBlackClient;
import mixinhelper.TextureAccess;
import com.mojang.blaze3d.texture.NativeImage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.client.texture.NativeImageBackedTexture;

@Mixin(NativeImageBackedTexture.class)
public class NativeImageBackedTexturedMixin implements TextureAccess {
	@Shadow
	NativeImage image;

	private boolean enableHook = false;

	@Inject(method = "upload", at = @At(value = "HEAD"))
	private void onUpload(CallbackInfo ci) {
		if (enableHook && PitchBlackClient.enabled && image != null) {
			final NativeImage img = image;

			for (int b = 0; b < 16; b++) {
				for (int s = 0; s < 16; s++) {
					final int color = PitchBlackClient.darken(img.getPixelColor(b, s), b, s);
					img.setPixelColor(b, s, color);
				}
			}
		}
	}

	@Override
	public void darkness_enableUploadHook() {
		enableHook = true;
	}
}
