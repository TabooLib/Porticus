package ink.ptms.porticus.bungeeside;

import com.google.common.collect.Lists;
import ink.ptms.porticus.api.Mission;
import ink.ptms.porticus.api.PorticusAPI;
import ink.ptms.porticus.common.MessageBuilder;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.connection.Server;
import net.md_5.bungee.api.plugin.Plugin;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author 坏黑
 * @since 2020-10-15
 */
public class Porticus extends Plugin {

    private static Porticus inst;

    @Override
    public void onEnable() {
        inst = this;
        ProxyServer.getInstance().registerChannel("porticus");
    }

    public void sendBungeeMessage(ProxiedPlayer player, String... args) {
        sendBungeeMessage(player.getServer(), args);
    }

    public void sendBungeeMessage(Server server, String... args) {
        BungeeCord.getInstance().getScheduler().runAsync(this, () -> {
            try {
                for (byte[] bytes : MessageBuilder.create(args)) {
                    server.sendData("porticus", bytes);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public static Porticus getInst() {
        return inst;
    }
}
