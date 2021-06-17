package me.marbanz.mcbasic.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class Ping implements CommandExecutor{
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {

		if (cmd.getName().equalsIgnoreCase("ping")) {
			if (sender instanceof Player) {
				Player p = (Player) sender;
				if (sender.hasPermission("mcbasic.ping")) {
					if (args.length == 0) {
						p.sendMessage("§aYou have §e" + p.getPing() + "§a ms");
					} else {
						Player target = Bukkit.getServer().getPlayerExact(args[0]);
						if (target != null) {
							sender.sendMessage(
									"§e" + target.getPlayer().getName() + "§a has §e" + target.getPing() + "§a ms");
							return true;
						} else
							sender.sendMessage("§cPlayer not found");
						return true;
					}
				} else {
					sender.sendMessage("§cYou don't have permissions to execute this command");
					return true;
				}
			}
			if (sender instanceof ConsoleCommandSender) {
				if (args.length == 1) {
					Player target = Bukkit.getServer().getPlayerExact(args[0]);
					if (target != null) {
						sender.sendMessage(target.getPlayer().getName() + " has " + target.getPing() + " ms");
						return true;
					} else
						sender.sendMessage("Player not found");
					return true;
				} else {
					sender.sendMessage("Use: /ping <player>");
				}
			}
		}

		return false;

	}

}