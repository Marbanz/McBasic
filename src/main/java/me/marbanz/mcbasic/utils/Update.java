package me.marbanz.mcbasic.utils;

import me.marbanz.mcbasic.Main;
import org.bukkit.Bukkit;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;
import java.util.function.Consumer;

public class Update {

    private Main plugin;
    private int resourceId;

    public Update(Main plugin, int resourceId) {
        this.plugin  = plugin;
        this.resourceId = resourceId;
    }

    public void getLatestVersion(Consumer<String> consumer) {
    	if (Boolean.parseBoolean(Main.getInstance().getConfig().getString("settings.update_checker"))) {
    	Bukkit.getScheduler().runTaskAsynchronously(this.plugin, () -> {
            try (InputStream inputStream = new URL("https://api.spigotmc.org/legacy/update.php?resource=" + this.resourceId).openStream();
            Scanner scanner = new Scanner(inputStream)) {
            if (scanner.hasNext()) {
                consumer.accept(scanner.next());
            }
        } catch (IOException exception) {
                plugin.getLogger().info("Update checker is broken, can't find an update!" + exception.getMessage());
            }
        });
    }
    }
}