package com.github.u9g.notsoessential.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Pseudo
@SuppressWarnings("UnresolvedMixinReference")
@Mixin(targets = "gg.essential.handlers.OnlineIndicator", remap = false)
public class OnlineIndicatorMixin
{

    @Inject(method = {
        "drawNametagIndicator(Lgg/essential/universal/UMatrixStack;Lnet/minecraft/class_1297;Ljava/lang/String;I)V",
        "drawTabIndicator(Lgg/essential/universal/UMatrixStack;Lnet/minecraft/class_640;II)V"
    }, at = @At("HEAD"), cancellable = true, remap = false)
    private void nse$overrideOnlineIndicator(final CallbackInfo ci)
    {
        ci.cancel();
    }

}
