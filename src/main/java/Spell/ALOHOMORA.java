package Spell;

import me.cakenggt.Ollivanders.Ollivanders;
import me.cakenggt.Ollivanders.SpellProjectile;
import me.cakenggt.Ollivanders.Spells;
import me.cakenggt.Ollivanders.StationarySpellObj;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ALOHOMORA extends SpellProjectile implements Spell {
	public ALOHOMORA(Ollivanders plugin, Player player, Spells name, Double rightWand) {
		super(plugin, player, name, rightWand);
	}

	public void checkEffect() {
		move();
		List<StationarySpellObj> inside = new ArrayList<>();

		for(StationarySpellObj spell : p.getStationary()) {
			if(spell instanceof StationarySpell.COLLOPORTUS && spell.isInside(location)) {
				inside.add(spell);
				kill();
			}
		}

		int subAmount = (int) ((usesModifier * 1200) / inside.size());

		for(StationarySpellObj spell : inside) {
			spell.age(subAmount);
			spell.flair(10);
		}
	}

}