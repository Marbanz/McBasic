package me.marbanz.mcbasic.commands;

import java.io.IOException;

import me.marbanz.mcbasic.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Setspawn implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (cmd.getName().equalsIgnoreCase("setspawn")) {
			if (sender instanceof Player) {
				Player p = (Player) sender;
				if (sender.hasPermission("mcbasic.setspawn")) {
					Main.spawnConfiguration.set("spawn.world", p.getLocation().getWorld().getName());
					Main.spawnConfiguration.set("spawn.x", p.getLocation().getX());
					Main.spawnConfiguration.set("spawn.y", p.getLocation().getY());
					Main.spawnConfiguration.set("spawn.z", p.getLocation().getZ());
					try {
						Main.spawnConfiguration.save(Main.spawnFile);
					} catch (IOException e) {
						e.printStackTrace();
					}
					p.sendMessage("§aSpawn set");
					System.out.println(
							"[MCBasic] " + p.getPlayer().getName() + " set the spawn to X: " + p.getLocation().getX()
									+ " Y: " + p.getLocation().getY() + " Z: " + p.getLocation().getZ());
				} else {
					sender.sendMessage("§cYou don't have permissions to execute this command");
				}
				return true;
			}

		}
		return false;
	}

}
