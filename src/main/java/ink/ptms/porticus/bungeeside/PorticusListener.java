package ink.ptms.porticus.bungeeside;

import ink.ptms.porticus.api.Mission;
import ink.ptms.porticus.api.PorticusAPI;
import ink.ptms.porticus.bungeeside.api.PorticusBungeeEvent;
import ink.ptms.porticus.common.Message;
import ink.ptms.porticus.common.MessageReader;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.connection.Server;
import net.md_5.bungee.api.event.PluginMessageEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * @author Bkm016
 * @since 2018-04-16
 */
public class PorticusListener implements Listener {

    public PorticusListener() {
        BungeeCord.getInstance().getScheduler().schedule(Porticus.getInst(), () -> {
            for (Mission mission : PorticusAPI.getMissions()) {
                if (!mission.isTimeout()) {
                    if (mission.getTimeoutRunnable() != null) {
                        mission.getTimeoutRunnable().run();
                    }
                    PorticusAPI.getMissions().remove(mission);
                }
            }
        }, 1, 1, TimeUnit.SECONDS);
    }

    @EventHandler
    public void e(PorticusBungeeEvent e) {
        System.out.println("[Porticus] " + Arrays.toString(e.getArgs()));
        if (e.isCancelled()) {
            return;
        }
        if (e.get(0).equals("porticus")) {
            switch (e.get(1)) {
                case "connect": {
                    ProxiedPlayer proxiedPlayer = ProxyServer.getInstance().getPlayer(e.get(2));
                    ServerInfo serverInfo = ProxyServer.getInstance().getServerInfo(e.get(3));
                    if (proxiedPlayer != null && serverInfo != null) {
                        proxiedPlayer.connect(serverInfo);
                    }
                    break;
                }
                case "whois": {
                    ProxiedPlayer proxiedPlayer = ProxyServer.getInstance().getPlayer(e.get(2));
                    if (proxiedPlayer != null) {
                        e.response(proxiedPlayer.getServer().getInfo().getName());
                    }
                    break;
                }
                default:
                    break;
            }
        } else {
            for (Mission mission : PorticusAPI.getMissions()) {
                if (mission.getUID().equals(e.getUID())) {
                    if (mission.getResponseConsumer() != null) {
                        try {
                            mission.getResponseConsumer().accept(e.getArgs());
                        } catch (Throwable t) {
                            t.printStackTrace();
                        }
                    }
                    PorticusAPI.getMissions().remove(mission);
                }
            }
        }
    }

    @EventHandler
    public void e(PluginMessageEvent e) {
        if (e.isCancelled()) {
            return;
        }
        if (e.getSender() instanceof Server && e.getTag().equalsIgnoreCase("porticus:main")) {
            try {
                Message message = MessageReader.read(e.getData());
                if (message.isCompleted()) {
                    PorticusBungeeEvent.call((Server) e.getSender(), message.getMessages().get(0).getUID(), message.build());
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
