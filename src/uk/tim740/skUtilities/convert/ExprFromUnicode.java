package uk.tim740.skUtilities.convert;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import uk.tim740.skUtilities.skUtilities;

import javax.annotation.Nullable;
import java.io.IOException;
import java.io.StringReader;
import java.util.Objects;
import java.util.Properties;

/**
 * Created by tim740 on 07/03/16
 */
public class ExprFromUnicode extends SimpleExpression<String> {
	private Expression<String> str;
    private int ty;

	@Override
	@Nullable
	protected String[] get(Event arg0) {
        String out = "";
        Properties p = new Properties();
        try {
            p.load(new StringReader("key="+str.getSingle(arg0)));
        } catch (IOException e) {
            skUtilities.prSysE(e.getMessage(), getClass().getSimpleName(), e);
        }
        if (ty == 0) {
            out = p.getProperty("key");
        }else{
            String iout = p.getProperty("key");
            for(String c : iout.split("")) {
                if (Objects.equals(out, "")) {
                    out = (Integer.toString(c.charAt(0)));
                } else {
                    out += "," + Integer.toString(c.charAt(0));
                }
            }
        }
        return new String[]{out};
	}

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, ParseResult arg3) {
        ty = arg3.mark;
        str = (Expression<String>) arg0[0];
        return true;
    }
    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
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