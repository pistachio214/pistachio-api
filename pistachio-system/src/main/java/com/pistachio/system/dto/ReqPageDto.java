package com.pistachio.system.dto;

import com.pistachio.common.constant.Constants;
import com.pistachio.common.utils.StringUtil;
import lombok.Data;

/**
 * @author: Pengsy
 * @date: 2023/08/04 16:57
 * @description: 作用于分页处理的基类
 */
@Data
public class ReqPageDto {

    //
    /**
     * 页码
     *
     * @mock 1
     * @since 页面默认为 第1页
     */
    private Integer current;

    //条数
    /**
     * 每页条数
     *
     * @mock 15
     * @since 页面默认 15 条数据
     */
    private Integer size;

    public Integer getCurrent() {
        if (StringUtil.isNull(current)) {
            return Constants.DEFAULT_CURRENT - 1;
        }
        return current;
    }

    public Integer getSize() {
        if (StringUtil.isNull(size)) {
            return Constants.DEFAULT_SIZE;
        }
        return size;
    }
}
