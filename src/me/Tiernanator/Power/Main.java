package me.Tiernanator.Power;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import me.Tiernanator.Power.Commands.GetFactionPower;
import me.Tiernanator.Power.Commands.GetPower;
import me.Tiernanator.Power.Events.ApplyPowerOnPlayerJoin;
import me.Tiernanator.Power.Events.RegisterPower;

public class Main extends JavaPlugin {
		
	@Override
	public void onEnable() {

		registerCommands();
		registerEvents();
	}

	@Override
	public void onDisable() {
		
	}

	public void registerCommands() {
		getCommand("getPower").setExecutor(new GetPower(this));
		getCommand("getFactionPower").setExecutor(new GetFactionPower(this));
	}

	public void registerEvents() {
		// the two events handle the player leaving & joining
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new RegisterPower(this), this);
		pm.registerEvents(new ApplyPowerOnPlayerJoin(this), this);
		
	}
	
}
