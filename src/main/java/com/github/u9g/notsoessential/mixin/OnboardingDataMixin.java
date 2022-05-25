package com.github.u9g.notsoessential.mixin;

import gg.essential.api.data.OnboardingData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = OnboardingData.class)
public abstract class OnboardingDataMixin implements gg.essential.api.data.OnboardingData {

    /**
     * Taken from unessential under the MIT License
     * https://github.com/Steviegt6/unessential/blob/master/LICENSE
     *
     * @author Steviegt6, https://github.com/Steviegt6
     */
    @Inject(method = "hasDeniedEssentialTOS", at = @At("HEAD"), remap = false, cancellable = true)
    private static void overrideHasDeniedTos(CallbackInfoReturnable<Boolean> clr) {
        clr.setReturnValue(true);
    }

    /**
     * Taken from unessential under the MIT License
     * https://github.com/Steviegt6/unessential/blob/master/LICENSE
     *
     * @author Steviegt6, https://github.com/Steviegt6
     */
    @Inject(method = "hasAcceptedEssentialTOS", at = @At("HEAD"), remap = false, cancellable = true)
    private void hasAcceptedEssentialTOS(CallbackInfoReturnable<Boolean> clr) {
        clr.setReturnValue(false);
    }

}
