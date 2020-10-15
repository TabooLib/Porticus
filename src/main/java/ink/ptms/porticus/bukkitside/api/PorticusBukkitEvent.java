package ink.ptms.porticus.bukkitside.api;

import ink.ptms.porticus.common.Response;
import io.izzel.taboolib.module.event.EventCancellable;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * @author 坏黑
 * @since 2018-04-16
 */
public class PorticusBukkitEvent extends EventCancellable<PorticusBukkitEvent> implements Response {

    private final Player sender;
    private final String[] args;
    private final UUID uid;

    public static void call(Player sender, UUID uid, String[] args) {
        Bukkit.getPluginManager().callEvent(new PorticusBukkitEvent(sender, uid, args));
    }

    PorticusBukkitEvent(Player sender, UUID uid, String[] args) {
        this.sender = sender;
        this.args = args;
        this.uid = uid;
    }

    @Override
    public void response(String... args) {
    }

    public Player getSender() {
        return this.sender;
    }

    public String[] getArgs() {
        return this.args;
    }

    public UUID getUID() {
        return this.uid;
    }
}
