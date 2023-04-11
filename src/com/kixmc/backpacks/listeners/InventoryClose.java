package com.kixmc.backpacks.listeners;

import com.kixmc.backpacks.contents.ItemHandler;
import com.kixmc.backpacks.core.PlayerSlots;
import com.kixmc.backpacks.core.SimpleBackpacks;
import com.kixmc.backpacks.utils.BackpackUtils;
import io.papermc.paper.event.player.PlayerInventorySlotChangeEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerChangedMainHandEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class InventoryClose implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    public void onInventoryClose(InventoryCloseEvent e) {
        if (!PlayerSlots.hasSlot(e.getPlayer().getUniqueId())) {
            return;
        }

        if (!(BackpackUtils.isBackpack(e.getPlayer().getInventory().getItem(PlayerSlots.getSlot(e.getPlayer().getUniqueId()))) && e.getView().getTitle().equals(SimpleBackpacks.get().getConfig().getString("backpack.gui-title"))))
            return;

        Inventory dummyInventory = Bukkit.createInventory(e.getPlayer(), 54, "");
        Arrays.stream(e.getInventory().getContents()).filter(Objects::nonNull).forEach(dummyInventory::addItem);

        ArrayList<ItemStack> tidiedContents = new ArrayList<>();

        Arrays.stream(dummyInventory.getContents()).filter(Objects::nonNull).forEach(tidiedContents::add);

        ItemHandler.store(e.getPlayer().getInventory().getItem(PlayerSlots.getSlot(e.getPlayer().getUniqueId())), tidiedContents);
        PlayerSlots.removePlayerSlot(e.getPlayer().getUniqueId());

    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onDroppingWhilstOpen(PlayerDropItemEvent e) {
        if (BackpackUtils.isBackpack(e.getItemDrop().getItemStack())) e.getPlayer().closeInventory();
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onSwitchToOffhand(PlayerSwapHandItemsEvent e) {
        if (BackpackUtils.isBackpack(e.getOffHandItem()) || BackpackUtils.isBackpack(e.getMainHandItem()))
            e.getPlayer().closeInventory();
    }
}
