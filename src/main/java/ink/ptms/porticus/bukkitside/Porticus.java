package ink.ptms.porticus.bukkitside;

import ink.ptms.porticus.common.MessageBuilder;
import io.izzel.taboolib.loader.Plugin;
import io.izzel.taboolib.module.inject.TInject;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.io.IOException;

/**
 * @author 坏黑
 * @since 2020-10-15
 */
public class Porticus extends Plugin {

    public static Porticus INSTANCE = new Porticus();

    public void sendBukkitMessage(Player player, String... args) {
        Bukkit.getScheduler().runTaskAsynchronously(getPlugin(), () -> {
            try {
                for (byte[] bytes : MessageBuilder.create(args)) {
                    player.sendPluginMessage(getPlugin(), "porticus:main", bytes);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
