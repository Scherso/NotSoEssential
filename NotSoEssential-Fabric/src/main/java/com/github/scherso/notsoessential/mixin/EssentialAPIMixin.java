package com.github.scherso.notsoessential.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Pseudo
@SuppressWarnings("UnresolvedMixinReference")
@Mixin(targets = "gg.essential.api.utils.DiKt", remap = false)
public class EssentialAPIMixin
{

    @Inject(method = "getEssentialDI", at = @At("HEAD"), cancellable = true, remap = false)
    private static void nse$getEssentialDI(CallbackInfoReturnable<Object> cir)
    {
//      cir.setReturnValue(null);
    }
}
