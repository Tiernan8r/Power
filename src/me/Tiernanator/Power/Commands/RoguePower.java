package me.Tiernanator.Power.Commands;

import java.util.List;
import java.util.Set;

import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachmentInfo;

import me.Tiernanator.Factions.Factions.GetFaction;

public class RoguePower {

	public static Integer getPower(Player player) {
		
		List<String> allFactions = GetFaction.allFactions();
		String rogue = allFactions.get(allFactions.size() - 1);
		
		String playerFaction = GetFaction.getFaction(player);
		
		if(!(playerFaction.equalsIgnoreCase(rogue))) {
			return 0;
		}
		
		Set<PermissionAttachmentInfo> permissionsInfo = player
				.getEffectivePermissions();
		
		for (PermissionAttachmentInfo permission : permissionsInfo) {
			if (permission.getPermission().contains(rogue)) {

				String permissionString = permission.getPermission();
				permissionString.replace(rogue, "");
				
				int power = Integer.parseInt(permissionString);
				
				return power;
			}
		}
		
		return 0;
		
	}
	
}
