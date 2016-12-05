package Spell;

import java.util.List;

import me.cakenggt.Ollivanders.Ollivanders;
import me.cakenggt.Ollivanders.SpellProjectile;
import me.cakenggt.Ollivanders.Spells;

import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

/**
 * Does direct damage to a living entity according to your level 
 * in the spell.
 * @author lownes
 *
 */
public class AVADA_KEDAVRA extends SpellProjectile implements Spell{

	@SuppressWarnings("deprecation")
	public AVADA_KEDAVRA(Ollivanders p, Player player, Spells name, Double rightWand){
		super(p, player, name, rightWand);
		moveEffectData = Material.MELON_BLOCK.getId();
	}


	public void checkEffect() {
		move();
		List<LivingEntity> entities = getLivingEntities(1);
		if (entities.size() > 0){
			LivingEntity entity = entities.get(0);
			entity.damage(usesModifier*2, player);
			kill = true;
			return;
		}
	}
}