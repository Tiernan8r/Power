package me.Tiernanator.Power.Commands;

import java.util.Set;

import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachmentInfo;

//Simply a class with a public function to remove alll a players permissions
public class RemovePower {

	static boolean isOP;


	// public coz other classes use it, input is the playto remove permissions.
	public static void removePower(Player player) {
		
		isOP = false;

		if (player.isOp()) {
			isOP = true;
			player.setOp(false);
		}

		Set<PermissionAttachmentInfo> permissionsInfo = player
				.getEffectivePermissions();
		for (PermissionAttachmentInfo permission : permissionsInfo) {

			if (permission.getPermission().contains("power")) {

				permission.getAttachment().remove();
				permission.getAttachment()
						.unsetPermission(permission.getPermission());

			}
		}

		if (isOP) {
			player.setOp(true);
		}

		return;
	}

}
