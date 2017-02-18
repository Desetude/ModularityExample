package com.harryfreeborough.modularityexample.modules;

import com.google.inject.Inject;
import com.harryfreeborough.modularity.Module;
import com.harryfreeborough.modularityexample.PongService;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.Plugin;

@Module(name = "Chat", desc = "Manages chat.")
public class ChatModule implements Listener {

    private final PongService pongService;

    @Inject
    public ChatModule(PongService pongService, Plugin plugin) {
        this.pongService = pongService;

        plugin.getLogger().info("ChatModule instantiated.");
    }

    @EventHandler(ignoreCancelled = true)
    public void onAsyncChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        if (this.pongService.getPongs(player) > 0) {
            player.sendMessage(ChatColor.RED + "You can not chat if you have been ponged!");
            event.setCancelled(true);
            return;
        }

        event.setFormat("%s: %s");
    }

}
