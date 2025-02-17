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
import net.minecraft.client.render.DimensionVisualEffects;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(DimensionVisualEffects.End.class)
public class EndDimensionVisualEffectsMixin {
	private static double MIN = 0.029999999329447746D;

	@Inject(method = "adjustFogColor", at = @At(value = "RETURN"), cancellable = true)
	private void onAdjustFogColor(CallbackInfoReturnable<Vec3d> ci) {
		final double factor = PitchBlack.darkEndFogEffective;

		if (factor != 1.0) {
			Vec3d result = ci.getReturnValue();
			result = new Vec3d(Math.max(MIN, result.x * factor), Math.max(MIN, result.y * factor), Math.max(MIN, result.z * factor));

			ci.setReturnValue(result);
		}
	}
}
