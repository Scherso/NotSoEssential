package com.github.u9g.notsoessential.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Attempts to cancel any external connections made via Essential itself,
 * i.e. telemetry. <br>
 * Although {@link OnboardingDataMixin} already attempts to deny the terms
 * of service, which in turn would *hopefully* prevent any external connections,
 * this is a failsafe, and confirms that there is absolutely no external connections made.
 */
@Pseudo
@SuppressWarnings("UnresolvedMixinReference")
@Mixin(targets = "gg.essential.network.connectionmanager.ConnectionManager", remap = false)
public class ConnectionManagerMixin
{

	/**
	 * Cancels procedural methods in the method array, in doing this,
	 * Essential is unable to send outbound, respond inbound, or open.
	 *
	 * @param ci {@link CallbackInfo}
	 */
	@Inject(method = {
		"onOpenAsync(Lgg/essential/connectionmanager/common/packet/connection/ClientConnectionLoginPacket;)V",
		"respond(Lgg/essential/connectionmanager/common/packet/Packet;Lgg/essential/connectionmanager/common/packet/Packet;)V",
		"send", /* No descriptor in order to match all methods. */
		"close" /* No descriptor in order to match all methods. */
	}, at = @At("HEAD"), cancellable = true, remap = false)
	private void nse$overrideConnectionManager(final CallbackInfo ci)
	{
		ci.cancel();
	}

	/**
	 * Returns boolean functions as false. Setting the return value os 
	 * 'isOpen' and 'isAuthenticated' hopes to return conditions that rely on
	 * the function, and stop any telemetry.
	 *
	 * @param clr {@link CallbackInfoReturnable}
	 */
	@Inject(method = {
		"isOpen()Z",
		"isAuthenticated()Z",
	}, at = @At("HEAD"), cancellable = true, remap = false)
	private void nse$overrideConnectionManager(final CallbackInfoReturnable<Boolean> clr)
	{
		clr.setReturnValue(false);
	}

}
