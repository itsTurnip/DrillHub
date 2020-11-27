package com.protonmail.itsTurnip.DrillHub;

import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import net.milkbowl.vault.permission.Permission;

public class DrillHub extends JavaPlugin {
    private Permission perms = null;
    @Override
    public void onEnable() {
        try {
            setupPermissions();
        } catch (AssertionError err) {
            getServer().getLogger().warning("No Vault found, disabling plugin");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        getServer().getPluginManager().registerEvents(new HubListener(this), this);
        getLogger().info("Enabled");
    }
    private void setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        assert rsp != null;
        perms = rsp.getProvider();
    }
    Permission getPerms() {
        return perms;
    }
}
