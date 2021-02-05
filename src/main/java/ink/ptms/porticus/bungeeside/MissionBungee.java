package ink.ptms.porticus.bungeeside;

import ink.ptms.porticus.api.Mission;
import net.md_5.bungee.api.connection.Connection;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.connection.Server;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

/**
 * Porticus
 * ink.ptms.porticus.bungeeside.MissionBungee
 *
 * @author bkm016
 * @since 2020/10/15 10:06 下午
 */
public class MissionBungee extends Mission {

    public MissionBungee() {
        super();
    }

    public MissionBungee(UUID uid) {
        super(uid);
    }

    @Override
    public void run(@NotNull Object target) {
        super.run(target);
        if (target instanceof Server) {
            Porticus.getInst().sendBungeeMessage((Server) target, command);
        } else if (target instanceof ProxiedPlayer) {
            Porticus.getInst().sendBungeeMessage((ProxiedPlayer) target, command);
        }
    }
}
