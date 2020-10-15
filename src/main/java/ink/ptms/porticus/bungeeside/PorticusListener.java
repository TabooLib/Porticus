package ink.ptms.porticus.bungeeside;

import ink.ptms.porticus.api.Mission;
import ink.ptms.porticus.api.PorticusAPI;
import ink.ptms.porticus.bungeeside.api.PorticusBungeeEvent;
import ink.ptms.porticus.common.Message;
import ink.ptms.porticus.common.MessageReader;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.connection.Server;
import net.md_5.bungee.api.event.PluginMessageEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.io.IOException;
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
        if (e.isCancelled()) {
            return;
        }
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

    @EventHandler
    public void e(PluginMessageEvent e) {
        if (e.isCancelled()) {
            return;
        }
        if (e.getSender() instanceof Server && e.getTag().equalsIgnoreCase("porticus")) {
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
