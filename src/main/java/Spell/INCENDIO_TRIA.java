package Spell;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import me.cakenggt.Ollivanders.Ollivanders;
import me.cakenggt.Ollivanders.SpellProjectile;
import me.cakenggt.Ollivanders.Spells;

/**
 * Creates a larger incendio that doesn't stop after hitting an entity.
 * @author lownes
 *
 */
public class INCENDIO_TRIA extends SpellProjectile implements Spell{

	private double lifeTime;
	boolean move;

	public INCENDIO_TRIA(Ollivanders plugin, Player player, Spells name,
			Double rightWand) {
		super(plugin, player, name, rightWand);
		location.add(vector.multiply(2));
		lifeTime = usesModifier*16;
		move = true;
	}

	public void checkEffect() {
		if (move){
			move();
			//Check if the blocks are still on fire.
			Set<Block> remChange = new HashSet<Block>();
			for (Block block : changed){
				if (block.getType() != Material.FIRE){
					remChange.add(block);
				}
			}
			changed.removeAll(remChange);
			for (Block block : getBlocksInRadius(location, 4)){
				block.getWorld().playEffect(block.getLocation(), Effect.MOBSPAWNER_FLAMES, 0);
				if (block.getType() == Material.AIR){
					block.setType(Material.FIRE);
					changed.add(block);
				}
			}
			int modifier = (int)(usesModifier*16);
			List<Item> items = getItems(2);
			for (Item item : items){
				item.setFireTicks(modifier);
			}
			List<LivingEntity> living = getLivingEntities(2);
			for (LivingEntity live : living){
				live.setFireTicks(modifier);
			}
			for (SpellProjectile proj : p.getProjectiles()){
				if ((proj.name == Spells.GLACIUS || proj.name == Spells.GLACIUS_DUO || proj.name == Spells.GLACIUS_TRIA) && proj.location.getWorld() == location.getWorld()){
					if (proj.location.distance(location) < 3){
						proj.revert();
						proj.kill();
					}
				}
			}
			if (lifeTicks > lifeTime){
				kill = false;
				move = false;
				lifeTicks = (int)(-(usesModifier*1200/4));
			}
		}
		else{
			lifeTicks ++;
		}
		if (lifeTicks >= 159){
			revert();
			kill();
		}
	}
	
	public void revert(){
		for (Block block : changed){
			Material mat = block.getType();
			if (mat == Material.FIRE){
				block.setType(Material.AIR);
			}
		}
	}

}