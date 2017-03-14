package me.Tiernanator.Power.Commands;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.Tiernanator.Colours.Colour;
import me.Tiernanator.Factions.Factions.GetFaction;
import me.Tiernanator.Power.Main;

public class GetPower implements CommandExecutor {

	// group arrays
	static List<String> groups;

	// I told you they recurred...(I did in TestPermission anyway)
	private static ChatColor good;
	private static ChatColor bad;
	private static ChatColor highlight;
	private static ChatColor warning;

	@SuppressWarnings("unused")
	private static Main plugin;

	// this has to stay the Main class won't be happy.
	public GetPower(Main main) {
		plugin = main;
	}

	// this Command Sends the player a message with their Group display name.
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {

		// set the constants
		allocateColours();

		// only Players have permissions...
		if (!(sender instanceof Player)) {
			sender.sendMessage(warning + "Only players can use this command.");
			return false;
		}
		// get the player
		Player player = (Player) sender;
		// uses the getGroup() function found below
		int amount = getPower(player);
		if (Integer.toString(amount) == null) {
			player.sendMessage(bad + "You don't have any powers...");
			return false;

		}
		if (amount == 0) {
			player.sendMessage(bad + "You don't have any powers...");
			return false;

		}
		if (amount > 9000) {
			player.sendMessage(ChatColor.GOLD + "It's over 9000!");
			return false;

		}
		player.sendMessage(good + "Your power level is: " + highlight
				+ Integer.toString(amount) + good + ".");
		return true;
	}

	public static int getPower(Player player) {

		String playerFaction = GetFaction.getFaction(player);
		int power;

		String playerSubFaction = GetFaction.getSubFaction(player);
		List<String> allSubFactionsOfPlayerFaction = GetFaction
				.allSubFactionsByFaction(playerFaction);
		power = allSubFactionsOfPlayerFaction.indexOf(playerSubFaction);

		return power + 1;

	}
	public static int getPowerOfOfflinePlayer(OfflinePlayer player) {

		String playerFaction = GetFaction.getOfflineFaction(player);
		
		String playerSubFaction = GetFaction.getOfflineSubFaction(player);
		
		List<String> playerFactionSubFactions = GetFaction.allSubFactionsByFaction(playerFaction);
		
		int subFactionIndex = playerFactionSubFactions.indexOf(playerSubFaction);
		
		
		return subFactionIndex + 1;
	}
	
	// constants initialising
	private static void allocateColours() {

		highlight = Colour.HIGHLIGHT.getColour();
		good = Colour.GOOD.getColour();
		bad = Colour.BAD.getColour();
		warning = Colour.WARNING.getColour();

	}
}
