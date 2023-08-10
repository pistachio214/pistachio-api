package com.pistachio.common.utils.ip;

import com.pistachio.common.config.PistachioConfig;
import com.pistachio.common.constant.Constants;
import com.pistachio.common.utils.GsonUtil;
import com.pistachio.common.utils.StringUtil;
import com.pistachio.common.utils.http.HttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.configurationprocessor.json.JSONObject;

/**
 * 获取地址类
 *
 * @author ruoyi
 */

/**
 * @author: Pengsy
 * @date: 2023/08/01 17:03
 * @description: 获取地址类
 */
public class AddressUtil {
    private static final Logger log = LoggerFactory.getLogger(AddressUtil.class);

    // IP地址查询
    public static final String IP_URL = "http://whois.pconline.com.cn/ipJson.jsp";

    // 未知地址
    public static final String UNKNOWN = "XX XX";

    public static String getRealAddressByIP(String ip) {
        // 内网不查询
        if (IpUtil.internalIp(ip)) {
            return "内网IP";
        }
        if (PistachioConfig.isAddressEnabled()) {
            try {
                String rspStr = HttpUtil.sendGet(IP_URL, "ip=" + ip + "&json=true", Constants.GBK);
                if (StringUtil.isEmpty(rspStr)) {
                    log.error("获取地理位置异常 {}", ip);
                    return UNKNOWN;
                }
                JSONObject obj = GsonUtil.parseObject(rspStr, JSONObject.class);
                String region = obj.getString("pro");
                String city = obj.getString("city");
                return String.format("%s %s", region, city);
            } catch (Exception e) {
                log.error("获取地理位置异常 {}", ip);
            }
        }
        return UNKNOWN;
    }
}
