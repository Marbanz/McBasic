package me.marbanz.mcbasic.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import me.marbanz.mcbasic.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Warpmanager {

	public static void addWarp(String warpname, Location location) {
		List<String> warps;
		if (Main.warpConfiguration.getStringList("Warps") != null) {
			warps = Main.warpConfiguration.getStringList("Warps");
		} else {
			warps = new ArrayList<>();
		}
		String finalstring;

		double x = location.getX();
		double y = location.getY();
		double z = location.getZ();
		float yaw = location.getYaw();
		float pitch = location.getPitch();
		String worldname = location.getWorld().getName();
		finalstring = warpname + ":" + x + ":" + y + ":" + z + ":" + yaw + ":" + pitch + ":" + worldname;

		if (!exists(warpname)) {
			warps.add(finalstring);
			Main.warpConfiguration.set("Warps", warps);
			try {
				Main.warpConfiguration.save(Main.warpFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public static void removeWarp(String warpname) {
		List<String> warps = null;
		if (Main.warpConfiguration.getStringList("Warps") != null) {
			warps = Main.warpConfiguration.getStringList("Warps");
		}
		if (warps == null || warps.isEmpty()) {
			return;
		}

		for (String s : warps) {
			String[] args = s.split(":");

			if (args[0].equals(warpname)) {
				warps.remove(s);
				Main.warpConfiguration.set("Warps", warps);
				try {
					Main.warpConfiguration.save(Main.warpFile);
				} catch (IOException e) {
					e.printStackTrace();
				}
				return;
			}

		}

	}

	public static boolean exists(String warpname) {
		List<String> warps = null;
		if (Main.warpConfiguration.getStringList("Warps") != null) {
			warps = Main.warpConfiguration.getStringList("Warps");
		}
		if (warps == null || warps.isEmpty()) {
			return false;
		}

		for (String s : warps) {
			String[] args = s.split(":");

			if (args[0].equals(warpname)) {
				return true;
			}

		}
		return false;
	}

	public static Location getWarp(String warpname) {
		Location finalloc = null;
		List<String> warps = null;
		if (Main.warpConfiguration.getStringList("Warps") != null) {
			warps = Main.warpConfiguration.getStringList("Warps");
		}
		if (warps == null || warps.isEmpty()) {
			return finalloc;
		}

		for (String s : warps) {
			String[] args = s.split(":");

			if (args[0].equals(warpname)) {
				double x = Double.valueOf(args[1]);
				double y = Double.valueOf(args[2]);
				double z = Double.valueOf(args[3]);
				float yaw = Float.valueOf(args[4]);
				float pitch = Float.valueOf(args[5]);
				World world = Bukkit.getWorld(args[6]);

				finalloc = new Location(world, x, y, z, yaw, pitch);

				return finalloc;
			}

		}
		return finalloc;
	}

	public static void shouldWarpList(Player player) {
		List<String> warps = null;
		if (Main.warpConfiguration.getStringList("Warps") != null) {
			warps = Main.warpConfiguration.getStringList("Warps");
		}

		if (warps == null || warps.isEmpty()) {
			player.sendMessage("§cThere are no warps created");
			return;
		}

		player.sendMessage("§aWarp list:");
		for (String warp : warps) {
			String[] args = warp.split(":");
			player.sendMessage("§a- §e" + args[0]);
		}

	}

	public static void shouldWarpListConsole(CommandSender sender) {
		List<String> warps = null;
		if (Main.warpConfiguration.getStringList("Warps") != null) {
			warps = Main.warpConfiguration.getStringList("Warps");
		}

		if (warps == null || warps.isEmpty()) {
			sender.sendMessage("There are no warps created");
			return;
		}

		sender.sendMessage("Warp list:");
		for (String warp : warps) {
			String[] args = warp.split(":");
			sender.sendMessage("- " + args[0]);
		}

	}

}
