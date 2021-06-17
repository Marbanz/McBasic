package me.marbanz.mcbasic.events;

import me.marbanz.mcbasic.Main;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Join implements Listener {
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		if (Main.getInstance().getConfig().getString("settings.motd") == null) {
			Main.getInstance().getConfig().set("settings.motd", true);
			Main.getInstance().saveConfig();
		}
		if (Main.getInstance().getConfig().getString("settings.server_name") == null) {
			Main.getInstance().getConfig().set("settings.server_name", "Test Server");
			Main.getInstance().saveConfig();
		}
		if (Boolean.parseBoolean(Main.getInstance().getConfig().getString("settings.motd"))) {
			e.getPlayer().sendMessage("§aWelcome §e" + e.getPlayer().getName() + "§a to §e"
					+ Main.getInstance().getConfig().getString("settings.server_name") + "§a!");
			if (Bukkit.getOnlinePlayers().size() <= 1) {
				e.getPlayer().sendMessage("§aYou are the only player online");
			} else {
				e.getPlayer().sendMessage("§aThere are §e" + Bukkit.getOnlinePlayers().size() + "§a players online");
			}

		}
	}
}
