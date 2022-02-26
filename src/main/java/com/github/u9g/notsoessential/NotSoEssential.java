package com.github.u9g.notsoessential;

import com.github.u9g.notsoessential.command.NSECommand;
import com.github.u9g.notsoessential.config.NSEConfig;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = NotSoEssential.ID, name = NotSoEssential.NAME, version = NotSoEssential.VER)
public class NotSoEssential {

    public static final String NAME = "@NAME@", VER = "@VER@", ID = "@ID@";
    // Instances
    @Mod.Instance private static NotSoEssential instance;
    public static NSEConfig config;

    public static NotSoEssential getInstance() {
        return instance;
    }

    public NSEConfig getConfig() {
        return config;
    }

    @Mod.EventHandler
    protected void onInitialization(FMLInitializationEvent event) {
        new NSECommand().register();
        this.config = new NSEConfig();
        this.config.preload();
    }

}
