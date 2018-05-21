package com.bj.wechat.common;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.bj.web.util.GlobalConstants;
import com.bj.wechat.util.HttpUtils;

import net.sf.json.JSONObject;

 
public class WeChatTask {
    /**
     * @Description: 任务执行体
     * @param @throws Exception
     * @author dapengniao
     * @date 2016年3月10日 下午2:04:37
     */
    public void getToken_getTicket() throws Exception {
        Map<String, String> params = new HashMap<String, String>();
        params.put("grant_type", "client_credential");
        params.put("appid", GlobalConstants.getInterfaceUrl("appid"));
        params.put("secret", GlobalConstants.getInterfaceUrl("AppSecret"));
        String jstoken = HttpUtils.sendGet(
                GlobalConstants.getInterfaceUrl("tokenUrl"), params);
        System.out.println("===================="+jstoken);
        String access_token = JSONObject.fromObject(jstoken).getString(
                "access_token"); // 获取到token并赋值保存
        GlobalConstants.interfaceUrlProperties.put("access_token", access_token);
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"token为=============================="+access_token);
    }
 
}