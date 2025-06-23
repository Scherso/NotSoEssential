package com.github.scherso.notsoessential.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Pseudo
@SuppressWarnings("UnresolvedMixinReference")
@Mixin(targets = "gg.essential.config.EssentialConfig", remap = false)
public class EssentialConfigMixin
{

    @Inject(method = {
        "getShowEssentialIndicatorOnTab()Z",
        "getShowEssentialIndicatorOnNametag()Z",
        "getEssentialEnabled()Z",
        "getEssentialFull()Z"
    }, at = @At("HEAD"), cancellable = true, remap = false)
    private void nse$overrideEssentialConfigF(final CallbackInfoReturnable<Boolean> clr)
    {
        clr.setReturnValue(false);
    }

    @Inject(method = {
        "getDisableCosmetics()Z",
        "getDisableAllNotifications()Z"
    }, at = @At("HEAD"), cancellable = true, remap = false)
    private void nse$overrideEssentialConfigT(final CallbackInfoReturnable<Boolean> clr)
    {
        clr.setReturnValue(true);
    }

}
