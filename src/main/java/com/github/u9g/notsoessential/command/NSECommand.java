package com.github.u9g.notsoessential.command;

import com.github.u9g.notsoessential.NotSoEssential;
import gg.essential.api.EssentialAPI;
import gg.essential.api.commands.Command;
import gg.essential.api.commands.DefaultHandler;

public class NSECommand extends Command {

    public NSECommand() {
        super(NotSoEssential.ID, true);
    }

    @DefaultHandler
    public void handle() {
        EssentialAPI.getGuiUtil().openScreen(NotSoEssential.config.gui());
    }

}