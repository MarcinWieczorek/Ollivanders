package StationarySpell;

import me.cakenggt.Ollivanders.Ollivanders;
import me.cakenggt.Ollivanders.SpellProjectile;
import me.cakenggt.Ollivanders.StationarySpellObj;
import me.cakenggt.Ollivanders.StationarySpells;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Destroys spell projectiles crossing the boundary.
 *
 * @author lownes
 */
public class PROTEGO_HORRIBILIS extends StationarySpellObj implements StationarySpell {
	private static final long serialVersionUID = -500146474072361113L;

	public PROTEGO_HORRIBILIS(Player player, Location location, StationarySpells name, Integer radius, Integer duration) {
		super(player, location, name, radius, duration);
	}

	public void checkEffect(Ollivanders p) {
		age();
		List<SpellProjectile> projectiles = p.getProjectiles();

		if(projectiles != null) {
			List<SpellProjectile> projectiles2 = new ArrayList<>(projectiles);

			for(SpellProjectile proj : projectiles2) {
				if(isInside(proj.location) && location.toLocation().distance(proj.location) > radius - 1) {
					p.remProjectile(proj);
				}
			}
		}
	}
}