package StationarySpell;

import me.cakenggt.Ollivanders.Ollivanders;
import me.cakenggt.Ollivanders.StationarySpellObj;
import me.cakenggt.Ollivanders.StationarySpells;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

/**
 * Hurts any entities within 0.5 meters of the spell wall.
 *
 * @author lownes
 */
public class PROTEGO_MAXIMA extends StationarySpellObj implements StationarySpell {
	double damage;
	private static final long serialVersionUID = -6758494791962491579L;

	public PROTEGO_MAXIMA(Player player, Location location, StationarySpells name, Integer radius, Integer duration, double damage) {
		super(player, location, name, radius, duration);
		this.damage = damage;
	}

	public void checkEffect(Ollivanders p) {
		age();
		Location loc = location.toLocation();

		for(Entity e : loc.getWorld().getEntities()) {
			if(e instanceof Player) {
				Player ply = (Player) e;
				if(ply.isPermissionSet("Ollivanders.BYPASS") && ply.hasPermission("Ollivanders.BYPASS")) {
					continue;
				}
			}

			double distance = e.getLocation().distance(loc);

			if(distance > radius - 0.5 && distance < radius + 0.5) {
				if(e instanceof LivingEntity) {
					((LivingEntity) e).damage(damage);
				}
				else {
					e.remove();
				}

				flair(10);
			}
		}
	}
}