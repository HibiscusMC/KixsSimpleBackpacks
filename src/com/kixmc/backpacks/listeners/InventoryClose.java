package com.kixmc.backpacks.listeners;

import com.kixmc.backpacks.contents.ItemHandler;
import com.kixmc.backpacks.core.SimpleBackpacks;
import com.kixmc.backpacks.utils.BackpackUtils;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class InventoryClose implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    public void onInventoryClose(InventoryCloseEvent e) {

        if (!(BackpackUtils.isBackpack(e.getPlayer().getInventory().getItemInMainHand()) && e.getView().getTitle().equals(SimpleBackpacks.get().getConfig().getString("backpack.gui-title")))) return;

        Inventory dummyInventory = Bukkit.createInventory(e.getPlayer(), 54, "");
        Arrays.stream(e.getInventory().getContents()).filter(Objects::nonNull).forEach(dummyInventory::addItem);

        ArrayList<ItemStack> tidiedContents = new ArrayList<>();

        Arrays.stream(dummyInventory.getContents()).filter(Objects::nonNull).forEach(tidiedContents::add);

        ItemHandler.store(e.getPlayer().getInventory().getItemInMainHand(), tidiedContents);

    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onDroppingWhilstOpen(PlayerDropItemEvent e) {
        if (BackpackUtils.isBackpack(e.getItemDrop().getItemStack())) e.getPlayer().closeInventory();
    }

}
