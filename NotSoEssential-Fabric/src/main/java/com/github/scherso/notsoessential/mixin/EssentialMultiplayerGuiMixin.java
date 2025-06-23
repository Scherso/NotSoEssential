package com.github.scherso.notsoessential.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

@Pseudo
@SuppressWarnings("UnresolvedMixinReference")
@Mixin(targets = "gg.essential.gui.multiplayer.EssentialMultiplayerGui", remap = false)
public class EssentialMultiplayerGuiMixin
{

    /**
     * Hides the Essential Multiplayer GUI.
     *
     * @param ci {@link CallbackInfo}
     * @author U9G (<a href="https://github.com/U9G">...</a>)
     */
    @Inject(method = "initGui(Lnet/minecraft/class_500;)V", at = @At("TAIL"), remap = false)
    private void nse$initGui(final CallbackInfo ci)
    {
        for (Field field : this.getClass().getDeclaredFields())
        {
            field.setAccessible(true);
            Object fieldValue;

            try
            {
                fieldValue = field.get(this);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }

            if (fieldValue == null) continue;

            try
            {
                Method hideMethod = fieldValue.getClass().getMethod("hide");
                hideMethod.invoke(fieldValue);
            } catch (Exception e)
            {
                try
                {
                    Field visible = fieldValue.getClass().getField("visible");
                    visible.set(fieldValue, false);
                } catch (Exception ignored) {}
            }
        }
    }

}
