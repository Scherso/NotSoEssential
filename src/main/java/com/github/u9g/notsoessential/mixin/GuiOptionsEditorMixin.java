package com.github.u9g.notsoessential.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiOptions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Pseudo
@SuppressWarnings("UnresolvedMixinReference")
@Mixin(targets = "gg.essential.gui.GuiOptionsEditor")
public class GuiOptionsEditorMixin {

    @Inject(method = "guiOptionsInit()V", at = @At("HEAD"), cancellable = true, remap = false)
    private void GuiOptionsInit(CallbackInfo ci) {
        if (Minecraft.getMinecraft().currentScreen instanceof GuiOptions) {
            ci.cancel();
        }
    }

}
