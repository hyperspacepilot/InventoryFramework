package de.hyper.inventory;

import de.hyper.inventory.managers.InventoryManager_1_12;
import de.hyper.inventory.managers.InventoryManager_1_18;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

/**
 * @author hyperspace_pilot
 */
@Getter
public class InventoryFramework {

    @Getter @Setter
    private static InventoryFramework instance;
    private ServerVersion serverVersion;

    public void setUp() {
        instance = this;
        String version = Bukkit.getVersion();
        if (version.contains("1.19")) {
            serverVersion = ServerVersion.V1_19;
        } else if (version.contains("1.18")) {
            serverVersion = ServerVersion.V1_18;
        } else if (version.contains("1.12")) {
            serverVersion = ServerVersion.V1_12;
        } else if (version.contains("1.8")) {
            serverVersion = ServerVersion.V1_8;
        }
    }

    public static InventoryManager createInventoryManager(Plugin plugin) {
        if (InventoryFramework.get().getServerVersion().equals(ServerVersion.V1_12)) return new InventoryManager_1_12(plugin);
        return new InventoryManager_1_18(plugin);
    }

    public static InventoryBuilder createInventoryBuilder(InventoryManager inventoryManager) {
        return new InventoryBuilder(inventoryManager);
    }

    public static InventoryFramework get() {
        return instance;
    }
}
