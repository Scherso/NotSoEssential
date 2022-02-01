package com.example.template.command;

import com.example.template.ForgeTemplate;
import gg.essential.api.EssentialAPI;
import gg.essential.api.commands.Command;
import gg.essential.api.commands.DefaultHandler;

public class TemplateCommand extends Command {
    public TemplateCommand() {
        super(ForgeTemplate.ID, true);
    }

    @DefaultHandler
    public void handle() {
        EssentialAPI.getGuiUtil().openScreen(ForgeTemplate.config.gui());
    }
}