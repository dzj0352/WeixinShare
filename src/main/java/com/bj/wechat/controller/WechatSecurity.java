package com.bj.wechat.controller;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bj.wechat.common.MessageUtil;
import com.bj.wechat.common.OauthCode_GetUseInfo;
import com.bj.wechat.dispatcher.EventDispatcher;
import com.bj.wechat.dispatcher.MsgDispatcher;
import com.bj.wechat.util.SignUtil;
 
 
@Controller
@RequestMapping("/wechat")
public class WechatSecurity {
    private static Logger logger = Logger.getLogger(WechatSecurity.class);
 
    /**
     * 
     * @Description: 用于接收get参数，返回验证参数
     * @param @param request
     * @param @param response
     * @param @param signature
     * @param @param timestamp
     * @param @param nonce
     * @param @param echostr
     * @author dapengniao
     * @date 2016年3月4日 下午6:20:00
     */
    @RequestMapping(value = "security", method = RequestMethod.GET)
    public void doGet(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "signature", required = true) String signature,
            @RequestParam(value = "timestamp", required = true) String timestamp,
            @RequestParam(value = "nonce", required = true) String nonce,
            @RequestParam(value = "echostr", required = true) String echostr) {
        try {
        	System.out.println("=====================");
            if (SignUtil.checkSignature(signature, timestamp, nonce)) {
                PrintWriter out = response.getWriter();
                out.print(echostr);
                out.close();
            } else {
                logger.info("这里存在非法请求！");
            }
        } catch (Exception e) {
            logger.error(e, e);
        }
    }
 
    @RequestMapping(value = "security", method = RequestMethod.POST)
    // post方法用于接收微信服务端消息
    public void DoPost(HttpServletRequest request,HttpServletResponse response) {
        System.out.println("这是post方法！");
        try{
            Map<String, String> map=MessageUtil.parseXml(request);
            String msgtype=map.get("MsgType");
            if(MessageUtil.REQ_MESSAGE_TYPE_EVENT.equals(msgtype)){
                EventDispatcher.processEvent(map); //进入事件处理
            }else{
                MsgDispatcher.processMessage(map); //进入消息处理
            }
            System.out.println("============================="+map.get("Content"));
        }catch(Exception e){
            logger.error(e,e);
        }
    }
    @RequestMapping("weixinOauth")
    public void weiXinOauth(HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "code", required = true) String code,
            @RequestParam(value = "state", required = true) String state) {
        System.out.println("Code============="+code+"==========state=======" + state);
        try {
            // 用code取得微信用户的基本信息
            OauthCode_GetUseInfo weixin = new OauthCode_GetUseInfo(code);
            Map<String, String> wmap = weixin.getUserInfo();
            System.out.println("用户昵称================================="
                    + wmap.get("nickname"));
     
        } catch (Exception e) {
            logger.error(e.toString(), e);
        }
    }
}
