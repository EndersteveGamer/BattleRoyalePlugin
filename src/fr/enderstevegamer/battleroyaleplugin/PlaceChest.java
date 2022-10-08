package fr.enderstevegamer.battleroyaleplugin;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.inventory.ItemStack;
public class PlaceChest {
    public static void placeChest() {
        int chestX = (int) (Math.random() * (250 - (-250) + 1)) + (-250);
        int chestZ = (int) (Math.random() * (250 - (-250) + 1)) + (-250);

        int highestBlockY = Bukkit.getWorld("world").getHighestBlockYAt(chestX, chestZ);
        int tempY = highestBlockY;
        tempY--;
        for (int temp = 0; temp <= 10; temp++) {
            if (Bukkit.getWorld("world").getBlockAt(chestX, tempY, chestZ).isPassable()) {
                tempY--;
            }
            else {
                highestBlockY = tempY + 2;
            }
        }

        Block block = new Location(Bukkit.getWorld("world"), chestX, highestBlockY, chestZ).getBlock();
        block.setType(Material.CHEST);
        Chest chest = (Chest) block.getState();

        double random = Math.random();
        boolean hasWeapon;
        if (random > 0.5) {
            hasWeapon = true;
        }
        else {
            hasWeapon = false;
        }
        int weaponType = (int) (Math.random() * 10);

        Material weaponMaterial;
        switch (weaponType){
            case 0 -> weaponMaterial = Material.STONE_SWORD;
            case 2-1 -> weaponMaterial = Material.GOLDEN_SWORD;
            case 3-1 -> weaponMaterial = Material.IRON_SWORD;
            case 4-1 -> weaponMaterial = Material.DIAMOND_SWORD;
            case 5-1 -> weaponMaterial = Material.NETHERITE_SWORD;
            case 6-1 -> weaponMaterial = Material.STONE_AXE;
            case 7-1 -> weaponMaterial = Material.GOLDEN_AXE;
            case 8-1 -> weaponMaterial = Material.IRON_AXE;
            case 9-1 -> weaponMaterial = Material.DIAMOND_AXE;
            default -> weaponMaterial = Material.NETHERITE_AXE;
        }

        ItemStack itemStack = new ItemStack(weaponMaterial);

        if (hasWeapon) {
            chest.getInventory().addItem(itemStack);
        }

        random = Math.random();
        boolean hasHealing;
        if (random > 0.5) {
            hasHealing = true;
        }
        else {
            hasHealing = false;
        }

        int healingType = (int) (Math.random() * 3);
        Material healingMaterial;
        int healNum;

        switch (healingType) {
            case 0:
                healingMaterial = Material.COOKED_BEEF;
                healNum = (int) (Math.random() * 10 + 1);
                break;
            case 1:
                healingMaterial = Material.APPLE;
                healNum = (int) (Math.random() * 20 + 1);
                break;
            default:
                healingMaterial = Material.GOLDEN_APPLE;
                healNum = 1;
                break;
        }

        itemStack = new ItemStack(healingMaterial);
        itemStack.setAmount(healNum);

        if (hasHealing) {
            chest.getInventory().addItem(itemStack);
        }

        int blockType = (int) (Math.random() * 4);
        Material blockMaterial;
        int blockNum;

        switch (blockType) {
            case 0:
                blockMaterial = Material.GLASS;
                blockNum = (int) (Math.random() * 20 + 1);
                break;
            case 1:
                blockMaterial = Material.OAK_PLANKS;
                blockNum = (int) (Math.random() * 15 + 1);
                break;
            case 2:
                blockMaterial = Material.COBBLESTONE;
                blockNum = (int) (Math.random() * 10 + 1);
                break;
            default:
                blockMaterial = Material.OBSIDIAN;
                blockNum = (int) (Math.random() * 2 + 1);
                break;
        }

        itemStack = new ItemStack(blockMaterial);
        itemStack.setAmount(blockNum);

        chest.getInventory().addItem(itemStack);

        chest.getLocation().setWorld(Bukkit.getWorld("world"));
        chest.getLocation().setX(chestX);
        chest.getLocation().setZ(chestZ);
        chest.getLocation().setY(highestBlockY);
        Bukkit.getWorld("world").setBlockData(chest.getLocation(), chest.getBlockData());

        Bukkit.getConsoleSender().sendMessage("Chest: " + chest.getLocation().getX() + " " + chest.getLocation().getY() + " " + chest.getLocation().getZ());
    }
}
