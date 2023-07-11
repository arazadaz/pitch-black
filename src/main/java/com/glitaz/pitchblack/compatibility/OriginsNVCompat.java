package com.glitaz.pitchblack.compatibility;


import io.github.apace100.apoli.Apoli;
import io.github.apace100.apoli.component.PowerHolderComponent;
import io.github.apace100.apoli.power.NightVisionPower;
import io.github.apace100.apoli.power.PowerType;
import io.github.apace100.apoli.power.PowerTypeReference;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;

public class OriginsNVCompat {

    public static float getOriginNVStrenth(LivingEntity target){

        PowerHolderComponent powerHolderComponent = PowerHolderComponent.KEY.get(target);

        //NightVisionPower power = powerHolderComponent.getPower(new PowerTypeReference<>(new Identifier(Apoli.MODID,"night_vision")));


        for (PowerType<?> powerType : powerHolderComponent.getPowerTypes(false)) {

            if(powerType.get(target) instanceof NightVisionPower nvPower){
                if(nvPower.isActive()) {
                    return nvPower.getStrength();
                }
            }

        }

        return 0.0F;

    }

}
