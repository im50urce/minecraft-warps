package nl._50urce.www.Commands;

import nl._50urce.www.Minercaft_Warps;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class delWarp implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use this command.");
            return true;
        }
        Player player = (Player) sender;
        if (args.length != 1) {
            player.sendMessage("Usage: /delwarp <warpname>");
            return true;
        }
        if (player.hasPermission("Minecraft-Warps.setwarp")) {
            String warpName = args[0];
            if (deleteWarpLocation(warpName)) {
                player.sendMessage("Warp " + warpName + " has been deleted.");
            } else {
                player.sendMessage("Warp " + warpName + " does not exist.");
            }
        } else {
            player.sendMessage("[!] You are not allowed to use this command");
            return true;
        }
        return true;
    }

    private boolean deleteWarpLocation(String warpName) {
        FileConfiguration config = Minercaft_Warps.getPlugin(Minercaft_Warps.class).getConfig();
        if (config.contains("warps." + warpName)) {
            config.set("warps." + warpName, null);
            Minercaft_Warps.getPlugin(Minercaft_Warps.class).saveConfig();
            return true;
        }
        return false;
    }
}
