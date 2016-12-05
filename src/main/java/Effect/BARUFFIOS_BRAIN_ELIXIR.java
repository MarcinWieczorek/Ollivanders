package Effect;

import me.cakenggt.Ollivanders.Effects;
import me.cakenggt.Ollivanders.OEffect;
import me.cakenggt.Ollivanders.Ollivanders;
import org.bukkit.entity.Player;

public class BARUFFIOS_BRAIN_ELIXIR extends OEffect implements Effect {

	/**
	 *
	 */
	private static final long serialVersionUID = 8344150682258820456L;

	public BARUFFIOS_BRAIN_ELIXIR(Player sender, Effects effect, int duration) {
		super(sender, effect, duration);
	}

	@Override
	public void checkEffect(Ollivanders p, Player owner) {
		age(1);
	}

}
