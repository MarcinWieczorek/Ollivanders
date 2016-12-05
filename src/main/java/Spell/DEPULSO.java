package Spell;

import me.cakenggt.Ollivanders.Ollivanders;
import me.cakenggt.Ollivanders.SpellProjectile;
import me.cakenggt.Ollivanders.Spells;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * Shoots the hit entity away from you.
 *
 * @author lownes
 */
public class DEPULSO extends SpellProjectile implements Spell {
	public DEPULSO(Ollivanders plugin, Player player, Spells name, Double rightWand) {
		super(plugin, player, name, rightWand);
	}

	public void checkEffect() {
		move();
		List<Entity> entities = getCloseEntities(1);

		for(Entity entity : entities) {
			entity.setVelocity(player.getLocation().getDirection().normalize().multiply(usesModifier / 20));
			kill();
			return;
		}
	}
}