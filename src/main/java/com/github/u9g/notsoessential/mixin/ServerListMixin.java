package com.github.u9g.notsoessential.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

@Pseudo
@SuppressWarnings("UnresolvedMixinReference")
@Mixin(targets = "gg.essential.gui.multiplayer.EssentialMultiplayerGui")
public class ServerListMixin {

    @Inject(method = "initGui(Lnet/minecraft/client/gui/GuiScreen;)V", at = @At("TAIL"), remap = false)
    public void initGui(CallbackInfo ci) {
        for (Field field : this.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            Object fieldValue = null;
            try {
                fieldValue = field.get(this);
            } catch (Exception ignored) {
            }
            if (fieldValue == null) continue;
            try { // it's a UIElement from essential
                Method hideMethod = fieldValue.getClass().getMethod("hide");
                hideMethod.invoke(fieldValue);
            } catch (Exception e) {
                try { // it's a `net.minecraft.client.gui.GuiButton`
                    Field visible = fieldValue.getClass().getField("visible");
                    visible.set(fieldValue, false);
                } catch (Exception ignored) {
                }
            }
        }
    }
}
