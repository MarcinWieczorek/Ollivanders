package Spell;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import StationarySpell.COLLOPORTUS;
import me.cakenggt.Ollivanders.Ollivanders;
import me.cakenggt.Ollivanders.SpellProjectile;
import me.cakenggt.Ollivanders.Spells;
import me.cakenggt.Ollivanders.StationarySpellObj;

/**
 * Mines a line of blocks of length depending on the player's level 
 * in this spell.
 * @author lownes
 *
 */
public class DEFODIO extends SpellProjectile implements Spell{

	int depth;

	public DEFODIO(Ollivanders plugin, Player player, Spells name,
			Double rightWand) {
		super(plugin, player, name, rightWand);
		depth = (int)usesModifier;
	}

	public void checkEffect() {
		Location newl = location.clone();
		Location forward = newl.add(vector);
		Block block = forward.getBlock();
		List<Block> tempBlocks = p.getTempBlocks();
		if (depth > 0){
			for (StationarySpellObj stat : p.getStationary()){
				if (stat instanceof COLLOPORTUS){
					if (stat.isInside(block.getLocation())){
						return;
					}
				}
			}
			if (block.getType() != Material.BEDROCK && !tempBlocks.contains(block)){
				if (block.breakNaturally()){
					depth --;
				}
			}
			else{
				kill();
			}
		}
		move();
	}

}