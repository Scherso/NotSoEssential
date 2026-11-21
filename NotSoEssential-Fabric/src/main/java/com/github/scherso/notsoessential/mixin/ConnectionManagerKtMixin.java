package com.github.scherso.notsoessential.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Pseudo
@SuppressWarnings("UnresolvedMixinReference")
@Mixin(targets = "gg.essential.network.connectionmanager.ConnectionManagerKt", remap = false)
public class ConnectionManagerKtMixin
{

    /**
     * Prevents Essential's connection loop from ever starting. The loop
     * waits on Essential's stored ToS state, while {@link OnboardingDataMixin}
     * reports the ToS as not accepted. If the user accepted the ToS before
     * installing this mod, the wait returns immediately and the loop spins
     * on the main thread, hanging the game.
     *
     * @param ci {@link CallbackInfo}
     */
    @Inject(method = "start()V", at = @At("HEAD"), cancellable = true, remap = false)
    private void nse$start(final CallbackInfo ci)
    {
        ci.cancel();
    }

}
