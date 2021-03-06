package uk.tim740.skUtilities.convert;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import uk.tim740.skUtilities.skUtilities;

import javax.annotation.Nullable;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by tim740 on 23/02/2016
 */
public class ExprEncrypt extends SimpleExpression<String> {
    private Expression<String> str, key, cipher;
    private int ty;

    @Override
    @Nullable
    protected String[] get(Event arg0) {
        byte[] cout = new byte[0];
        Cipher c = null;
        try{
            c = Cipher.getInstance(cipher.getSingle(arg0).toUpperCase());
            c.init(ty, new SecretKeySpec(key.getSingle(arg0).getBytes(), cipher.getSingle(arg0).toUpperCase()));
        }catch (Exception e){
            skUtilities.prSysE(e.getMessage() + " '"+ cipher.getSingle(arg0).toUpperCase() +"'", getClass().getSimpleName(), e);
        }
        assert c != null;
        if (ty == Cipher.ENCRYPT_MODE){
            try{
                cout = c.doFinal(str.getSingle(arg0).getBytes());
            }catch (Exception e){
                skUtilities.prSysE(e.getMessage(), getClass().getSimpleName(), e);
            }
            return new String[]{new BASE64Encoder().encode(cout)};
        }else{
            byte[] decry;
            String out = "";
            try{
                decry = new BASE64Decoder().decodeBuffer(str.getSingle(arg0));
                cout = c.doFinal(decry);
            }catch (Exception e) {
                skUtilities.prSysE(e.getMessage(), getClass().getSimpleName(), e);
            }for (byte aCout : cout) {
                out = (out + Character.toString((char) new Byte(aCout).intValue()));
            }
            return new String[]{out};
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, SkriptParser.ParseResult arg3) {
        if (arg3.mark == 0){
            ty = Cipher.ENCRYPT_MODE;
        }else{
            ty = Cipher.DECRYPT_MODE;
        }
        str = (Expression<String>) arg0[0];
        cipher = (Expression<String>) arg0[1];
        key = (Expression<String>) arg0[2];
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
