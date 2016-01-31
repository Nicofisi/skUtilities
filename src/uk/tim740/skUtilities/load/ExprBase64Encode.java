package uk.tim740.skUtilities.load;

import javax.annotation.Nullable;
import java.util.Base64;
import java.nio.charset.StandardCharsets;

import org.bukkit.event.Event;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

public class ExprBase64Encode extends SimpleExpression<String> {
	private int bEncode;
	private Expression<String> string;

	@Override
	public Class<? extends String> getReturnType() {
		return String.class;
	}
	@Override
	public boolean isSingle() {
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, ParseResult arg3) {
		bEncode = arg3.mark;
		this.string = (Expression<String>) arg0[0];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return "encode %string% to base[ ]64 using (0�utf-8|1�ascii|2�iso-8859-1)";
	}
	@Override
	@Nullable
	protected String[] get(Event arg0) {
		String s = this.string.getSingle(arg0);
		if (bEncode == 0){
			byte[] auby = s.getBytes(StandardCharsets.UTF_8);
			return new String[]{Base64.getEncoder().encodeToString(auby)};
		}else if (bEncode == 1){
			byte[] auby = s.getBytes(StandardCharsets.US_ASCII);
			return new String[]{Base64.getEncoder().encodeToString(auby)};
		}else{
			byte[] auby = s.getBytes(StandardCharsets.ISO_8859_1);
			return new String[]{Base64.getEncoder().encodeToString(auby)};
		}
	}
}