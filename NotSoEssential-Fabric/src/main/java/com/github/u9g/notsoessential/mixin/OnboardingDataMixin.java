package com.github.u9g.notsoessential.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Pseudo
@SuppressWarnings("UnresolvedMixinReference")
@Mixin(targets = "gg.essential.data.OnboardingData", remap = false)
public class OnboardingDataMixin
{

	@Inject(method = {"hasAcceptedTos", /* "hasAcceptedEssentialTOS" */}, at = @At("HEAD"), cancellable = true, remap = false)
	private static void nse$hasAcceptedTos(final CallbackInfoReturnable<Boolean> clr)
	{
		clr.setReturnValue(false);
	}

	@Inject(method = {"hasDeniedTos", /* "hasDeniedEssentialTOS" */}, at = @At("HEAD"), cancellable = true, remap = false)
	private static void nse$hasDeniedTos(final CallbackInfoReturnable<Boolean> clr)
	{
		clr.setReturnValue(true);
	}

}
