package org.craftmining.craftmining4officialplugin.tpSystem;

import org.bukkit.entity.Player;

public class TeleportRequest {
    public Player requestee;
    public boolean isReversed;

    public TeleportRequest (Player sender, boolean direction) {
        requestee = sender;
        isReversed = direction;
    }
}
