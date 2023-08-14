package org.craftmining.craftmining4officialplugin.msgSystem;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PrivateMessageSentEvent extends Event {
    private static final HandlerList HANDLERS = new HandlerList();

    CommandSender sender;
    Player target;
    StringBuilder message;

    public PrivateMessageSentEvent(CommandSender sender, Player target, StringBuilder message) {
        this.sender = sender;
        this.target = target;
        this.message = message;
    }

    public CommandSender getSender() {
        return sender;
    }

    public Player getTarget() {
        return target;
    }

    public StringBuilder getMessage() {
        return message;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }
}
