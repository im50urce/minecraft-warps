package nl._50urce.www.Commands;

import nl._50urce.www.Minercaft_Warps;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Warp implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use this command.");
            return true;
        }
        Player player = (Player) sender;
        if (args.length != 1) {
            player.sendMessage("Usage: /warp <warpname>");
            return true;
        }

        String warpName = args[0];
        Location warpLocation = getWarpLocation(warpName);
        if (warpLocation != null) {
            // Bypass anti-cheat checks by using teleport() directly
            player.teleport(warpLocation);
            player.sendMessage("You have been teleported to warp " + warpName + ".");
        } else {
            player.sendMessage("Warp " + warpName + " does not exist.");
        }
        return true;
    }

    private Location getWarpLocation(String warpName) {
        FileConfiguration config = Minercaft_Warps.getPlugin(Minercaft_Warps.class).getConfig();
        if (config.contains("warps." + warpName)) {
            String worldName = config.getString("warps." + warpName + ".world");
            double x = config.getDouble("warps." + warpName + ".x");
            double y = config.getDouble("warps." + warpName + ".y");
            double z = config.getDouble("warps." + warpName + ".z");
            float yaw = (float) config.getDouble("warps." + warpName + ".yaw");
            float pitch = (float) config.getDouble("warps." + warpName + ".pitch");
            return new Location(Bukkit.getWorld(worldName), x, y, z, yaw, pitch);
        }
        return null;
    }
}
