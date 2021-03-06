package uk.tim740.skUtilities.util;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

/**
 * Created by tim740 on 02/03/16
 */
public class EffPrintTag extends Effect{
	private Expression<String> str;
    private int ty;

	@Override
	protected void execute(Event arg0) {
        if (ty == 0){
            Bukkit.getServer().getLogger().info(ChatColor.translateAlternateColorCodes('&', str.getSingle(arg0)));
        }else if (ty == 1){
            Bukkit.getServer().getLogger().warning(ChatColor.translateAlternateColorCodes('&', str.getSingle(arg0)));
        }else{
            Bukkit.getServer().getLogger().severe(ChatColor.translateAlternateColorCodes('&', str.getSingle(arg0)));
        }
	}

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, ParseResult arg3) {
        str = (Expression<String>) arg0[0];
        ty = arg3.mark;
        return true;
    }
    @Override
    public String toString(@Nullable Event arg0, boolean arg1) {
        return getClass().getName();
    }
}