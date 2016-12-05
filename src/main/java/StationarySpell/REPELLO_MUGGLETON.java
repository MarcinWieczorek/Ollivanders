package StationarySpell;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import me.cakenggt.Ollivanders.Ollivanders;
import me.cakenggt.Ollivanders.StationarySpellObj;
import me.cakenggt.Ollivanders.StationarySpells;

/**Hides all blocks within its area by sending out block changes.
 * Hides all players within its area. The code to hide players is located
 * in OllivandersSchedule.invisPlayer()
 * @author lownes
 *
 */
public class REPELLO_MUGGLETON extends StationarySpellObj implements StationarySpell{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4106501979697343806L;

	public REPELLO_MUGGLETON(Player player, Location location,
			StationarySpells name, Integer radius, Integer duration) {
		super(player, location, name, radius, duration);
	}

	@SuppressWarnings("deprecation")
	public void checkEffect(Ollivanders p) {
		age();
		if (p.getConfig().getBoolean("muggletonBlockChange")){
			if (duration % 20 == 0){
				Material toMat = getBlock().getType();
				byte toDat = getBlock().getData();
				double viewDistance = Math.sqrt(2*Math.pow(((Bukkit.getServer().getViewDistance()+1)*16),2));
				for (Player ply : getBlock().getWorld().getPlayers()){
					if (ply.isPermissionSet("Ollivanders.BYPASS")){
						if (ply.hasPermission("Ollivanders.BYPASS")){
							continue;
						}
					}
					if (ply.getLocation().distance(location.toLocation()) < viewDistance && !isInside(ply.getLocation())){
						for (Block block : getBlocksInRadius(location.toLocation(), radius)){
							ply.sendBlockChange(block.getLocation(), toMat, toDat);
						}
					}
					else if(isInside(ply.getLocation())){
						for (Block block : getBlocksInRadius(location.toLocation(), radius)){
							ply.sendBlockChange(block.getLocation(), block.getType(), block.getData());
						}
					}
				}
			}
			if (duration <= 1){
				for (Player ply : getBlock().getWorld().getPlayers()){
					for (Block block : getBlocksInRadius(location.toLocation(), radius)){
						ply.sendBlockChange(block.getLocation(), block.getType(), block.getData());
					}
				}
			}
		}
	}

}