package fr.enderstevegamer.battleroyaleplugin.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

public class OnDeath implements Listener {
    public static void onDeath(org.bukkit.event.entity.PlayerDeathEvent event) {
        event.setDeathMessage(null);
        String killed = event.getEntity().getName();
        String killer = event.getEntity().getKiller().getName();
        Bukkit.broadcastMessage(killer + " killed " + killed + "!");
    }
}
