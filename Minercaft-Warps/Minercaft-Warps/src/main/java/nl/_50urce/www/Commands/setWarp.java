package nl._50urce.www.Commands;

import nl._50urce.www.Minercaft_Warps;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class setWarp implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use this command.");
            return true;
        }
        Player player = (Player) sender;
        if (args.length != 1) {
            player.sendMessage("Usage: /setwarp <warpname>");
            return true;
        }
        if (player.hasPermission("Minecraft-Warps.setwarp")) {
            String warpName = args[0];
            Location warpLocation = player.getLocation();
            saveWarpLocation(warpName, warpLocation);
            player.sendMessage("Warp " + warpName + " has been set.");
        } else {
            player.sendMessage("[!] You are not allowed to use this command");
            return true;
        }
        return true;
    }

    private void saveWarpLocation(String warpName, Location warpLocation) {
        FileConfiguration config = Minercaft_Warps.getPlugin(Minercaft_Warps.class).getConfig();
        config.set("warps." + warpName + ".world", warpLocation.getWorld().getName());
        config.set("warps." + warpName + ".x", warpLocation.getX());
        config.set("warps." + warpName + ".y", warpLocation.getY());
        config.set("warps." + warpName + ".z", warpLocation.getZ());
        config.set("warps." + warpName + ".yaw", warpLocation.getYaw());
        config.set("warps." + warpName + ".pitch", warpLocation.getPitch());
        Minercaft_Warps.getPlugin(Minercaft_Warps.class).saveConfig();
    }
}
