package me.Tiernanator.Power.Events;

import java.util.logging.Level;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.Tiernanator.Colours.Colour;
import me.Tiernanator.Config.ConfigAccessor;
import me.Tiernanator.Power.Main;
import me.Tiernanator.Power.Commands.SetPower;
import me.Tiernanator.Utilities.Events.PlayerUUIDLogger;

public class ApplyPowerOnPlayerJoin implements Listener {

	private Main plugin;
		
	int amount;

	// the partial path found in the config
	String header = "Players.";

	public ApplyPowerOnPlayerJoin(Main main) {
		plugin = main;
	}

	/*
	 * This class as the name suggests, applies a previously registered group to the 
	 * player when they log on , if it's their first time, they are by default added to 
	 * group 1
	 */
	@EventHandler
	public void applyPermissionsOnPlayerJoin(PlayerJoinEvent event) {
		
		if(plugin == null) {
			plugin.getLogger().log(Level.WARNING, "plugin cannot be null!");
			return;
		}
		
		
		// the config
		ConfigAccessor powerAccessor = new ConfigAccessor(plugin, "power.yml");
		// get the player
		Player player = (Player) event.getPlayer();
		
		// Utilise a function found in PlayerLogger plugin,
		// i.e if it's the players first time on the server
		if (!PlayerUUIDLogger.ifPlayerHasPlayedBefore(player)) {
			
			ChatColor highlight = Colour.HIGHLIGHT.getColour();
			ChatColor good = Colour.GOOD.getColour();
						
			amount = 1;
			player.sendMessage(good + "As it is your first time on the Server, your power level is at the minimum of " + highlight + Integer.toString(amount));
			
			// otherwise get their group from the config and apply it to them
		} else {
			// get the group
			amount = powerAccessor.getConfig().getInt(header + player.getUniqueId().toString());
		}
		SetPower.setPower(player, amount, plugin);
	}
}
