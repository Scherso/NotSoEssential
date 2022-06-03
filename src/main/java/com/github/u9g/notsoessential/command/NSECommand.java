package com.github.u9g.notsoessential.command;

import com.github.u9g.notsoessential.NotSoEssential;
import com.google.common.collect.Sets;
import gg.essential.api.commands.Command;
import gg.essential.api.commands.DefaultHandler;
import gg.essential.universal.ChatColor;
import gg.essential.universal.UChat;

import java.util.Set;

public class NSECommand extends Command {

    public NSECommand() {
        super(NotSoEssential.ID, true);
    }

    @DefaultHandler
    public void Handle() {
        UChat.chat(ChatColor.GRAY + "[" + ChatColor.RED + "Not So " + ChatColor.GREEN + "Essential" + ChatColor.GRAY + "] " + ChatColor.YELLOW + "Why hello there.");
    }

    public Set<Alias> getCommandAliases() {
        return Sets.newHashSet(
                new Alias("nse")
        );
    }

}