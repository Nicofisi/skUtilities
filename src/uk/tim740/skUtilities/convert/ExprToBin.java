package uk.tim740.skUtilities.convert;

import javax.annotation.Nullable;

import org.bukkit.event.Event;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

/**
 * Created by tim740.
 */
public class ExprToBin extends SimpleExpression<String> {
	private Expression<String> str;
	private int ty;

	@Override
	@Nullable
	protected String[] get(Event arg0) {
		if (ty == 0){
			 byte[] by = str.getSingle(arg0).getBytes();
			 StringBuilder bin = new StringBuilder();
			 for (byte b : by) {
			    int val = b;
			    for (int i = 0; i < 8; i++) {
			    	bin.append((val & 128) == 0 ? 0 : 1);
			        val <<= 1;
			    }
			    bin.append(' ');
			}
			return new String[]{bin.toString()};
		}else if (ty == 1){
			return new String[]{Integer.toBinaryString(Integer.parseInt(str.getSingle(arg0)))};
		}else if (ty == 2){
			return new String[]{Integer.toBinaryString(Integer.parseInt(str.getSingle(arg0), 16))};
		}else{
			return new String[]{Integer.toBinaryString(Integer.parseInt(str.getSingle(arg0), 8))};
		}
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
