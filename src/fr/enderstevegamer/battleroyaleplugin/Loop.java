package fr.enderstevegamer.battleroyaleplugin;

import org.bukkit.scheduler.BukkitRunnable;

public class Loop extends BukkitRunnable {
    @Override
    public void run() {
        Main.time++;
    }
}
