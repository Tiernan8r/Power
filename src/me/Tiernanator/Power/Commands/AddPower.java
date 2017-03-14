package me.Tiernanator.Power.Commands;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;

import me.Tiernanator.Colours.Colour;
import me.Tiernanator.Power.Main;

public class AddPower {
	
	private static Main plugin;
	
	Player playerForCurrency;

	// group arrays
	static List<String> groups;
	
	//I told you they recurred...(I did in TestPermission anyway)
	private static ChatColor highlight;
	private static ChatColor good;
	
	
	// this has to stay the Main class won't be happy.
	public AddPower(Main main) {
		plugin = main;
	}

	/*
	 * This Function tests if the player has the permission for each of the groups, 
	 * and returns the group's display name if they do
	 */
	public static void addPower(Player player, int amount) {
		
		boolean isOP = false;
		
		if(player.isOp()) {
			isOP = true;
			player.setOp(false);
		}
		
		// constants
		allocateColours();
		
		int previousPower = GetPower.getPower(player);
		
		int total = amount + previousPower;
		
		RemovePower.removePower(player);
		
		String playersPower = "power " + Integer.toString(total);
		
		PermissionAttachment powerAttachment = player.addAttachment(plugin);
		
		powerAttachment.setPermission(playersPower, true);
		
		player.sendMessage(good + "Your power level is now " + highlight + GetPower.getPower(player));
		
		if(isOP) {
			player.setOp(true);
		}

	}
	// constants initialising
	private static void allocateColours() {
			
		highlight = Colour.HIGHLIGHT.getColour();
		good = Colour.GOOD.getColour();
		
	}
}
