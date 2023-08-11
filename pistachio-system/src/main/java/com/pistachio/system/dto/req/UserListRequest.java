package com.pistachio.system.dto.req;

import com.pistachio.system.dto.ReqPageDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @description: user list dto
 * @date: 2023/08/09 16:05
 * @author: Pengsy
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserListRequest extends ReqPageDto {

    /**
     * 管理员账号
     *
     * @mock admin
     */
    private String username;
}
