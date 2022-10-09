package fr.enderstevegamer.battleroyaleplugin.commands;

import fr.enderstevegamer.battleroyaleplugin.Main;
import fr.enderstevegamer.battleroyaleplugin.PlaceChest;
import fr.enderstevegamer.battleroyaleplugin.TpPlayers;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StartGame implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        // Place chests
        for (int chestNum = 0; chestNum < 100; chestNum++) {
            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), PlaceChest::placeChest, chestNum);
        }

        // Set immediate respawn
        Bukkit.getWorld("world").setGameRule(GameRule.DO_IMMEDIATE_RESPAWN, true);

        // Start game
        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), TpPlayers::tpPlayers, 100);

        // Set worldborder
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "worldborder set 500");
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "worldborder damage amount 1");
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "worldborder damage buffer 2");
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "worldborder warning distance 10");
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "worldborder set 16 900");
        return false;
    }
}
