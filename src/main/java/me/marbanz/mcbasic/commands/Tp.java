package me.marbanz.mcbasic.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Tp implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {

		if (cmd.getName().equalsIgnoreCase("playertp")) {
			if (sender instanceof Player) {
				Player p = (Player) sender;
				if (sender.hasPermission("mcbasic.playertp")) {
					if (args.length == 1) {
						Player target = Bukkit.getServer().getPlayerExact(args[0]);
						Player player = (Player) sender;
						if (target != null) {
							p.teleport(target);
							p.sendMessage("§aYou teleported to §e" + target.getPlayer().getName() + "");
							System.out.println("[MCBasic] " + player.getPlayer().getName() + " teleported to "
									+ target.getPlayer().getName());
							return true;
						} else
							sender.sendMessage("§cPlayer not found");
					} else {
						sender.sendMessage("§fUse: /playertp <player>");
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
