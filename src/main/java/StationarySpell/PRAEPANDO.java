package StationarySpell;

import me.cakenggt.Ollivanders.ExtraDimensional;
import me.cakenggt.Ollivanders.Ollivanders;
import me.cakenggt.Ollivanders.SpellProjectile;
import me.cakenggt.Ollivanders.StationarySpells;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class PRAEPANDO extends ExtraDimensional implements StationarySpell {
	private static final long serialVersionUID = 2628887080639154071L;
	private Set<UUID> teleported = new HashSet<>();

	public PRAEPANDO(Player player, Location location, StationarySpells name, Integer radius, Integer duration, Integer dimenRadius) {
		super(player, location, name, radius, duration, dimenRadius);
	}

	@Override
	public void checkEffect(Ollivanders p) {
		Location edLocation = getEDLoc().toLocation().clone().add(0, 1.1, 0);
		Location normLocation = location.toLocation();
		edLocation.getWorld().playEffect(edLocation, Effect.MOBSPAWNER_FLAMES, 0);
		normLocation.getWorld().playEffect(normLocation, Effect.MOBSPAWNER_FLAMES, 0);

		for(Entity entity : normLocation.getWorld().getEntities()) {
			if(teleported.contains(entity.getUniqueId())) {
				if(entity.getLocation().distance(normLocation) > radius && !entity.getLocation().getBlock().equals(edLocation.getBlock())) {
					teleported.remove(entity.getUniqueId());
				}
			}
			else {
				if(entity.getLocation().distance(normLocation) <= radius) {
					entity.teleport(edLocation);
					teleported.add(entity.getUniqueId());
				}
				else if(entity.getLocation().getBlock().equals(edLocation.getBlock())) {
					entity.teleport(normLocation);
					teleported.add(entity.getUniqueId());
				}
			}
		}

		for(SpellProjectile sp : p.getProjectiles()) {
			if(sp.getBlock().equals(edLocation.getBlock())) {
				System.out.println("teleported projectile");
				sp.location = normLocation.clone();
			}
		}

		age();
	}
}
