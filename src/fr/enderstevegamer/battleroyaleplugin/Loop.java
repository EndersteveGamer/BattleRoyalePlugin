package fr.enderstevegamer.battleroyaleplugin;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

public class Loop extends BukkitRunnable {
    @Override
    public void run() {
        Main.time++;
        for (int i = 0; i < Main.getIsAlive().size(); i++){
            UUID uuid = (UUID) Main.getIsAlive().keySet().toArray()[i];
            if (!Main.getIsAlive().get(uuid)){
                Player player = Bukkit.getPlayer(uuid);
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.RED + "You are dead!"));
            }
        }
    }
}
