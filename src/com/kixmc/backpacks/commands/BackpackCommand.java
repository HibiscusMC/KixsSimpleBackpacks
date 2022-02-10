package com.kixmc.backpacks.commands;

import com.kixmc.backpacks.core.BackpackItem;
import com.kixmc.backpacks.core.SimpleBackpacks;
import me.mattstudios.mf.annotations.*;
import me.mattstudios.mf.base.CommandBase;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Command("backpack")
public class BackpackCommand extends CommandBase {

    public final String usage = ChatColor.RED + "/backpacks <get|reload|give> [player]";

    @Default
    public void onDefault(final Player player) {
        player.sendMessage(usage);
    }

    @SubCommand("get")
    @Permission("backpacks.getcommand")
    public void getCommand(final Player player) {
        player.getInventory().addItem(BackpackItem.makeUnopened());
    }

    @SubCommand("give")
    @Permission("backpacks.givecommand")
    public void giveCommand(CommandSender sender, final Player player) {
        if (player == null) {
            return;
        }
        Bukkit.getPlayer(player.getName()).getInventory().addItem(BackpackItem.makeUnopened());
        sender.sendMessage(ChatColor.GREEN + "Gave a backpack to " + player.getName() + "!");
    }

    @SubCommand("reload")
    @Permission("backpacks.reload")
    public void reloadCommand(CommandSender sender) {
        SimpleBackpacks.get().reloadConfig();
        sender.sendMessage(ChatColor.GREEN + "KixsSimpleBackpacks has been reloaded!");
        sender.sendMessage(ChatColor.GRAY + "Note: updates to the recipe require a restart to take effect");
    }
}