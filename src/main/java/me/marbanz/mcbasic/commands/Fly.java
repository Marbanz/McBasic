package me.marbanz.mcbasic.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class Fly implements CommandExecutor{

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {

		if (cmd.getName().equalsIgnoreCase("fly")) {
			if (sender instanceof Player) {
				Player p = (Player) sender;
				if (sender.hasPermission("mcbasic.fly")) {
					if (args.length == 0) {

						if (p.getAllowFlight()) {
							p.setAllowFlight(false);
							sender.sendMessage("§aFly disabled");
							System.out.println("[MCBasic] " + p.getPlayer().getName() + " has disabled fly");
						} else {
							p.setAllowFlight(true);
							sender.sendMessage("§aFly enabled");
							System.out.println("[MCBasic] " + p.getPlayer().getName() + " has enabled fly");
						}
						return true;
					} else if (args.length == 1) {
						Player target = Bukkit.getServer().getPlayerExact(args[0]);
						Player player = (Player) sender;
						if (target != null) {
							if (target.getAllowFlight()) {
								target.setAllowFlight(false);
								target.sendMessage("§aFly disabled");
								sender.sendMessage("§aNow §e" + target.getPlayer().getName() + "§a can't fly");
								System.out.println("[MCBasic] " + player.getPlayer().getName()
										+ " has disabled fly for " + target.getPlayer().getName());
							} else {
								target.setAllowFlight(true);
								target.sendMessage("§aFly enabled");
								sender.sendMessage("§aNow §e" + target.getPlayer().getName() + "§a can fly");
								System.out.println("[MCBasic] " + player.getPlayer().getName() + " has enabled fly for "
										+ target.getPlayer().getName());
							}
						} else {
							sender.sendMessage("§cPlayer not found");
						}
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
						if (target.getAllowFlight()) {
							target.setAllowFlight(false);
							target.sendMessage("§aFly disabled");
							sender.sendMessage("Now " + target.getPlayer().getName() + " can't fly");
							System.out
									.println("[MCBasic] Console has disabled fly for " + target.getPlayer().getName());
						} else {
							target.setAllowFlight(true);
							target.sendMessage("§aFly enabled");
							sender.sendMessage("Now " + target.getPlayer().getName() + " can fly");
							System.out.println("[MCBasic] Console has enabled fly for " + target.getPlayer().getName());
						}
					} else {
						sender.sendMessage("Player not found");
					}
					return true;

				} else {
					sender.sendMessage("Use: /fly <player name>");
				}
			}
		}
		return false;
	}
}
