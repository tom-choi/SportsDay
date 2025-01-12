package org.macausmp.sportsday.command;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.macausmp.sportsday.SportsDay;
import org.macausmp.sportsday.gui.CompetitionGUI;

import java.util.ArrayList;
import java.util.List;

public class CompetitionGUICommand extends PluginCommand {
    @Override
    public void onCommand(CommandSender sender, String[] args) {
        if (sender instanceof Player p) {
            if (args.length != 0 && args[0].equals("book")) {
                p.getInventory().addItem(book());
                return;
            }
            CompetitionGUI.MENU_GUI.openTo(p);
        } else {
            sender.sendMessage(Component.translatable("permissions.requires.player").color(NamedTextColor.RED));
        }
    }

    public static @NotNull ItemStack book() {
        ItemStack book = new ItemStack(Material.BOOK);
        book.editMeta(meta -> {
            meta.displayName(Component.text("運動會修煉手冊").decoration(TextDecoration.ITALIC, false).color(NamedTextColor.GOLD));
            List<Component> lore = new ArrayList<>();
            lore.add(Component.text("用於在運動會中呼風喚雨、隻手遮天").decoration(TextDecoration.ITALIC, false).color(NamedTextColor.GRAY));
            meta.lore(lore);
            meta.getPersistentDataContainer().set(SportsDay.ITEM_ID, PersistentDataType.STRING, "competition_book");
        });
        return book;
    }

    @Override
    public String name() {
        return "competitiongui";
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        List<String> list = new ArrayList<>();
        list.add("book");
        return list;
    }
}
