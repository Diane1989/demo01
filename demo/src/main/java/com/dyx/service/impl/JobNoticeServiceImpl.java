package com.dyx.service.impl;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiMessageCorpconversationAsyncsendV2Request;
import com.dingtalk.api.response.OapiMessageCorpconversationAsyncsendV2Response;
import com.dyx.Result.Result;
import com.dyx.service.JobNoticeService;
import com.dyx.util.AccessToken;
import com.dyx.domain.vo.ParmaVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class JobNoticeServiceImpl implements JobNoticeService {

    @Autowired
    private AccessToken accessToken;

    @Override
    public Result submitMessage(ParmaVo parmaVo) throws Exception {


        // 留言提交
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/message/corpconversation/asyncsend_v2");
        OapiMessageCorpconversationAsyncsendV2Request request = new OapiMessageCorpconversationAsyncsendV2Request();
        request.setAgentId(2863036127L);
        request.setUseridList("020217234422848283");
        request.setToAllUser(false);

        OapiMessageCorpconversationAsyncsendV2Request.Msg msg = new OapiMessageCorpconversationAsyncsendV2Request.Msg();
        msg.setMsgtype("markdown");
        msg.setMarkdown(new OapiMessageCorpconversationAsyncsendV2Request.Markdown());
        // 获取当前时间
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String date = now.format(formatter);
        msg.getMarkdown().setTitle("发送留言");
        msg.getMarkdown().setText(date);

        request.setMsg(msg);
        OapiMessageCorpconversationAsyncsendV2Response rsp = client.execute(request, accessToken.getAccessToken());
        return Result.success(rsp);
    }

}
