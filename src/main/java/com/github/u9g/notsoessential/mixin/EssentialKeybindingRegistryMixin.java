package com.github.u9g.notsoessential.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Pseudo
@SuppressWarnings("UnresolvedMixinReference")
@Mixin(targets = "gg.essential.key.EssentialKeybindingRegistry")
public class EssentialKeybindingRegistryMixin {

    @Inject(method = "registerKeyBinds", at = @At("INVOKE"), cancellable = true, remap = false)
    public void registerKeyBinds(CallbackInfo ci) {
        ci.cancel();
    }

}
