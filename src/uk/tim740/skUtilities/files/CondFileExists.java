package uk.tim740.skUtilities.files;

import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import uk.tim740.skUtilities.Utils;

import java.io.File;

/**
 * Created by tim740 on 18/03/2016
 */
public class CondFileExists extends Condition {
    private Expression<String> path;

    @Override
    public boolean check(Event arg0) {
        Boolean pth = new File(Utils.getDefaultPath(path.getSingle(arg0))).exists();
        return (isNegated() ? !pth : pth);
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, SkriptParser.ParseResult arg3) {
        path = (Expression<String>) arg0[0];
        setNegated(arg1 == 1);
        return true;
    }
    @Override
    public String toString(Event arg0, boolean arg1) {
        return getClass().getName();
    }


}
