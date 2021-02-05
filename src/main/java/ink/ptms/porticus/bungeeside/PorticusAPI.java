package ink.ptms.porticus.bungeeside;

import ink.ptms.porticus.api.API;
import ink.ptms.porticus.api.APIType;
import ink.ptms.porticus.api.Mission;

import java.util.UUID;

/**
 * Porticus
 * ink.ptms.porticus.bungeeside.PorticusAPI
 *
 * @author bkm016
 * @since 2020/10/15 9:50 下午
 */
public class PorticusAPI extends API {

    @Override
    public APIType getType() {
        return APIType.SERVER;
    }

    @Override
    public Mission createMission() {
        return new MissionBungee();
    }

    @Override
    public Mission createMission(UUID uid) {
        return new MissionBungee(uid);
    }
}
