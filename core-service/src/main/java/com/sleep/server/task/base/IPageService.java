package com.sleep.server.task.base;

import com.sleep.server.dao.IExample;
import com.sleep.server.dao.IMapper;
import com.sleep.server.util.StringUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangjing
 * @date 2020/7/9
 */
public interface IPageService<T> {
    default Pair<Long, List<T>> executePage(IExample example, IMapper<T> mapper, Integer index, Integer limit, String orderClause) {
        long count = mapper.countByExample(example);
        if (count == 0) {
            return Pair.of(0L, new ArrayList<>(0));
        }

        example.setOffset((index -1) * limit);
        example.setLimit(limit);
        if (StringUtils.notEmpty(orderClause)){
            example.setOrderByClause(orderClause);
        }

        return Pair.of(count, mapper.selectByExample(example));
    }

    default Pair<Long, List<T>> executePage(IExample example, IMapper<T> mapper, Integer index, Integer limit) {
        return executePage(example, mapper, index, limit, "");
    }
}
