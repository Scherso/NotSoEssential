package com.github.u9g.notsoessential.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Pseudo
@SuppressWarnings("UnresolvedMixinReference")
@Mixin(targets = "gg.essential.key.EssentialKeybinding")
public class EssentialKeybindingMixin {

    @Inject(method = "Lgg/essential/key/EssentialKeybinding;register()V", at = @At("HEAD"), cancellable = true)
    public void registerKeyBinds(CallbackInfo ci) {
        ci.cancel();
    }

    @Inject(method = "Lgg/essential/key/EssentialKeybinding;EssentialKeybinding()V", at = @At("HEAD"), cancellable = true)
    public void EssentialKeybinding(CallbackInfo ci) {
        ci.cancel();
    }

}
