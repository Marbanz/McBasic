package me.marbanz.mcbasic.commands;

import me.marbanz.mcbasic.utils.Warpmanager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class Tpwarp implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (cmd.getName().equalsIgnoreCase("tpwarp")) {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                if (sender.hasPermission("mcbasic.tpwarp")) {
                    if (args.length == 2) {
                            Player target = Bukkit.getServer().getPlayerExact(args[1]);
                            if (target != null) {

                                if (Warpmanager.exists(args[0])) {
                                    target.teleport(Warpmanager.getWarp(args[0]));
                                    target.sendMessage("§aTeleported to warp §e" + args[0]);
                                    sender.sendMessage(
                                            "§aTeleported §e" + target.getPlayer().getName() + "§a to warp §e" + args[0]);
                                    System.out.println("[MCBasic] " + p.getPlayer().getName() + " teleported "
                                            + target.getPlayer().getName() + " to warp " + args[0]);
                                } else {
                                    sender.sendMessage("§aThe warp §e" + args[0] + "§a does not exist");
                                }
                            } else
                                sender.sendMessage("§cPlayer not found");

                        return true;
                    } else  {
                        sender.sendMessage("§fUse: /tpwarp <name> <player name>");
                    }
                } else {
                    sender.sendMessage("§cYou don't have permissions to execute this command");
                    return true;
                }

            }
            if (sender instanceof ConsoleCommandSender) {
                if (args.length == 2) {
                    Player target = Bukkit.getServer().getPlayerExact(args[1]);
                    if (target != null) {

                        if (Warpmanager.exists(args[0])) {
                            target.teleport(Warpmanager.getWarp(args[0]));
                            target.sendMessage("§aTeleported to warp §e" + args[0]);
                            sender.sendMessage("Teleported " + target.getPlayer().getName() + " to warp " + args[0]);
                            System.out.println("[MCBasic] Console teleported " + target.getPlayer().getName()
                                    + " to warp " + args[0]);
                        } else {
                            sender.sendMessage("The warp " + args[0] + " does not exist");
                        }

                    } else
                        sender.sendMessage("Player not found");
                    return true;
                } else {
                    sender.sendMessage("Use: /tpwarp <name> <player name>");
                }
            }

        }
        return false;
    }
}
