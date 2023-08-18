package com.pistachio.system.dto.vo;

import io.swagger.v3.oas.annotations.media.Schema;
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

    @Schema(description = "管理员名称", defaultValue = "萧十一郎")
    private String nickname;

    @Schema(description = "管理员头像", defaultValue = "https://www.xxxxx.png")
    private String avatar;
}
