package com.pistachio.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author: Pengsy
 * @date: 2023/08/03 10:56
 * @description: 登录设备端枚举
 */
@Getter
@AllArgsConstructor
public enum LoginDeviceEnum {
    /**
     * pc端
     */
    COMPUTER_TERMINAL("PC"),

    /**
     * 安卓手机
     */
    ANDROID_MOBILE("Android"),

    /**
     * IOS
     */
    IOS_MOBILE("IOS"),

    /**
     * 微信小程序
     */
    WX_APP("WECHAT_APP"),

    /**
     * H%
     */
    WAP_TERMIAL("H5");

    private final String device;
}
