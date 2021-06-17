package me.marbanz.mcbasic;

import me.marbanz.mcbasic.commands.*;
import me.marbanz.mcbasic.events.Join;
import me.marbanz.mcbasic.events.Join2;
import me.marbanz.mcbasic.events.Quit;
import me.marbanz.mcbasic.utils.Update;
import org.bukkit.configuration.file.FileConfiguration;
import java.io.File;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	public static Main plugin;
	public static File warpFile = new File("./plugins/MCBasic", "warp.yml");
	public static FileConfiguration warpConfiguration = YamlConfiguration.loadConfiguration(warpFile);
	public static File spawnFile = new File("./plugins/MCBasic", "spawn.yml");
	public static FileConfiguration spawnConfiguration = YamlConfiguration.loadConfiguration(spawnFile);
	public static File homeFile = new File("./plugins/MCBasic", "home.yml");
	public static FileConfiguration homeConfiguration = YamlConfiguration.loadConfiguration(homeFile);

	public void onEnable() {
		plugin = this;

		System.out.println("[MCBasic] Plugin is enabled");
		System.out.println("[MCBasic] Plugin made by Marbanz");

		int pluginId = 11266;
		me.marbanz.mcbasic.utils.Metrics metrics = new me.marbanz.mcbasic.utils.Metrics(this, pluginId);

		getServer().getPluginManager().registerEvents(new Join2(), this);
		getServer().getPluginManager().registerEvents(new Join(), this);
		getServer().getPluginManager().registerEvents(new Quit(), this);
		getCommand("fly").setExecutor(new Fly());
		getCommand("gamemode").setExecutor(new Gamemode());
		getCommand("heal").setExecutor(new Heal());
		getCommand("feed").setExecutor(new Feed());
		getCommand("playertp").setExecutor(new Tp());
		getCommand("tphere").setExecutor(new Tphere());
		getCommand("spawn").setExecutor(new Spawn());
		getCommand("setspawn").setExecutor(new Setspawn());
		getCommand("warp").setExecutor(new Warp());
		getCommand("setwarp").setExecutor(new Setwarp());
		getCommand("delwarp").setExecutor(new Delwarp());
		getCommand("warplist").setExecutor(new Warplist());
		getCommand("ping").setExecutor(new Ping());
		getCommand("boom").setExecutor(new Boom());
		getCommand("home").setExecutor(new Home());
		getCommand("sethome").setExecutor(new Sethome());
		getCommand("tpwarp").setExecutor(new Tpwarp());
		saveDefaultConfig();
		(new Update(this, 85523)).getLatestVersion(version -> {
	          if (getDescription().getVersion().equalsIgnoreCase(version)) {
	            System.out.println("[MCBasic] Plugin is up to date");
	          } else {
	            System.out.println("[MCBasic] Plugin need to be updated. Download: https://www.spigotmc.org/resources/mcbasic.85523/ ");
	          } 
	        });
	}

	public void onDisable() {
		System.out.println("[MCBasic] Plugin is disabled");
	}

	public static Main getInstance() {
		return plugin;
	}

}
