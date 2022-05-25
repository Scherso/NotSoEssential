package com.github.u9g.notsoessential.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Pseudo
@SuppressWarnings("UnresolvedMixinReference")
@Mixin(targets = "gg.essential.gui.GuiOptionsEditor")
public class GuiOptionsEditorMixin {

    @Inject(method = "guiOptionsInit", at = @At("INVOKE"), remap = false, cancellable = true)
    private void GuiOptionsInit(CallbackInfo ci) {
        ci.cancel();
    }

}
