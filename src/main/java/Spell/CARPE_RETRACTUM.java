package Spell;

import me.cakenggt.Ollivanders.Ollivanders;
import me.cakenggt.Ollivanders.SpellProjectile;
import me.cakenggt.Ollivanders.Spells;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * Pulls a living entity towards the caster.
 *
 * @author lownes
 */
public class CARPE_RETRACTUM extends SpellProjectile implements Spell {
	public CARPE_RETRACTUM(Ollivanders plugin, Player player, Spells name, Double rightWand) {
		super(plugin, player, name, rightWand);
	}

	public void checkEffect() {
		move();
		List<LivingEntity> living = getLivingEntities(1);

		for(LivingEntity live : living) {
			live.setVelocity(player.getEyeLocation().toVector().subtract(live.getLocation().toVector()).normalize().multiply(usesModifier / 10));
			kill();
			return;
		}
	}
}