package fr.enderstevegamer.battleroyaleplugin;

import fr.enderstevegamer.battleroyaleplugin.commands.StartGame;
import fr.enderstevegamer.battleroyaleplugin.listeners.OnBreak;
import fr.enderstevegamer.battleroyaleplugin.listeners.OnDeath;
import fr.enderstevegamer.battleroyaleplugin.listeners.OnJoin;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public class Main extends JavaPlugin {

    private static Main instance;
    public static int time = 0;

    public static int generatedChests = 0;

    public static HashMap<UUID, Boolean> isAlive = new HashMap<>();

    public static HashMap<UUID, Integer> kills = new HashMap<>();

    @Override
    public void onEnable() {
        instance = this;
        getLogger().info("BattleRoyalePlugin has been enabled!");
        getCommand("startgame").setExecutor(new StartGame());
        Bukkit.getServer().getPluginManager().registerEvents(new OnJoin(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new OnBreak(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new OnDeath(), this);
        Loop loop = new Loop();
        loop.runTaskTimer(this, 0, 20);
    }

    @Override
    public void onDisable() {
        getLogger().info("BattleRoyalePlugin has been disabled!");
    }

    public static HashMap<UUID, Boolean> getIsAlive() {
        return isAlive;
    }

    public static HashMap<UUID, Integer> getKills() {
        return kills;
    }

    public static Main getInstance() {
        return instance;
    }

    public static void setGeneratedChests(int generatedChests) {
        Main.generatedChests = generatedChests;
    }

    public static int getGeneratedChests() {
        return generatedChests;
    }
}