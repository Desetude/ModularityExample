package com.harryfreeborough.modularityexample.modules;

import com.google.inject.Inject;
import com.harryfreeborough.modularity.Module;
import com.harryfreeborough.modularityexample.PongService;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

@Module(name = "Ping", desc = "Allows people to ping the server.")
public class PingModule implements CommandExecutor {

    private final PongService pongService;

    @Inject
    public PingModule(PongService pongService, Plugin plugin) {
        this.pongService = pongService;

        plugin.getServer().getPluginCommand("ping").setExecutor(this);

        plugin.getLogger().info("PingModule instantiated.");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String alias, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players can ping the server!");
            return true;
        }

        int newPongs = this.pongService.incrementPongs((Player) sender);
        sender.sendMessage(ChatColor.GREEN + "Pong! You have now been ponged " + newPongs + " times!");

        return true;
    }

}
