package nl._50urce.www.Commands;

import nl._50urce.www.Minercaft_Warps;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class Warps implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use this command.");
            return true;
        }

        Player player = (Player) sender;

        if (command.getName().equalsIgnoreCase("warps")) {
            Set<String> warpNames = getWarpNames();
            if (warpNames == null) {
                player.sendMessage("There are no warps available.");
                return true;
            }
            int numWarps = warpNames.size();
            player.sendMessage("Available warps (" + numWarps + "):");
            for (String warpName : warpNames) {
                player.sendMessage("- " + warpName);
            }
            return true;
        }

        return false;
    }

    private Set<String> getWarpNames() {
        FileConfiguration config = Minercaft_Warps.getPlugin(Minercaft_Warps.class).getConfig();
        ConfigurationSection warpSection = config.getConfigurationSection("warps");

        if (warpSection != null) {
            return warpSection.getKeys(false);
        } else {
            return null; // Return null if the "warps" section doesn't exist
        }
    }
}
