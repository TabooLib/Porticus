package ink.ptms.porticus.bukkitside;

import ink.ptms.porticus.api.API;
import ink.ptms.porticus.api.APIType;
import ink.ptms.porticus.api.Mission;
import org.bukkit.Bukkit;

import java.util.UUID;

/**
 * Porticus
 * ink.ptms.porticus.bukkitside.PorticusAPI
 *
 * @author bkm016
 * @since 2020/10/15 9:48 下午
 */
public class PorticusAPI extends API {

    @Override
    public APIType getType() {
        return APIType.CLIENT;
    }

    @Override
    public Mission createMission() {
        return new MissionBukkit();
    }

    @Override
    public Mission createMission(UUID uid) {
        return new MissionBukkit(uid);
    }
}
