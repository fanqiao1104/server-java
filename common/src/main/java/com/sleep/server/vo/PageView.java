package com.sleep.server.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangjing
 * @date 2020/7/6
 */
@Data
public class PageView<T> {
    @ApiModelProperty(value= "总页数")
    private long total;
    @ApiModelProperty(value= "返回数据列表")
    private List<T> items;

    public static <T> PageView<T> of(long total, List<T> result) {
        PageView<T> pageView = new PageView<>();
        pageView.setItems(result);
        pageView.setTotal(total);
        return pageView;
    }

    public static <T> PageView<T> empty() {
        return PageView.of(0, new ArrayList<>(0));
    }
}
