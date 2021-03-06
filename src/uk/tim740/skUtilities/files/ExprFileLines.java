package uk.tim740.skUtilities.files;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import uk.tim740.skUtilities.Utils;
import uk.tim740.skUtilities.skUtilities;

import javax.annotation.Nullable;
import java.io.*;
import java.nio.file.Files;

/**
 * Created by tim740 on 17/03/2016
 */
public class ExprFileLines extends SimpleExpression<Number>{
	private Expression<String> path;

	@Override
	@Nullable
	protected Number[] get(Event arg0) {
        File pth = new File(Utils.getDefaultPath(path.getSingle(arg0)));
        if (pth.exists()){
            try {
                return new Number[]{Files.readAllLines(pth.toPath()).size()};
            } catch (Exception e) {
                skUtilities.prSysE(e.getMessage(), getClass().getSimpleName(), e);
            }
        }else{
            skUtilities.prSysE("'" + pth + "' doesn't exist!", getClass().getSimpleName());
        }
        return null;
	}

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, ParseResult arg3) {
        path = (Expression<String>) arg0[0];
        return true;
    }
    @Override
    public Class<? extends Number> getReturnType() {
        return Number.class;
    }
    @Override
    public boolean isSingle() {
        return true;
    }
    @Override
    public String toString(@Nullable Event arg0, boolean arg1) {
        return getClass().getName();
    }
}
