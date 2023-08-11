package com.pistachio.system.dto.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @description:
 * @date: 2023/08/11 15:30
 * @author: Pengsy
 */
@Data
@AllArgsConstructor
public class NavUserVo {

    /**
     * 昵称
     * @mock 萧十一郎
     */
    private String nickname;

    /**
     * 头像
     * @mock https://www.xxxxx.png
     */
    private String avatar;
}
