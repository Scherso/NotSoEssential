package com.github.scherso.notsoessential.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Pseudo
@SuppressWarnings("UnresolvedMixinReference")
@Mixin(targets = "gg.skytils.skytilsmod.utils.ModChecker", remap = false)
public class ModCheckerMixin
{

    @Inject(method = "getCanShowNotifications", at = @At("HEAD"), cancellable = true, remap = false)
    private static void nse$getCanShowNotifications(CallbackInfoReturnable<Boolean> cir)
    {
        cir.setReturnValue(false);
    }

    @Inject(method = "canShowNotifications_delegate$lambda$1", at = @At("HEAD"), cancellable = true, remap = false)
    private static void nse$canShowNotifications_delegate$lambda$1(CallbackInfoReturnable<Boolean> cir)
    {
        cir.setReturnValue(false);
    }
    
}
