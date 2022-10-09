package fr.enderstevegamer.battleroyaleplugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class PlaceChest extends BukkitRunnable {
    @Override
    public void run() {
        placeChest();
    }
    public static void placeChest() {
        // Place chest
        int chestX = (int) (Math.random() * (250 - (-250) + 1)) + (-250);
        int chestZ = (int) (Math.random() * (250 - (-250) + 1)) + (-250);

        int highestBlockY = Bukkit.getWorld("world").getHighestBlockYAt(chestX, chestZ);
        int tempY = highestBlockY;
        tempY--;
        for (int temp = 0; temp <= 10; temp++) {
            if (Bukkit.getWorld("world").getBlockAt(chestX, tempY, chestZ).isPassable()) {
                tempY--;
            } else {
                highestBlockY = tempY + 2;
            }
        }

        Block block = new Location(Bukkit.getWorld("world"), chestX, highestBlockY, chestZ).getBlock();
        block.setType(Material.CHEST);
        Chest chest = (Chest) block.getState();

        // Add weapons
        double random = Math.random();
        boolean hasWeapon;
        hasWeapon = random > 0.5;
        int weaponType = (int) (Math.random() * 10);

        Material weaponMaterial;
        switch (weaponType) {
            case 0 -> weaponMaterial = Material.STONE_SWORD;
            case 1 -> weaponMaterial = Material.GOLDEN_SWORD;
            case 2 -> weaponMaterial = Material.IRON_SWORD;
            case 3 -> weaponMaterial = Material.DIAMOND_SWORD;
            case 4 -> weaponMaterial = Material.NETHERITE_SWORD;
            case 5 -> weaponMaterial = Material.STONE_AXE;
            case 6 -> weaponMaterial = Material.GOLDEN_AXE;
            case 7 -> weaponMaterial = Material.IRON_AXE;
            case 8 -> weaponMaterial = Material.DIAMOND_AXE;
            default -> weaponMaterial = Material.NETHERITE_AXE;
        }

        ItemStack itemStack = new ItemStack(weaponMaterial);

        if (hasWeapon) {
            chest.getInventory().addItem(itemStack);
        }

        // Add healing
        random = Math.random();
        boolean hasHealing;
        hasHealing = random > 0.5;

        int healingType = (int) (Math.random() * 3);
        Material healingMaterial;
        int healNum;

        switch (healingType) {
            case 0 -> {
                healingMaterial = Material.COOKED_BEEF;
                healNum = (int) (Math.random() * 10 + 1);
            }
            case 1 -> {
                healingMaterial = Material.APPLE;
                healNum = (int) (Math.random() * 20 + 1);
            }
            default -> {
                healingMaterial = Material.GOLDEN_APPLE;
                healNum = 1;
            }
        }

        itemStack = new ItemStack(healingMaterial);
        itemStack.setAmount(healNum);

        if (hasHealing) {
            chest.getInventory().addItem(itemStack);
        }

        // Add blocks
        int blockType = (int) (Math.random() * 4);
        Material blockMaterial;
        int blockNum;

        switch (blockType) {
            case 0 -> {
                blockMaterial = Material.GLASS;
                blockNum = (int) (Math.random() * 20 + 1);
            }
            case 1 -> {
                blockMaterial = Material.OAK_PLANKS;
                blockNum = (int) (Math.random() * 15 + 1);
            }
            case 2 -> {
                blockMaterial = Material.COBBLESTONE;
                blockNum = (int) (Math.random() * 10 + 1);
            }
            default -> {
                blockMaterial = Material.OBSIDIAN;
                blockNum = (int) (Math.random() * 2 + 1);
            }
        }

        itemStack = new ItemStack(blockMaterial);
        itemStack.setAmount(blockNum);

        chest.getInventory().addItem(itemStack);

        // Add armor
        random = Math.random();
        boolean hasArmor;
        hasArmor = random > 0.5;

        int armorType = (int) (Math.random() * 10);
        Material armorMaterial = switch (armorType) {
            case 0 -> Material.LEATHER_HELMET;
            case 1 -> Material.LEATHER_CHESTPLATE;
            case 2 -> Material.LEATHER_LEGGINGS;
            case 3 -> Material.LEATHER_BOOTS;
            case 4 -> Material.GOLDEN_HELMET;
            case 5 -> Material.GOLDEN_CHESTPLATE;
            case 6 -> Material.GOLDEN_LEGGINGS;
            case 7 -> Material.GOLDEN_BOOTS;
            case 8 -> Material.IRON_HELMET;
            case 9 -> Material.IRON_CHESTPLATE;
            case 10 -> Material.IRON_LEGGINGS;
            case 11 -> Material.IRON_BOOTS;
            case 12 -> Material.DIAMOND_HELMET;
            case 13 -> Material.DIAMOND_CHESTPLATE;
            case 14 -> Material.DIAMOND_LEGGINGS;
            case 15 -> Material.DIAMOND_BOOTS;
            default -> Material.NETHERITE_HELMET;
        };

        itemStack = new ItemStack(armorMaterial);

        if (hasArmor) {
            chest.getInventory().addItem(itemStack);
        }

        chest.getLocation().setWorld(Bukkit.getWorld("world"));
        chest.getLocation().setX(chestX);
        chest.getLocation().setZ(chestZ);
        chest.getLocation().setY(highestBlockY);
        Bukkit.getWorld("world").setBlockData(chest.getLocation(), chest.getBlockData());

        // Show generated chests
        Main.setGeneratedChests(Main.getGeneratedChests() + 1);
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.sendTitle(ChatColor.GREEN + "Generating chests...", ChatColor.GREEN + "Generated chests: " + Main.getGeneratedChests() + " / 100", 0, 200, 0);
        }
    }
}
