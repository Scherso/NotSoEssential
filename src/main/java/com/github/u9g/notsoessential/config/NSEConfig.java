package com.github.u9g.notsoessential.config;

import com.github.u9g.notsoessential.NotSoEssential;
import gg.essential.universal.ChatColor;
import gg.essential.vigilance.Vigilant;

import java.io.File;

public class NSEConfig extends Vigilant {

    public NSEConfig() {
        super(new File("./config", NotSoEssential.ID + ".toml"), ChatColor.GREEN + NotSoEssential.NAME);
        initialize();
    }

}
