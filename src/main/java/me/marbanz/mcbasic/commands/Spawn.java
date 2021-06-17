package me.marbanz.mcbasic.commands;

import me.marbanz.mcbasic.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class Spawn implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (cmd.getName().equalsIgnoreCase("spawn")) {
			if (sender instanceof Player) {
				Player p = (Player) sender;
				if (sender.hasPermission("mcbasic.spawn")) {
					if (Main.spawnConfiguration.getConfigurationSection("spawn") == null) {
						p.sendMessage("§cThe spawn has not yet been set");
						return true;
					}
					World w = Bukkit.getServer().getWorld(Main.spawnConfiguration.getString("spawn.world"));
					double x = Main.spawnConfiguration.getDouble("spawn.x");
					double y = Main.spawnConfiguration.getDouble("spawn.y");
					double z = Main.spawnConfiguration.getDouble("spawn.z");
					if (args.length == 0) {
						p.teleport(new Location(w, x, y, z));
						p.sendMessage("§aTeleported to spawn!");
						System.out.println("[MCBasic] " + p.getPlayer().getName() + " teleported to spawn");
					}
					if (args.length == 1) {
						Player target = Bukkit.getServer().getPlayerExact(args[0]);
						if (target != null) {
							target.teleport(new Location(w, x, y, z));
							target.sendMessage("§aTeleported to spawn!");
							sender.sendMessage("§aTeleported §e" + target.getPlayer().getName() + "§a to spawn!");
							System.out.println("[MCBasic] " + p.getPlayer().getName() + " teleported "
									+ target.getPlayer().getName() + " to spawn");
						} else {
							sender.sendMessage("§cPlayer not found");
							return true;
						}
					}
				} else {
					sender.sendMessage("§cYou don't have permissions to execute this command");
					return true;
				}
			}
			if (sender instanceof ConsoleCommandSender) {
				World w = Bukkit.getServer().getWorld(Main.spawnConfiguration.getString("spawn.world"));
				double x = Main.spawnConfiguration.getDouble("spawn.x");
				double y = Main.spawnConfiguration.getDouble("spawn.y");
				double z = Main.spawnConfiguration.getDouble("spawn.z");
				if (args.length == 1) {
					Player target = Bukkit.getServer().getPlayerExact(args[0]);
					if (target != null) {
						target.teleport(new Location(w, x, y, z));
						target.sendMessage("§aTeleported to spawn!");
						sender.sendMessage("Teleported " + target.getPlayer().getName() + " to spawn!");
						System.out
								.println("[MCBasic] Console teleported " + target.getPlayer().getName() + " to spawn");
					} else {
						sender.sendMessage("Player not found");
						return true;
					}
				} else {
					sender.sendMessage("Use: /spawn <player>");
				}
			}
		}
		return false;
	}

}
