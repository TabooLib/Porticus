package ink.ptms.porticus.api;

import java.util.UUID;

/**
 * Porticus
 * ink.ptms.porticus.api.API
 * <p>
 * Porticus API 抽象类
 *
 * @author bkm016
 * @since 2020/10/15 9:45 下午
 */
public abstract class API {

    /**
     * 获取 API 类型
     *
     * @return {@link APIType}
     */
    public abstract APIType getType();

    /**
     * 新建一个通讯任务
     *
     * @return {@link Mission}
     */
    public abstract Mission createMission();

    public abstract Mission createMission(UUID uid);
}
