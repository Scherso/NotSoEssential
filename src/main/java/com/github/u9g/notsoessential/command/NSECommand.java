package com.github.u9g.notsoessential.command;

import com.github.u9g.notsoessential.NotSoEssential;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class NSECommand extends CommandBase {

    public String getCommandName() {
        return NotSoEssential.ID;
    }

    public String getCommandUsage(ICommandSender sender) {
        return "/" + this.getCommandName();
    }

    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }

    public void processCommand(ICommandSender sender, String[] args) {
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText( EnumChatFormatting.GRAY + "[" + EnumChatFormatting.RED + "Not So " + EnumChatFormatting.GREEN + "Essential" + EnumChatFormatting.GRAY + "] " + EnumChatFormatting.YELLOW + "Why hello there."));
    }

}