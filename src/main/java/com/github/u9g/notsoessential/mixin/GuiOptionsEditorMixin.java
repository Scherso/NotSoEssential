package com.github.u9g.notsoessential.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

@SuppressWarnings("UnresolvedMixinReference")
@Pseudo
@Mixin(targets = "gg.essential.gui.GuiOptionsEditor")
public class GuiOptionsEditorMixin {

    /*
    this only works in a dev env, I'll try to fix it later :sob:
     */
    @Inject(method = "Lgg/essential/gui/GuiOptionsEditor;guiOptionsInit()V", at = @At("HEAD"), cancellable = true)
    private void GuiOptionsInit(CallbackInfo ci) {
        Arrays.stream(this.getClass().getDeclaredFields()).forEach(field -> field.setAccessible(true));
        ci.cancel();
    }

    @Inject(method = "Lgg/essential/gui/GuiOptionsEditor;guiClick()V", at = @At("HEAD"), cancellable = true)
    public void GuiClick(CallbackInfo ci) {
        ci.cancel();
    }

}
