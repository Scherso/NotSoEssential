package com.github.u9g.notsoessential.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.resources.I18n;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Pseudo
@Mixin(GuiMultiplayer.class)
public class GuiMultiplayerMixin {
    @Redirect(
            method = "drawScreen(IIF)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiMultiplayer;drawCenteredString(Lnet/minecraft/client/gui/FontRenderer;Ljava/lang/String;III)V")
    )
    public void injected(GuiMultiplayer instance, FontRenderer fontRenderer, String s, int x, int y, int color) {
        if (!(Minecraft.getMinecraft().currentScreen instanceof GuiMultiplayer)) return;
        instance.drawCenteredString(fontRenderer, I18n.format("multiplayer.title", new Object[0]), instance.width / 2, 20, 16777215);
    }

}
