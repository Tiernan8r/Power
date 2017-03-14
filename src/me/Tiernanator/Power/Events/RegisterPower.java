package me.Tiernanator.Power.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import me.Tiernanator.Config.ConfigAccessor;
import me.Tiernanator.Power.Main;
import me.Tiernanator.Power.Commands.GetPower;

public class RegisterPower implements Listener {

	private static Main plugin;
	// the partial path from within the config
	static String header = "Players.";
	
	public RegisterPower(Main main) {
		plugin = main;
	}
	

	/*
	 * This class is more complex, it records a players group when they log out, 
	 * in the config, for future reference
	 */
	@EventHandler
	public void registerPowerOnPlayerLeave(PlayerQuitEvent event) {
		// Initialise the group names array

		ConfigAccessor powerAccessor = new ConfigAccessor(plugin, "power.yml");		
		// get the player
		Player player = (Player) event.getPlayer();
		// generate the path to the value for the config, the path is Permission.(player UUID)
		// I used UUID because the player could change their name, and still be able to play on the server
		String path = header + player.getUniqueId().toString();
		// get the group from the config
		int playersPower = powerAccessor.getConfig().getInt(header + player.getUniqueId().toString());

		// if the player has never left before, i.e it's their first time logging out after their first play,
		// or the players data was wiped somehow
		if(Integer.toString(playersPower).isEmpty()) {
			// get their group & set the value
			powerAccessor.getConfig().set(path, GetPower.getPower(player));
			// set the value using the path generated earlier
		} else if(!(Integer.toString(playersPower).equalsIgnoreCase(Integer.toString(GetPower.getPower(player))))) {
			powerAccessor.getConfig().set(path, GetPower.getPower(player));
		}
		// always save the config after you modify it
		powerAccessor.saveConfig();
	}
	
	public static void registerPowerOfPlayer(Player player) {

		ConfigAccessor powerAccessor = new ConfigAccessor(plugin, "power.yml");		
		// generate the path to the value for the config, the path is Permission.(player UUID)
		// I used UUID because the player could change their name, and still be able to play on the server
		String path = header + player.getUniqueId().toString();
		// get the group from the config
		int playersPower = powerAccessor.getConfig().getInt(header + player.getUniqueId().toString());

		// if the player has never left before, i.e it's their first time logging out after their first play,
		// or the players data was wiped somehow
		if(Integer.toString(playersPower).isEmpty()) {
			// get their group & set the value
			powerAccessor.getConfig().set(path, GetPower.getPower(player));
			// set the value using the path generated earlier
		} else if(!(Integer.toString(playersPower).equalsIgnoreCase(Integer.toString(GetPower.getPower(player))))) {
			powerAccessor.getConfig().set(path, GetPower.getPower(player));
		}
		// always save the config after you modify it
		powerAccessor.saveConfig();
	}
}