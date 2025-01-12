package org.macausmp.sportsday.gui;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

public abstract class AbstractGUI implements IPluginGUI {
    private final Inventory inventory;

    public AbstractGUI(int size, Component title) {
        this.inventory = Bukkit.createInventory(null, size, title);
        update();
    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }

    @Override
    public void openTo(@NotNull Player player) {
        player.openInventory(this.inventory);
        CompetitionGUI.GUI_MAP.put(player, this);
    }
}
