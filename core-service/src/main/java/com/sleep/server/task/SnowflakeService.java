package com.sleep.server.task;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.sleep.server.task.base.IGlobalIdService;
import org.springframework.stereotype.Service;

/**
 * @author zhangjing
 * @date 2020/7/6
 */
@Service("snowflakeService")
public class SnowflakeService implements IGlobalIdService {
    private Snowflake snowflake;

    public SnowflakeService() {
        this.snowflake = IdUtil.createSnowflake(0, 0);
    }

    @Override
    public long nextId() {
        return snowflake.nextId();
    }
}
