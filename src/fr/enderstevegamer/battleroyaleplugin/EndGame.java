package fr.enderstevegamer.battleroyaleplugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.UUID;

public class EndGame {
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
            Player player = Bukkit.getPlayer(Main.getIsAlive().keySet().toArray()[i].toString());
            boolean isAlive = Main.getIsAlive().get(player.getUniqueId());

            if (isAlive) {
                player.sendTitle(ChatColor.RED + "Game Over!", ChatColor.GREEN + "You won!", 10, 70, 20);
            } else {
                player.sendTitle(ChatColor.RED + "Game Over!", winner + " won!", 10, 70, 20);
            }
        }
    }
}