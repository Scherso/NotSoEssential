package com.example.template;

import com.example.template.command.TemplateCommand;
import com.example.template.config.TemplateConfig;
import com.example.template.updater.Updater;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.io.File;

@Mod(modid = ForgeTemplate.ID, name = ForgeTemplate.NAME, version = ForgeTemplate.VER)
public class ForgeTemplate {
    public static final String NAME = "@NAME@", VER = "@VER@", ID = "@ID@";
    public static File jarFile;
    public static File modDir = new File(new File(Minecraft.getMinecraft().mcDataDir, "W-OVERFLOW"), NAME);
    public static TemplateConfig config;

    @Mod.EventHandler
    protected void onFMLPreInitialization(FMLPreInitializationEvent event) {
        if (!modDir.exists()) modDir.mkdirs();
        jarFile = event.getSourceFile();
    }

    @Mod.EventHandler
    protected void onInitialization(FMLInitializationEvent event) {
        new TemplateCommand().register();
        config = new TemplateConfig();
        config.preload();
        Updater.update();
    }
}
