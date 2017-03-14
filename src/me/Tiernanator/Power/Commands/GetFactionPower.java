package me.Tiernanator.Power.Commands;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.Tiernanator.Colours.Colour;
import me.Tiernanator.Factions.Factions.FactionMembers;
import me.Tiernanator.Factions.Factions.GetFaction;
import me.Tiernanator.Power.Main;
import me.Tiernanator.Utilities.Events.PlayerUUIDNameLogger;
import me.Tiernanator.Utilities.Players.GetPlayer;
public class GetFactionPower implements CommandExecutor {

	private static Main plugin;

	// this has to stay the Main class won't be happy.
	public GetFactionPower(Main main) {
		plugin = main;
	}

	// this Command Sends the player a message with their Group display name.
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		
		ChatColor warning = Colour.WARNING.getColour();
		ChatColor good = Colour.GOOD.getColour();
		ChatColor informative = Colour.INFORMATIVE.getColour();
		ChatColor highlight = Colour.HIGHLIGHT.getColour();

		
		if(!(sender instanceof Player) ) {
				sender.sendMessage(warning + "You can't  use this.");
				return false;
		}
		Player player = (Player) sender;
		String faction = GetFaction.getFaction(player);
		
		List<String> allFactions = GetFaction.allFactions();
		
		String rogueFaction = allFactions.get(allFactions.size() - 1);
		if(faction.equalsIgnoreCase(rogueFaction)) {
			int power = GetPower.getPower(player);
			sender.sendMessage(good + "You as a " + informative + faction + good + " have a power of " + highlight + Integer.toString(power));
			return true;
		}
		String guestFaction = allFactions.get(0);
		if(faction.equalsIgnoreCase(guestFaction)) {
			sender.sendMessage(good + "You as a member of " + informative + faction + good + " are powerless...");
			return true;
		}
		
		int power = getPowerOfFaction(faction);
		sender.sendMessage(good + "The Faction " + informative + faction + good + " has a power of " + highlight + Integer.toString(power));
		return true;
	}
	
	public static Integer getPowerOfFaction(String faction) {
					
			int totalPower = 0;
			List<String> allMembers = FactionMembers.getFactionMembers(faction);
			for (String uuid : allMembers) {
				
				String name = PlayerUUIDNameLogger.getPlayerNameByUUID(uuid);
				OfflinePlayer player = GetPlayer
						.functionGetOfflinePlayer(name, plugin);
				if(player == null) {
					break;
				}
				
				int power = GetPower.getPowerOfOfflinePlayer(player);
				totalPower += power;
			}
			
			return totalPower;
			
		}
}