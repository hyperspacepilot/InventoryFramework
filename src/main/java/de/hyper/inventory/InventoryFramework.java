package de.hyper.inventory;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public class InventoryFramework extends JavaPlugin {

    @Getter @Setter
    private static InventoryFramework instance;

    private InventoryManager inventoryManager;

    @Override
    public void onLoad() {
        instance = this;
    }

    @Override
    public void onEnable() {
        this.inventoryManager = createInventoryManager(this);
        this.getCommand("testinventory").setExecutor(new CommandExecutor() {
            @Override
            public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
                if (commandSender instanceof Player player) {
                    if (player.hasPermission("admin.admin.testinventory")) {
                        TestInventory testInventory = new TestInventory(player);
                        createInventoryBuilder(inventoryManager).buildInventory(testInventory);
                    }
                }
                return false;
            }
        });
    }

    @Override
    public void onDisable() {

    }

    public static InventoryManager createInventoryManager(Plugin plugin) {
        return new InventoryManager(plugin);
    }

    public static InventoryBuilder createInventoryBuilder(InventoryManager inventoryManager) {
        return new InventoryBuilder(inventoryManager);
    }

    public static InventoryFramework get() {
        return instance;
    }
}
