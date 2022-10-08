package fr.enderstevegamer.battleroyaleplugin;

import org.bukkit.Bukkit;
import org.bukkit.Material;

public class BuildLobby {
    public static void buildLobby() {
        for (int x = -3; x <= 3; x++) {
            for (int z = -3; z <= 3; z++) {
                for (int y = 300; y <= 304; y++) {
                    if (y == 300) {
                        Bukkit.getWorld("world").getBlockAt(x, y, z).setType(Material.POLISHED_ANDESITE);
                    }
                    if (y > 300 && (x == -3 || x == 3 || z == -3 || z == 3)) {
                        Bukkit.getWorld("world").getBlockAt(x, y, z).setType(Material.QUARTZ_BLOCK);
                    }
                    if (y == 304 && !(x == -3 || x == 3 || z == -3 || z == 3)) {
                        Bukkit.getWorld("world").getBlockAt(x, y, z).setType(Material.BARRIER);
                    }
                }
            }
        }
    }
}
