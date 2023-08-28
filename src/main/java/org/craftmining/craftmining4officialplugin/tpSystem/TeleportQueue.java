package org.craftmining.craftmining4officialplugin.tpSystem;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class TeleportQueue {
    public static final TeleportQueue INSTANCE = new TeleportQueue();
    private TeleportQueue() {
        // Exists only to defeat instantiation.
    }
    public final HashMap<Player, TeleportRequest> TELEPORT_REQUESTS = new HashMap<Player, TeleportRequest>();
    public final HashMap<Player, Location> PREVIOUS_LOCATIONS = new HashMap<Player, Location>();
}
