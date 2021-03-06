package Spell;

import me.cakenggt.Ollivanders.Ollivanders;
import me.cakenggt.Ollivanders.SpellProjectile;
import me.cakenggt.Ollivanders.Spells;
import org.bukkit.World;
import org.bukkit.entity.Player;

/**
 * Ends a storm for a variable duration
 *
 * @author lownes
 */
public class METEOLOJINX_RECANTO extends SpellProjectile implements Spell {
	public METEOLOJINX_RECANTO(Ollivanders plugin, Player player, Spells name, Double rightWand) {
		super(plugin, player, name, rightWand);
	}

	public void checkEffect() {
		move();

		if(location.getY() > 256) {
			World world = location.getWorld();
			int duration = world.getWeatherDuration();

			if(!world.hasStorm()) {
				world.setWeatherDuration((int) (duration + (usesModifier * 1200)));
			}
			else {
				duration -= usesModifier * 1200;

				if(duration < 0) {
					duration = -duration;
					world.setStorm(false);
				}

				world.setWeatherDuration(duration);
			}
			kill();
		}
	}
}