package Effect;

import me.cakenggt.Ollivanders.Effects;
import me.cakenggt.Ollivanders.OEffect;
import me.cakenggt.Ollivanders.Ollivanders;
import org.bukkit.entity.Player;

public class MEMORY_POTION extends OEffect implements Effect {

	/**
	 *
	 */
	private static final long serialVersionUID = 4202743924958098780L;

	public MEMORY_POTION(Player sender, Effects effect, int duration) {
		super(sender, effect, duration);
	}

	@Override
	public void checkEffect(Ollivanders p, Player owner) {
		age(1);
	}

}
