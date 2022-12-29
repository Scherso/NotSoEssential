package com.github.u9g.notsoessential.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Pseudo
@SuppressWarnings("UnresolvedMixinReference")
@Mixin(targets = "gg.essential.handlers.PauseMenuDisplay", remap = false)
public class PauseMenuDisplayMixin
{

	@Inject(method = "init(Lnet/minecraft/class_437;)V", at = @At("HEAD"), cancellable = true, remap = false)
	private void nse$init(final CallbackInfo ci)
	{
		ci.cancel();
	}

}
