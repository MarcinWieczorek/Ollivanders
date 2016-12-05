package Spell;

import me.cakenggt.Ollivanders.Ollivanders;
import me.cakenggt.Ollivanders.SpellProjectile;
import me.cakenggt.Ollivanders.Spells;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 * Bubble head charm gives the player water breathing for a length of 
 * time depending on the player's spell level.
 * @author lownes
 *
 */
public class EBUBLIO extends SpellProjectile implements Spell{
	
	public EBUBLIO(Ollivanders p, Player player, Spells name, Double rightWand){
		super(p, player, name, rightWand);
	}


	public void checkEffect() {
		move();
		player.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, (int) (usesModifier*1200), 1), true);
		kill();
	}
}