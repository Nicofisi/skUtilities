package uk.tim740.skUtilities.files;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import uk.tim740.skUtilities.Utils;
import uk.tim740.skUtilities.files.event.EvtFileDeletion;
import uk.tim740.skUtilities.skUtilities;

import javax.annotation.Nullable;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by tim740 on 16/03/2016
 */
public class EffDeleteFile extends Effect {
    private Expression<String> path;

    @Override
    protected void execute(Event arg0) {
        Path pth = Paths.get(Utils.getDefaultPath(path.getSingle(arg0)));
        try {
            EvtFileDeletion efd = new EvtFileDeletion(pth.toFile());
            Bukkit.getServer().getPluginManager().callEvent(efd);
            if (!efd.isCancelled()) {
                Files.delete(pth);
            }
        } catch (IOException e) {
            skUtilities.prSysE("File: '" + pth + "' doesn't exist!", getClass().getSimpleName(), e);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, SkriptParser.ParseResult arg3) {
        path = (Expression<String>) arg0[0];
        return true;
    }
    @Override
    public String toString(@Nullable Event arg0, boolean arg1) {
        return getClass().getName();
    }
}
