package Spell;

import me.cakenggt.Ollivanders.Ollivanders;
import me.cakenggt.Ollivanders.SpellProjectile;
import me.cakenggt.Ollivanders.Spells;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * Hurts skeletons, withers, and wither skeletons
 *
 * @author lownes
 */
public class BRACKIUM_EMENDO extends SpellProjectile implements Spell {
	public BRACKIUM_EMENDO(Ollivanders plugin, Player player, Spells name, Double rightWand) {
		super(plugin, player, name, rightWand);
	}

	public void checkEffect() {
		move();
		List<LivingEntity> entities = getLivingEntities(1);
		for(LivingEntity entity : entities) {
			EntityType type = entity.getType();

			if(type == EntityType.SKELETON || type == EntityType.WITHER_SKULL || type == EntityType.WITHER) {
				entity.damage(usesModifier * 2, player);
				kill();
				return;
			}
		}
	}
}