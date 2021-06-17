package me.marbanz.mcbasic.commands;

import me.marbanz.mcbasic.utils.Warpmanager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class Warplist implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {

		if (cmd.getName().equalsIgnoreCase("warplist")) {
			if (sender instanceof Player) {
				Player p = (Player) sender;
				if (sender.hasPermission("mcbasic.warplist")) {
					Warpmanager.shouldWarpList(p);
				} else {
					sender.sendMessage("§cYou don't have permissions to execute this command");
					return true;
				}
			}
			if (sender instanceof ConsoleCommandSender) {
				Warpmanager.shouldWarpListConsole(sender);
			}
		}
		return false;
	}

}
