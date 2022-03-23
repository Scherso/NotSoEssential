package com.github.u9g.notsoessential;

import com.github.u9g.notsoessential.command.NSECommand;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = NotSoEssential.ID, name = NotSoEssential.NAME, version = NotSoEssential.VER, acceptedMinecraftVersions = "1.8.9, 1.12.2")
public class NotSoEssential {

    public static final String NAME = "@NAME@", VER = "@VER@", ID = "@ID@";

    /*
    @Mod.Instance(ID)
    public static NotSoEssential instance;
    */

    @Mod.EventHandler
    protected void onInitialization(FMLInitializationEvent event) {
        new NSECommand().register();
    }

}
