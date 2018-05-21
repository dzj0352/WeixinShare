package com.bj.wechat.common;

import java.util.HashMap;

import com.bj.web.util.GlobalConstants;
import com.bj.wechat.util.HttpUtils;

import net.sf.json.JSONObject;

public class OauthCode_GetUseInfo {
	private String openid;
    private String access_token;
    private String code;
    private String unionid;
    private HashMap<String, String> params = new HashMap<String, String>();
    public OauthCode_GetUseInfo(String code) {
        this.code = code;
        params.put("appid", GlobalConstants.getInterfaceUrl("appid"));
        params.put("secret", GlobalConstants.getInterfaceUrl("AppSecret"));
    }
 
    /**
     *
     * @param @return hashmap {subscribe是否关注 0没有关注，1关注 unionid openid nickname昵称
     *        sex性别 province省份 city城市 headimgurl图像地址}
     * @param @throws Exception
     * @author dapengniao
     * @date  2016年4月26日 上午9:54:55
     */
    public HashMap<String, String> getUserInfo() throws Exception {
        // 将用户信息获取拼装成map
        // 通过code获取access_token,openid,unionid
        params.put("code", code);
        params.put("grant_type", "authorization_code");
        String tokenrs = HttpUtils.sendGet(
                GlobalConstants.getInterfaceUrl("OauthCodeUrl"), params);
        System.out.println("tokenrs======================"+tokenrs);
        access_token = JSONObject.fromObject(tokenrs).getString("access_token");
        openid = JSONObject.fromObject(tokenrs).getString("openid");
        unionid = JSONObject.fromObject(tokenrs).getString("unionid");
        // 通过用户openid信息获取用户详细信息
        params.clear();
        params.put("access_token", access_token);
        params.put("openid", openid);
        params.put("lang", "zh_CN");
        String useinfors = HttpUtils.sendGet(
                GlobalConstants.getInterfaceUrl("OauthInfoUrl"), params);
        // 通过用户的openid判断用户是否关注公众账号
        params.clear();
 
        params.put("access_token", GlobalConstants.getInterfaceUrl("access_token")
                );
        params.put("openid", openid);
        params.put("lang", "zh_CN");
        String subscribers = "";
        subscribers = HttpUtils.sendGet(
                GlobalConstants.getInterfaceUrl("SubscribeUrl"), params);
 
        // 将用户信息获取拼装成map
         
        System.out.println(subscribers);
            params.clear();
        params.put("subscribe",
                    JSONObject.fromObject(subscribers).getString("subscribe"));
        params.put("unionid", unionid);
        params.put("openid", openid);
        params.put("nickname",
                JSONObject.fromObject(useinfors).getString("nickname"));
        params.put("sex", JSONObject.fromObject(useinfors).getString("sex"));
        params.put("province",
                JSONObject.fromObject(useinfors).getString("province"));
        params.put("city", JSONObject.fromObject(useinfors).getString("city"));
        params.put("headimgurl",
                JSONObject.fromObject(useinfors).getString("headimgurl"));
         
         
        return params;
    }
 
    /**
     * @Description: 通过openid获取用户信息
     * @param @param openid
     * @param @return
     * @param @throws Exception   
     * @author dapengniao
     * @date 2016年4月26日 上午9:53:40
     */
     
    public static HashMap<String, String> Openid_userinfo(String openid)
            throws Exception {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("access_token",
                GlobalConstants.getInterfaceUrl("access_token"));
        params.put("openid", openid);
        params.put("lang", "zh_CN");
        String subscribers = HttpUtils.sendGet(
                GlobalConstants.getInterfaceUrl("SubscribeUrl"), params);
        params.clear();
        params.put("nickname",
                JSONObject.fromObject(subscribers).getString("nickname"));
        params.put("headimgurl",
                JSONObject.fromObject(subscribers).getString("headimgurl"));
        params.put("sex", JSONObject.fromObject(subscribers).getString("sex"));
        return params;
    }
 
 
    @SuppressWarnings("unused")
    private static String create_timestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }
}
