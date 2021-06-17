package me.marbanz.mcbasic.commands;

import me.marbanz.mcbasic.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Home implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (cmd.getName().equalsIgnoreCase("home")) {
			if (sender instanceof Player) {
				Player p = (Player) sender;
				if (sender.hasPermission("mcbasic.home")) {
					if (Main.homeConfiguration.getConfigurationSection(p.getPlayer().getName()+".home") == null) {
						p.sendMessage("§cThe home has not yet been set");
						return true;
					}
					World w = Bukkit.getServer().getWorld(Main.homeConfiguration.getString(p.getPlayer().getName()+".home.world"));
					double x = Main.homeConfiguration.getDouble(p.getPlayer().getName()+".home.x");
					double y = Main.homeConfiguration.getDouble(p.getPlayer().getName()+".home.y");
					double z = Main.homeConfiguration.getDouble(p.getPlayer().getName()+".home.z");
					if (args.length == 0) {
						p.teleport(new Location(w, x, y, z));
						p.sendMessage("§aTeleported to home!");
						System.out.println("[MCBasic] " + p.getPlayer().getName() + " teleported to home");
					}
				} else {
					sender.sendMessage("§cYou don't have permissions to execute this command");
					return true;
				}
			}
		
		}
		return false;
	}

}
