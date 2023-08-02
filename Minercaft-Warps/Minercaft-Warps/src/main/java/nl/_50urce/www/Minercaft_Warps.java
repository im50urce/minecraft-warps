package nl._50urce.www;

import nl._50urce.www.Commands.Warp;
import nl._50urce.www.Commands.Warps;
import nl._50urce.www.Commands.delWarp;
import nl._50urce.www.Commands.setWarp;
import org.bukkit.plugin.java.JavaPlugin;

public final class Minercaft_Warps extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("Minecraft Warps plugin has been enabled!");
        getCommand("delwarp").setExecutor(new delWarp());
        getCommand("setwarp").setExecutor(new setWarp());
        getCommand("warp").setExecutor(new Warp());
        getCommand("warps").setExecutor(new Warps());
        this.saveDefaultConfig();
    }

    @Override
    public void onDisable() {
        getLogger().info("Minecraft Warps plugin has been disabled!");
    }
}
