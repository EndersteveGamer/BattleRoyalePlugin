package fr.enderstevegamer.battleroyaleplugin;

public class GetAliveNumber {
    public static int getAliveNumber() {
        int aliveNumber = 0;
        for (int i = 0; i < Main.getIsAlive().size(); i++) {
            Object uuid = Main.getIsAlive().keySet().toArray()[i];
            if (Main.getIsAlive().get(uuid)) {
                aliveNumber++;
            }
        }
        return aliveNumber;
    }
}
