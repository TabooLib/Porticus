package ink.ptms.porticus.bungeeside;

import ink.ptms.porticus.api.Mission;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import org.jetbrains.annotations.NotNull;

/**
 * Porticus
 * ink.ptms.porticus.bungeeside.MissionBungee
 *
 * @author bkm016
 * @since 2020/10/15 10:06 下午
 */
public class MissionBungee extends Mission {

    @Override
    public void run(@NotNull Object target) {
        super.run(target);
        Porticus.getInst().sendBungeeMessage((ProxiedPlayer) target, command);
    }
}
