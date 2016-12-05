package Spell;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.block.DoubleChest;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import me.cakenggt.Ollivanders.Ollivanders;
import me.cakenggt.Ollivanders.SpellProjectile;
import me.cakenggt.Ollivanders.Spells;
import me.cakenggt.Ollivanders.StationarySpellObj;

public class PACK extends SpellProjectile implements Spell{

	public PACK(Ollivanders plugin, Player player, Spells name, Double rightWand) {
		super(plugin, player, name, rightWand);
	}

	public void checkEffect() {
		move();
		Block block = getBlock();
		if (block.getType() == Material.CHEST){
			for (StationarySpellObj stat : p.getStationary()){
				if (stat instanceof StationarySpell.COLLOPORTUS && stat.active){
					stat.flair(10);
					return;
				}
			}
			Chest c = (Chest) block.getState();
			Inventory inv = c.getInventory();
			if (inv.getHolder() instanceof DoubleChest){
				inv = ((DoubleChest)inv.getHolder()).getInventory();
			}
			for (Item item : getItems(usesModifier)){
				if (inv.addItem(item.getItemStack()).size() == 0){
					item.remove();
				}
			}
		}
	}
	
}