package Spell;

import me.cakenggt.Ollivanders.Ollivanders;
import me.cakenggt.Ollivanders.SpellProjectile;
import me.cakenggt.Ollivanders.Spells;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;

/**
 * Encases target's head in a melon itemstack with amount 0
 *
 * @author lownes
 */
public class MELOFORS extends SpellProjectile implements Spell {
	public MELOFORS(Ollivanders plugin, Player player, Spells name, Double rightWand) {
		super(plugin, player, name, rightWand);
	}

	public void checkEffect() {
		move();

		for(LivingEntity live : getLivingEntities(1)) {
			EntityEquipment ee = live.getEquipment();
			ItemStack helmet = ee.getHelmet();

			if(helmet != null && helmet.getType() != Material.AIR) {
				live.getWorld().dropItem(live.getEyeLocation(), helmet);
			}

			ee.setHelmet(new ItemStack(Material.MELON_BLOCK, 0));
			kill();
			return;
		}
	}
}