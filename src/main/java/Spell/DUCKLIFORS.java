package Spell;

import me.cakenggt.Ollivanders.Ollivanders;
import me.cakenggt.Ollivanders.Spells;
import me.cakenggt.Ollivanders.Transfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class DUCKLIFORS extends Transfiguration implements Spell {
	public DUCKLIFORS(Ollivanders plugin, Player player, Spells name, Double rightWand) {
		super(plugin, player, name, rightWand);
	}

	public void checkEffect() {
		simpleTransfigure(EntityType.CHICKEN, null);
	}
}