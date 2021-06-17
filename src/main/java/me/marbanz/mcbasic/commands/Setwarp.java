package me.marbanz.mcbasic.commands;

import me.marbanz.mcbasic.utils.Warpmanager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Setwarp implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {

		if (cmd.getName().equalsIgnoreCase("setwarp")) {
			if (sender instanceof Player) {
				Player p = (Player) sender;
				if (sender.hasPermission("mcbasic.setwarp")) {
					if (args.length == 1) {
						if (Warpmanager.exists(args[0])) {
							p.sendMessage("§cThis warp already exist");
						} else {
							Warpmanager.addWarp(args[0], p.getLocation());
							p.sendMessage("§aCreated warp §e" + args[0]);
							System.out.println("[MCBasic] " + p.getPlayer().getName() + " created warp " + args[0]);
						}
					} else {
						sender.sendMessage("§fUse: /setwarp <name>");
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
