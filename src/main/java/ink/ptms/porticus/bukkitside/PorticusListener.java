package ink.ptms.porticus.bukkitside;

import ink.ptms.porticus.api.Mission;
import ink.ptms.porticus.api.PorticusAPI;
import ink.ptms.porticus.bukkitside.api.PorticusBukkitEvent;
import ink.ptms.porticus.common.Message;
import ink.ptms.porticus.common.MessageReader;
import io.izzel.taboolib.module.inject.TListener;
import io.izzel.taboolib.module.inject.TSchedule;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * @author 坏黑
 * @since 2020-10-15
 */
@TListener
public class PorticusListener implements Listener, PluginMessageListener {

    public PorticusListener() {
        Bukkit.getMessenger().registerIncomingPluginChannel(Porticus.getInst().getPlugin(), "porticus", this);
        Bukkit.getMessenger().registerOutgoingPluginChannel(Porticus.getInst().getPlugin(), "porticus");
    }

    @TSchedule(period = 20)
    void timeout() {
        for (Mission mission : ink.ptms.porticus.api.PorticusAPI.getMissions()) {
            if (!mission.isTimeout()) {
                if (mission.getTimeoutRunnable() != null) {
                    try {
                        mission.getTimeoutRunnable().run();
                    } catch (Throwable t) {
                        t.printStackTrace();
                    }
                }
                PorticusAPI.getMissions().remove(mission);
            }
        }
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void e(PorticusBukkitEvent e) {
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

    @Override
    public void onPluginMessageReceived(@NotNull String channel, @NotNull Player player, @NotNull byte[] bytes) {
        if (channel.equalsIgnoreCase("porticus")) {
            try {
                Message message = MessageReader.read(bytes);
                if (message.isCompleted()) {
                    PorticusBukkitEvent.call(player, message.getMessages().get(0).getUID(), message.build());
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
