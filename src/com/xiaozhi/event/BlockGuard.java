package com.xiaozhi.event;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class BlockGuard extends JavaPlugin implements Listener {
    private Map<Block, UUID> BlockMap = new HashMap();
    public void onEnable() {
        System.out.println("[BlockGuard]Plugin  package loaded...");
        System.out.println("[BlockGuard]author: xiaozhi_z");
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    public void onDisable() {
        System.out.println("[BlockGuard]Plugin  package unloaded...");
    }

    @EventHandler(
            priority = EventPriority.LOW,
            ignoreCancelled = true
    )
    public void place(BlockPlaceEvent e) {
        this.BlockMap.put(e.getBlock(), e.getPlayer().getUniqueId());
    }

    @EventHandler(
            priority = EventPriority.LOW,
            ignoreCancelled = true
    )
    public void Break(BlockBreakEvent e) {
        if (!((UUID)this.BlockMap.get(e.getBlock())).equals(e.getPlayer().getUniqueId())) {
            e.setCancelled(true);
        }
    }
}
