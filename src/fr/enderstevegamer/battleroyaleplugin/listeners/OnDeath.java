package fr.enderstevegamer.battleroyaleplugin.listeners;

import fr.enderstevegamer.battleroyaleplugin.EndGame;
import fr.enderstevegamer.battleroyaleplugin.GetAliveNumber;
import fr.enderstevegamer.battleroyaleplugin.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class OnDeath implements Listener {
    @EventHandler
    public static void onDeath(org.bukkit.event.entity.PlayerDeathEvent event) {
        // Death message
        event.setDeathMessage(null);
        String killed = event.getEntity().getName();
        boolean wasKilledByPlayer = event.getEntity().getKiller() != null;
        if (wasKilledByPlayer) {
            String killer = event.getEntity().getKiller().getName();
            Bukkit.broadcastMessage(ChatColor.RED + killer + " killed " + killed + "!");
        }
        else {
            Bukkit.broadcastMessage(ChatColor.RED + killed + " died!");
        }

        // Set killed to dead
        Main.getIsAlive().put(event.getEntity().getUniqueId(), false);

        // Set killed to spectator
        event.getEntity().setGameMode(GameMode.SPECTATOR);

        // Re-TP killed
        Location pos =  event.getEntity().getLocation();
        event.getEntity().spigot().respawn();
        event.getEntity().teleport(pos);

        // Add kill to killer
        if (wasKilledByPlayer) {
            if (Main.getKills().containsKey(event.getEntity().getKiller().getUniqueId())) {
                Main.getKills().put(event.getEntity().getKiller().getUniqueId(), Main.getKills().get(event.getEntity().getKiller().getUniqueId()) + 1);
            } else {
                Main.getKills().put(event.getEntity().getKiller().getUniqueId(), 1);
            }
        }

        // Title killed player
        if (wasKilledByPlayer) {
            String killer = event.getEntity().getKiller().getName();
            event.getEntity().sendTitle("You died!", "You were killed by " + killer + "!", 10, 70, 20);
        }
        else {
            event.getEntity().sendTitle("You died!", "", 10, 70, 20);
        }

        // Check end of game
        if (GetAliveNumber.getAliveNumber() <= 1) {
            EndGame.endGame();
        }
    }
}
