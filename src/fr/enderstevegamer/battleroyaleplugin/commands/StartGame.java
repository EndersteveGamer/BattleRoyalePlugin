package fr.enderstevegamer.battleroyaleplugin.commands;

import fr.enderstevegamer.battleroyaleplugin.Main;
import fr.enderstevegamer.battleroyaleplugin.PlaceChest;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StartGame implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "worldborder set 500");
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "worldborder damage amount 1");
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "worldborder damage buffer 2");
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "worldborder warning distance 10");
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "worldborder set 16 900");

        // Place chests
        for (int chestNum = 0; chestNum < 100; chestNum++) {
            PlaceChest.placeChest();
        }

        // TP players
        for (Player player : Bukkit.getOnlinePlayers()){
            int playerX = (int) (Math.random() * (250 - (-250) + 1)) + (-250);
            int playerZ = (int) (Math.random() * (250 - (-250) + 1)) + (-250);

            player.teleport(new Location(Bukkit.getWorld("world"), playerX, Bukkit.getWorld("world").getHighestBlockYAt(playerX, playerZ), playerZ));
            player.setGameMode(GameMode.SURVIVAL);
        }

        // Send game started
        commandSender.sendMessage(ChatColor.GREEN + "Game started!");

        // Save if player is alive
        Main.getIsAlive().clear();
        for (Player player : Bukkit.getOnlinePlayers()){
            Main.getIsAlive().put(player.getUniqueId(), true);
        }
        return false;
    }
}
