package com.github.scherso.notsoessential.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Attempts to cancel any external connections made via Essential.
 */
@Pseudo
@SuppressWarnings("UnresolvedMixinReference")
@Mixin(targets = "gg.essential.network.connectionmanager.Connection", remap = false)
public class ConnectionMixin
{

    /**
     * Cancelling procedural methods at the head with the hope
     * of preventing any attempted connections or out-bound packets from Essential.
     *
     * @param ci {@link CallbackInfo}
     */
    @Inject(method = {
        "send(Lgg/essential/connectionmanager/common/packet/Packet;Ljava/util/function/Consumer;Ljava/util/concurrent/TimeUnit;Ljava/lang/Long;Ljava/util/UUID;)V",
        "attemptConnect()V",
        "attemptConnect$1385ff()V",
        "doAttemptConnect()V",
        "retryConnectWithBackoff()V"
    }, at = @At("HEAD"), cancellable = true, remap = false)
    private void nse$overrideConnection(final CallbackInfo ci)
    {
        ci.cancel();
    }

}
