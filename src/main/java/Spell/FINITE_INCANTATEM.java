package Spell;

import me.cakenggt.Ollivanders.OEffect;
import me.cakenggt.Ollivanders.OPlayer;
import me.cakenggt.Ollivanders.Ollivanders;
import me.cakenggt.Ollivanders.SpellProjectile;
import me.cakenggt.Ollivanders.Spells;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

/**
 * Reduces any spell effects on an item
 *
 * @author lownes
 */
public class FINITE_INCANTATEM extends SpellProjectile implements Spell {
	public FINITE_INCANTATEM(Ollivanders plugin, Player player, Spells name, Double rightWand) {
		super(plugin, player, name, rightWand);
	}

	public void checkEffect() {
		move();
		for(LivingEntity live : getLivingEntities(1)) {
			if(live instanceof Player) {
				Player ply = (Player) live;
				OPlayer op = p.getOPlayer(ply);

				for(OEffect effect : op.getEffects()) {
					effect.age((int) (usesModifier * 1200));
				}

				kill();
				return;
			}
		}

		for(Item item : getItems(1)) {
			ItemStack stack = item.getItemStack();
			ItemMeta meta = stack.getItemMeta();

			if(meta.hasLore()) {
				List<String> lore = meta.getLore();
				List<String> newLore = new ArrayList<>();
				for(String string : lore) {
					if(string.contains("Geminio ")) {
						String[] loreParts = string.split(" ");
						int magnitude = Integer.parseInt(loreParts[1]);
						magnitude -= (int) usesModifier;

						if(magnitude > 0) {
							newLore.add("Geminio " + magnitude);
						}
					}
					else if(string.contains("Flagrante ")) {
						String[] loreParts = string.split(" ");
						int magnitude = Integer.parseInt(loreParts[1]);
						magnitude -= (int) usesModifier;
						if(magnitude > 0) {
							newLore.add("Flagrante " + magnitude);
						}
					}
					else if(string.contains("Portkey ")) {
						//remove the portkey by not adding it to newLore
					}
					else {
						newLore.add(string);
					}
				}

				meta.setLore(newLore);
				stack.setItemMeta(meta);
				item.setItemStack(stack);
			}

			kill();
		}
	}
}