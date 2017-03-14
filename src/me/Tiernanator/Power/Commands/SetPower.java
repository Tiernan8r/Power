package me.Tiernanator.Power.Commands;

import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.plugin.java.JavaPlugin;

public class SetPower {
	
	public static void setPower(Player player, int amount, JavaPlugin plugin) {
		
		boolean isOP = false;
		
		if(player.isOp()) {
			isOP = true;
			player.setOp(false);
		}
		
		RemovePower.removePower(player);
		
		String playersPower = "power " + Integer.toString(amount);
		
		PermissionAttachment powerAttachment = player.addAttachment(plugin);
		
		powerAttachment.setPermission(playersPower, true);
		
		if(isOP) {
			player.setOp(true);
		}

	}

}
