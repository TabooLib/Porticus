package ink.ptms.porticus.bungeeside;

import ink.ptms.porticus.bungeeside.api.PorticusBungeeEvent;
import ink.ptms.porticus.common.MessageBuilder;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.connection.Server;
import net.md_5.bungee.api.event.ProxyPingEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.event.EventHandler;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author 坏黑
 * @since 2020-10-15
 */
public class Porticus extends Plugin implements Listener {

    private static Porticus inst;

    @Override
    public void onEnable() {
        inst = this;
        ProxyServer.getInstance().registerChannel("porticus:main");
        ProxyServer.getInstance().getPluginManager().registerListener(this, new PorticusListener());
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
