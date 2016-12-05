package Spell;

import me.cakenggt.Ollivanders.Ollivanders;
import me.cakenggt.Ollivanders.SpellProjectile;
import me.cakenggt.Ollivanders.Spells;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

/**
 * Places a flagrante affect on the item.
 *
 * @author lownes
 */
public class FLAGRANTE extends SpellProjectile implements Spell {
	public FLAGRANTE(Ollivanders plugin, Player player, Spells name, Double rightWand) {
		super(plugin, player, name, rightWand);
	}

	public void checkEffect() {
		move();
		List<Item> items = getItems(1);

		for(Item item : items) {
			if(isWand(item.getItemStack())) {
				return;
			}

			ItemStack stack = item.getItemStack().clone();
			int amount = stack.getAmount();
			ItemMeta meta = stack.getItemMeta();
			List<String> lore = new ArrayList<>();

			if(meta.hasLore()) {
				lore = meta.getLore();
				for(int i = 0; i < lore.size(); i++) {
					if(lore.get(i).contains("Flagrante ")) {
						String[] loreParts = lore.get(i).split(" ");
						int magnitude = Integer.parseInt(loreParts[1]);
						if(magnitude < usesModifier) {
							magnitude = (int) usesModifier;
						}
						lore.set(i, "Flagrante " + magnitude);
					}
					else {
						lore.add("Flagrante " + (int) usesModifier);
					}
				}
			}
			else {
				lore.add("Flagrante " + (int) usesModifier);
			}

			meta.setLore(lore);
			stack.setItemMeta(meta);
			stack.setAmount(1);

			if(amount > 1) {
				item.getItemStack().setAmount(amount - 1);
			}
			else {
				item.remove();
			}

			item.getWorld().dropItem(item.getLocation(), stack);
			kill();
			return;
		}
	}

	/**
	 * Is the item a wand?
	 *
	 * @param held Player to check.
	 * @return True if wand, false if not.
	 */
	public boolean isWand(ItemStack held) {
		if(player.getItemInHand() != null
				&& (held.getType() == Material.STICK || held.getType() == Material.BLAZE_ROD)
				&& held.getItemMeta().hasLore()) {
			List<String> lore = held.getItemMeta().getLore();
			return lore.get(0).split(" and ").length == 2;
		}

		return false;
	}
}