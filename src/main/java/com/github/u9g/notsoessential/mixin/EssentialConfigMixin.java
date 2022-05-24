package com.github.u9g.notsoessential.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiMultiplayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Pseudo
@SuppressWarnings("UnresolvedMixinReference")
@Mixin(targets = "gg.essential.config.EssentialConfig")
public class EssentialConfigMixin {

    @Inject(method = "getEssentialFull()Z", at = @At("RETURN"), cancellable = true, remap = false)
    public void getEssentialFull(CallbackInfoReturnable<Boolean> clr) {
        if (!(Minecraft.getMinecraft().currentScreen instanceof GuiMultiplayer)) return;
        clr.setReturnValue(false);
    }

    @Inject(method = "getEssentialFull()Z", at = @At("RETURN"), cancellable = true, remap = false)
    public void getEssentialFullMenu(CallbackInfoReturnable<Boolean> clr) {
        if (Minecraft.getMinecraft().currentScreen instanceof GuiMainMenu)
            clr.setReturnValue(true);
        else if (Minecraft.getMinecraft().currentScreen instanceof GuiIngameMenu)
            clr.setReturnValue(true);
    }

    @Inject(method = "getShowEssentialIndicatorOnTab()Z", at = @At("RETURN"), cancellable = true, remap = false)
    public void getTabIndicators(CallbackInfoReturnable<Boolean> clr) {
        clr.setReturnValue(false);
    }

    @Inject(method = "getShowEssentialIndicatorOnNametag()Z", at = @At("RETURN"), cancellable = true, remap = false)
    public void getNameTagIndicators(CallbackInfoReturnable<Boolean> clr) {
        clr.setReturnValue(false);
    }

    @Inject(method = "getStreamerMode()Z", at = @At("RETURN"), cancellable = true, remap = false)
    public void getStreamerMode(CallbackInfoReturnable<Boolean> clr) {
        clr.setReturnValue(true);
    }

    @Inject(method = "getDisableAllNotifications()Z", at = @At("RETURN"), cancellable = true, remap = false)
    public void getDisableAllNotifications(CallbackInfoReturnable<Boolean> clr) {
        clr.setReturnValue(true);
    }

    @Inject(method = "getOpenToFriends()Z", at = @At("RETURN"), cancellable = true, remap = false)
    public void getOpenToFriends(CallbackInfoReturnable<Boolean> clr) {
        clr.setReturnValue(false);
    }

}
