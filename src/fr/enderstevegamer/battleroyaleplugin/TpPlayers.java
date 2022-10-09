package fr.enderstevegamer.battleroyaleplugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class TpPlayers extends BukkitRunnable {
    @Override
    public void run() {
        tpPlayers();
    }

    public static void tpPlayers() {
        // TP players
        for (Player player : Bukkit.getOnlinePlayers()){
            int playerX = (int) (Math.random() * (250 - (-250) + 1)) + (-250);
            int playerZ = (int) (Math.random() * (250 - (-250) + 1)) + (-250);

            player.teleport(new Location(Bukkit.getWorld("world"), playerX, Bukkit.getWorld("world").getHighestBlockYAt(playerX, playerZ), playerZ));
            player.setGameMode(GameMode.SURVIVAL);

            // Reset title
            player.sendTitle("", "", 0, 0, 0);
        }

        // Send game started
        for (Player player : Bukkit.getOnlinePlayers()){
            player.sendMessage(ChatColor.GREEN + "The game has started!");
        }

        // Save if player is alive
        Main.getIsAlive().clear();
        for (Player player : Bukkit.getOnlinePlayers()){
            Main.getIsAlive().put(player.getUniqueId(), true);
        }

        // Reset kills
        Main.getKills().clear();
        for (Player player : Bukkit.getOnlinePlayers()){
            Main.getKills().put(player.getUniqueId(), 0);
        }
    }
}
