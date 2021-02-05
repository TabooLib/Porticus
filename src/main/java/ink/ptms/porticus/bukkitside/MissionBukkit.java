package ink.ptms.porticus.bukkitside;

import ink.ptms.porticus.api.Mission;
import ink.ptms.porticus.api.PorticusAPI;
import net.md_5.bungee.BungeeCord;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Porticus
 * ink.ptms.porticus.bukkitside.MissionBukkit
 *
 * @author bkm016
 * @since 2020/10/15 10:03 下午
 */
public class MissionBukkit extends Mission {

    public MissionBukkit() {
    }

    public MissionBukkit(UUID uid) {
        super(uid);
    }

    @Override
    public void run(@NotNull Object target) {
        super.run(target);
        Porticus.INSTANCE.sendBukkitMessage((Player) target, command);
    }
}
