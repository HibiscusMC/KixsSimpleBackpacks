package com.kixmc.backpacks.commands;

import com.kixmc.backpacks.core.SimpleBackpacks;
import com.kixmc.backpacks.contents.ItemHandler;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;

public class BackpackCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {

            Player p = (Player) sender;

            if (args.length > 0) {
                if (args[0].equalsIgnoreCase("get")) {

                    ItemStack is = new ItemStack(Material.CHEST);
                    ItemHandler.store(is, new ArrayList<ItemStack>());

                    NamespacedKey key = new NamespacedKey(SimpleBackpacks.get(), "kixs-backpacks");
                    ItemMeta itemMeta = is.getItemMeta();
                    itemMeta.getPersistentDataContainer().set(key, PersistentDataType.STRING, "");

                    itemMeta.setDisplayName(ChatColor.YELLOW + "Backpack");

                    ArrayList<String> lore = new ArrayList<>();
                    lore.add(" ");
                    lore.add(ChatColor.AQUA + "Brand new, open me!");
                    lore.add(" ");

                    itemMeta.setLore(lore);

                    is.setItemMeta(itemMeta);

                    p.getInventory().addItem(is);
                    p.updateInventory();

                }
            }

        }
        return false;
    }

}