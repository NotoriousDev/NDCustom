package com.notoriousdev.custom;

import org.bukkit.command.CommandSender;

public enum Permissions
{

    COMMAND_NDCUSTOM,
    CHAT,
    CHAT_BYPASS,
    BOOK,
    COMMAND_OP,
    COMMAND_DEOP,
    COMMAND_RELOAD,
    COMMAND_STOP;

    public boolean isAuthorised(CommandSender sender)
    {
        return sender.hasPermission(this.toString());
    }

    @Override
    public String toString()
    {
        return "ndcustom." + this.name().toLowerCase().replace("_", ".");
    }
}
