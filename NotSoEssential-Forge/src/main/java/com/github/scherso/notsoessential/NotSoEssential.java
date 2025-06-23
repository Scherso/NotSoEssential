package com.github.scherso.notsoessential;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ModMetadata;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = NotSoEssential.ID, name = NotSoEssential.NAME, version = NotSoEssential.VER)
public class NotSoEssential
{

    public static final String NAME = "@NAME@", VER = "@VER@", ID = "@ID@";

    @Mod.EventHandler
    public void preInitializationEvent(FMLPreInitializationEvent event)
    {
        ModMetadata data = event.getModMetadata();
        data.name = "§cNot §aSo §9Essential";
        data.description = "Keeps only what's §9Essential§r.";
    }

}
