package fr.enderstevegamer.battleroyaleplugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameRule;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

public class EndGame extends BukkitRunnable {
    public static void endGame() {
        // Get winner
        String winner = "Nobody";
        for (int i = 0; i < Main.getIsAlive().size(); i++) {
            UUID uuid = (UUID) Main.getIsAlive().keySet().toArray()[i];
            if (Main.getIsAlive().get(uuid)) {
                winner = Bukkit.getPlayer(uuid).getName();
            }
        }

        // Send titles
        for (int i = 0; i < Main.getIsAlive().size(); i++) {
            Player player = Bukkit.getPlayer((UUID) Main.getIsAlive().keySet().toArray()[i]);
            boolean isAlive = Main.getIsAlive().get(player.getUniqueId());

            if (isAlive) {
                player.sendTitle(ChatColor.RED + "Game Over!", ChatColor.GREEN + "You won!", 10, 70, 20);
            } else {
                player.sendTitle(ChatColor.RED + "Game Over!", winner + " won!", 10, 70, 20);
            }
        }

        // Disable immediate respawn
        Bukkit.getWorld("world").setGameRule(GameRule.DO_IMMEDIATE_RESPAWN, false);
    }

    @Override
    public void run() {
        endGame();
    }
}
