package Spell;

import me.cakenggt.Ollivanders.Ollivanders;
import me.cakenggt.Ollivanders.SpellProjectile;
import me.cakenggt.Ollivanders.Spells;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class GLACIUS_DUO extends SpellProjectile implements Spell {
	boolean move;

	public GLACIUS_DUO(Ollivanders plugin, Player player, Spells name, Double rightWand) {
		super(plugin, player, name, rightWand);
		move = true;
	}

	public void checkEffect() {
		if(move) {
			move();
			Block center = getBlock();
			Material type = center.getType();
			double radius = usesModifier;

			if(type != Material.AIR) {
				for(Block block : getBlocksInRadius(location, radius)) {
					Material changeType = block.getType();

					if(changeType == Material.FIRE) {
						block.setType(Material.AIR);
						changed.add(block);
					}
					else if(changeType == Material.WATER || changeType == Material.STATIONARY_WATER) {
						block.setType(Material.ICE);
						changed.add(block);
					}
					else if(changeType == Material.LAVA || changeType == Material.STATIONARY_LAVA) {
						block.setType(Material.OBSIDIAN);
						changed.add(block);
					}
					else if(changeType == Material.ICE) {
						block.setType(Material.PACKED_ICE);
						changed.add(block);
					}
				}

				kill = false;
				move = false;
				lifeTicks = (int) (-(usesModifier * 1200 / 2));
			}
		}
		else {
			lifeTicks++;
		}

		if(lifeTicks >= 159) {
			revert();
			kill();
		}
	}

	public void revert() {
		for(Block block : changed) {
			Material mat = block.getType();

			if(mat == Material.PACKED_ICE) {
				block.setType(Material.ICE);
			}
			else if(mat == Material.ICE) {
				block.setType(Material.STATIONARY_WATER);
			}
			else if(mat == Material.OBSIDIAN) {
				block.setType(Material.STATIONARY_LAVA);
			}
			else if(mat == Material.AIR) {
				block.setType(Material.FIRE);
			}
		}
	}
}