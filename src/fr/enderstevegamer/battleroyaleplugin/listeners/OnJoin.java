package fr.enderstevegamer.battleroyaleplugin.listeners;

import fr.enderstevegamer.battleroyaleplugin.BuildLobby;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class OnJoin implements Listener {
    @EventHandler
    public static void onJoin(org.bukkit.event.player.PlayerJoinEvent event) {
        BuildLobby.buildLobby();
        event.getPlayer().getInventory().clear();
        event.getPlayer().setGameMode(GameMode.ADVENTURE);
        event.getPlayer().teleport(new Location(Bukkit.getWorld("world"), 0, 302, 0));
    }
}
