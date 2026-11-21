package com.github.scherso.notsoessential;

import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.entrypoint.EntrypointContainer;
import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;
import org.spongepowered.asm.mixin.Mixins;

public class NotSoEssentialPreLaunch implements PreLaunchEntrypoint
{

    /**
     * Essential is loaded onto the classpath by the constructor of its
     * stage0 {@code preLaunch} entrypoint. Our mixins can only resolve their
     * {@code @Pseudo} targets once that has happened, so the config is added
     * here instead of in {@code fabric.mod.json}.
     * <p>
     * Fabric does not order {@code preLaunch} entrypoints, so Essential's is
     * instantiated first. Fabric caches entrypoint instances, so it is not
     * constructed again when its own turn comes.
     * <p>
     * No mixin may be applied before Essential selects its own configs,
     * otherwise Fabric's MixinExtras initializes first and Essential's
     * bundled copy fails to take over.
     */
    @Override
    public void onPreLaunch()
    {
        FabricLoader.getInstance()
            .getEntrypointContainers("preLaunch", PreLaunchEntrypoint.class)
            .stream()
            .filter(container -> container.getProvider().getMetadata().getId().equals("essential-loader"))
            .forEach(EntrypointContainer::getEntrypoint);

        Mixins.addConfiguration("mixins.notsoessential.json");
    }

}
