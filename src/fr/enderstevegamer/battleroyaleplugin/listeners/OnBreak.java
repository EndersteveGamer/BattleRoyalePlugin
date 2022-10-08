package fr.enderstevegamer.battleroyaleplugin.listeners;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class OnBreak implements Listener {
    @EventHandler
    public static void onBreak(org.bukkit.event.block.BlockBreakEvent event) {
        Location blockPos = event.getBlock().getLocation();
        if (blockPos.getY() >= 300 && blockPos.getY() <= 304 && blockPos.getX() >= -3 && blockPos.getX() <= 3 && blockPos.getZ() >= -3 && blockPos.getZ() <= 3) {
            event.setCancelled(true);
        }
    }
}
