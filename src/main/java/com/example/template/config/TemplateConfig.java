package com.example.template.config;

import com.example.template.ForgeTemplate;
import com.example.template.updater.DownloadGui;
import com.example.template.updater.Updater;
import gg.essential.api.EssentialAPI;
import gg.essential.vigilance.Vigilant;
import gg.essential.vigilance.data.Property;
import gg.essential.vigilance.data.PropertyType;

import java.io.File;

public class TemplateConfig extends Vigilant {
    @Property(
            type = PropertyType.SWITCH,
            name = "Show Update Notification",
            description = "Show a notification when you start Minecraft informing you of new updates.",
            category = "Updater"
    )
    public static boolean showUpdate = true;

    @Property(
            type = PropertyType.BUTTON,
            name = "Update Now",
            description = "Update by clicking the button.",
            category = "Updater"
    )
    public void update() {
        if (Updater.shouldUpdate) EssentialAPI.getGuiUtil()
                .openScreen(new DownloadGui());
        else EssentialAPI.getNotifications()
                .push(ForgeTemplate.NAME, "No update had been detected at startup, and thus the update GUI has not been shown.");
    }

    public TemplateConfig() {
        super(new File(ForgeTemplate.modDir, ForgeTemplate.ID + ".toml"), ForgeTemplate.NAME);
        initialize();
    }
}
