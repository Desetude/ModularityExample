package com.harryfreeborough.modularityexample;

import com.harryfreeborough.modularity.additionalmodules.BukkitGuiceModule;
import com.harryfreeborough.modularity.loader.ModuleLoader;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class ModularityExample extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        this.getLogger().info("Starting Module loading.");

        new ModuleLoader()
                .addInjectorModules(new BukkitGuiceModule(this))
                .load();

        this.getLogger().info("Finished Module loading.");
    }

}
