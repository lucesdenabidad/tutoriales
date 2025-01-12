package datta.dev.pluginTutorial;

import datta.dev.pluginTutorial.listeners.BlockBreakListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new BlockBreakListener(), this); // Registramos el listener
    }

    @Override
    public void onDisable() {

    }
}
