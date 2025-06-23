package com.github.scherso.notsoessential.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mixins;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Pseudo
@SuppressWarnings("UnresolvedMixinReference")
@Mixin(targets = "gg.essential.loader.stage0.EssentialSetupPreLaunch", remap = false)
public class EssentialSetupPreLaunchMixin
{

    /**
     * Essential is not a typical mod; it loads dynamically from
     * {@code .minecraft/essential} via a prelaunch entry point,
     * even when bundled in the mod JAR. During prelaunch, it
     * remains on the classpath since
     * {@code gg.essential.loader.stage2.EssentialLoader#chainLoadMixins()}
     * hasn't been called yet, allowing a configuration to be
     * loaded alongside Essential.
     *
     * @param ci {@link CallbackInfo}
     */
    @Inject(method = "onPreLaunch", at = @At("RETURN"), remap = false)
    private void nse$onPreLaunch(final CallbackInfo ci)
    {
        Mixins.addConfiguration("mixins.notsoessential.json");
    }

}
