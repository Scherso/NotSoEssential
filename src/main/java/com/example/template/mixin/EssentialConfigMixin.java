package com.example.template.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMultiplayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Pseudo
@Mixin(targets = "gg.essential.config.EssentialConfig")
public class EssentialConfigMixin {
    @Inject(method = "Lgg/essential/config/EssentialConfig;getEssentialFull()Z", at = @At("RETURN"), cancellable = true)
    public void getEssentialFull(CallbackInfoReturnable<Boolean> clr) {
        if (Minecraft.getMinecraft().currentScreen instanceof GuiMultiplayer) {
            clr.setReturnValue(false);
        }
    }
}
