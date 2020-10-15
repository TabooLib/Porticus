package ink.ptms.porticus.api;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Porticus API 通用入口
 *
 * @author 坏黑
 * @since 2020-10-15
 */
public class PorticusAPI {

    private static final List<Mission> missions = Lists.newCopyOnWriteArrayList();
    private static API api;

    static {
        try {
            Class.forName("org.bukkit.Bukkit");
            api = new ink.ptms.porticus.bukkitside.PorticusAPI();
        } catch (Throwable ignored) {
        }
        try {
            Class.forName("net.md_5.bungee.BungeeCord");
            api = new ink.ptms.porticus.bungeeside.PorticusAPI();
        } catch (Throwable ignored) {
        }
    }

    /**
     * 获取正在运行的通讯任务
     */
    public static List<Mission> getMissions() {
        return missions;
    }

    /**
     * 获取 Porticus API
     */
    public static API getAPI() {
        return api;
    }
}
