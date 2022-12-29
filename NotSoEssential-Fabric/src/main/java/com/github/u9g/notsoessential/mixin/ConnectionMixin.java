package com.github.u9g.notsoessential.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Pseudo
@SuppressWarnings("UnresolvedMixinReference")
@Mixin(targets = "gg.essential.network.connectionmanager.Connection", remap = false)
public class ConnectionMixin
{

	@Inject(method = {
			"send",
			"attemptConnect",
			"attemptConnect$1385ff",
			"doAttemptConnect",
			"retryConnectWithBackoff"
	}, at = @At("HEAD"), cancellable = true, remap = false)
	private void nse$overrideConnection(final CallbackInfo ci)
	{
		ci.cancel();
	}

}
