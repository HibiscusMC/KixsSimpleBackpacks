package com.kixmc.backpacks.core;

import com.kixmc.backpacks.utils.ChatUtil;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.UUID;

public class BackpackItem {

    public static ItemStack makeUnopened() {

        ItemStack backpack = new ItemStack(Material.valueOf(SimpleBackpacks.get().getConfig().getString("backpack.material")));
        ItemMeta itemMeta = backpack.getItemMeta();

        itemMeta.getPersistentDataContainer().set(new NamespacedKey(SimpleBackpacks.get(), "kixs-backpacks-new"), PersistentDataType.STRING, "");
        itemMeta.setDisplayName(ChatUtil.colorize(SimpleBackpacks.get().getConfig().getString("backpack.name.unopened")));

        ArrayList<String> lore = new ArrayList<>();

        for (String loreLine : SimpleBackpacks.get().getConfig().getStringList("backpack.lore.new")) { lore.add(ChatUtil.colorize(loreLine)); }

        itemMeta.setLore(lore);
        itemMeta.setCustomModelData(SimpleBackpacks.get().getConfig().getInt("backpack.custom-model-data"));
        backpack.setItemMeta(itemMeta);

        return backpack;
    }

    public static ItemStack makeNew() {

        ItemStack backpack = new ItemStack(Material.valueOf(SimpleBackpacks.get().getConfig().getString("backpack.material")));
        ItemMeta itemMeta = backpack.getItemMeta();

        itemMeta.getPersistentDataContainer().set(new NamespacedKey(SimpleBackpacks.get(), "kixs-backpacks"), PersistentDataType.STRING, "");
        itemMeta.getPersistentDataContainer().set(new NamespacedKey(SimpleBackpacks.get(), UUID.randomUUID().toString()), PersistentDataType.STRING, "");

        itemMeta.setDisplayName(ChatUtil.colorize(SimpleBackpacks.get().getConfig().getString("backpack.name.regular")));

        ArrayList<String> lore = new ArrayList<>();

        for (String loreLine : SimpleBackpacks.get().getConfig().getStringList("backpack.lore.empty")) { lore.add(ChatUtil.colorize(loreLine.replace("{SLOTS_IN_USE}", "0")).replace("{MAX_SLOTS}", Integer.toString(SimpleBackpacks.get().getConfig().getInt("backpack.rows") * 9))); }

        itemMeta.setLore(lore);
        backpack.setItemMeta(itemMeta);

        return backpack;
    }

}
