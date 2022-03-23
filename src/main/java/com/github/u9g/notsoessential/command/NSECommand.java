package com.github.u9g.notsoessential.command;

import com.github.u9g.notsoessential.NotSoEssential;
import gg.essential.api.commands.Command;
import gg.essential.api.commands.DefaultHandler;
import gg.essential.universal.ChatColor;
import gg.essential.universal.UChat;
import gg.essential.universal.wrappers.message.UTextComponent;

public class NSECommand extends Command {

    public NSECommand() {
        super(NotSoEssential.ID, true);
    }

    @DefaultHandler
    public void Handle() {
        UChat.chat(new UTextComponent(ChatColor.GRAY + "[" + ChatColor.RED + "Not So " + ChatColor.GREEN + "Essential" + ChatColor.GRAY + "] " + ChatColor.YELLOW + "Why hello there."));
    }

}