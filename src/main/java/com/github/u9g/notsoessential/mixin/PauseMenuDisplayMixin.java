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
@Mixin(targets = "gg.essential.handlers.PauseMenuDisplay")
public class PauseMenuDisplayMixin {
    @Inject(method = "Lgg/essential/handlers/PauseMenuDisplay;init(Lnet/minecraft/client/gui/GuiScreen;Z)V", at = @At("TAIL"))
    public void init(CallbackInfo ci) {
        for (Field field : this.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                Object fieldValue = field.get(this);
                if (fieldValue == null) continue;
                Method hideMethod = fieldValue.getClass().getMethod("hide");
                hideMethod.invoke(fieldValue);
            } catch (Exception ignored) {}
        }
    }
}