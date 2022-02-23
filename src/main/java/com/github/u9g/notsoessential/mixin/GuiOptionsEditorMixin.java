package com.github.u9g.notsoessential.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Pseudo
@Mixin(targets = "gg.essential.gui.GuiOptionsEditor")
public class GuiOptionsEditorMixin {

    @Inject(method = "Lgg/essential/gui/GuiOptionsEditor;guiOptionsInit()V", at = @At("HEAD"), cancellable = true)
    private void GuioptionsInit(CallbackInfo ci) {
        ci.cancel();
    }

}
