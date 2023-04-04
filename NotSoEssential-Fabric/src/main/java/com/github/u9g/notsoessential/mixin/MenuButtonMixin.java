package com.github.u9g.notsoessential.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Pseudo
@SuppressWarnings("UnresolvedMixinReference")
@Mixin(targets = "gg.essential.gui.common.MenuButton", remap = false)
public class MenuButtonMixin
{

	@Inject(method = {
		"<init>" // No descriptor to match all constructors.
	}, at = @At("HEAD"), cancellable = true, remap = false)
	private void nse$guiOptionsInit(final CallbackInfo ci)
	{
		ci.cancel();
	}

}
