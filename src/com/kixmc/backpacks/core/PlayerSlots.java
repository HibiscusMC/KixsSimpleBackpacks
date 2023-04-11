package com.kixmc.backpacks.core;

import java.util.HashMap;
import java.util.UUID;

public class PlayerSlots {

    private static final HashMap<UUID, Integer> playerSlots = new HashMap<>();


    public static void addPlayerSlot(UUID uuid, int playerSlot) {
        if (playerSlots.containsKey(uuid)) {
            playerSlots.replace(uuid, playerSlot);
        } else {
            playerSlots.put(uuid, playerSlot);
        }
    }

    public static void removePlayerSlot(UUID uuid) {
        playerSlots.remove(uuid);
    }

    public static int getSlot(UUID uuid) {
        return playerSlots.get(uuid);
    }

    public static boolean hasSlot(UUID uuid) {
        return playerSlots.containsKey(uuid);
    }
}
