package de.hyper.inventory;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author hyperspace_pilot
 */
@Getter
public class InventoryFramework extends JavaPlugin {

    @Getter @Setter
    private static InventoryFramework instance;

    private InventoryManager inventoryManager;
    private ServerVersion serverVersion;

    @Override
    public void onLoad() {
        instance = this;
        if (Bukkit.getVersion().contains("1.12")) {
            serverVersion = ServerVersion.V1_12;
        } else {
            serverVersion = ServerVersion.V1_18;
        }
    }

    @Override
    public void onEnable() {
        this.inventoryManager = createInventoryManager(this);
        this.getCommand("testinventory").setExecutor((commandSender, command, s, strings) -> {
            if (commandSender instanceof Player player) {
                if (player.hasPermission("inventoryframework.testinventory")) {
                    TestInventory testInventory = new TestInventory(player);
                    createInventoryBuilder(inventoryManager).buildInventory(testInventory);
                }
            }
            return false;
        });
    }

    @Override
    public void onDisable() {

    }

    public static InventoryManager createInventoryManager(Plugin plugin) {
        //return new InventoryManager(plugin);
        return null;
    }

    public static InventoryBuilder createInventoryBuilder(InventoryManager inventoryManager) {
        return new InventoryBuilder(inventoryManager);
    }

    public static InventoryFramework get() {
        return instance;
    }
}
