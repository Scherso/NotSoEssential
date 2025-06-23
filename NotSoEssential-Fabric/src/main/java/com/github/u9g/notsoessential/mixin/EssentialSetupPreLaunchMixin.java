package com.github.u9g.notsoessential.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mixins;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Pseudo
@SuppressWarnings("UnresolvedMixinReference")
@Mixin(targets = "gg.essential.loader.stage0.EssentialSetupPreLaunch", remap = false)
public class EssentialSetupPreLaunchMixin
{

    @Inject(method = "onPreLaunch", at = @At("RETURN"), remap = false)
    private void nse$onPreLaunch(final CallbackInfo ci)
    {
        Mixins.addConfiguration("mixins.notsoessential.json");
    }

}
