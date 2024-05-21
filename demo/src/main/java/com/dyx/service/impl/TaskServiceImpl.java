package com.dyx.service.impl;

import com.alibaba.fastjson.JSON;
import com.aliyun.dingtalktodo_1_0.models.CreateTodoTaskHeaders;
import com.aliyun.dingtalktodo_1_0.models.CreateTodoTaskRequest;
import com.aliyun.dingtalktodo_1_0.models.CreateTodoTaskResponse;
import com.aliyun.tea.TeaException;
import com.aliyun.teaopenapi.models.Config;
import com.aliyun.teautil.models.RuntimeOptions;
import com.dyx.Result.Result;
import com.dyx.service.TaskService;
import com.dyx.util.AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private AccessToken accessToken;


    @Override
    public Result createTask() throws Exception {
        com.aliyun.dingtalktodo_1_0.Client client = TaskServiceImpl.createClient();
        CreateTodoTaskHeaders createTodoTaskHeaders = new CreateTodoTaskHeaders();
        createTodoTaskHeaders.xAcsDingtalkAccessToken = accessToken.getAccessToken();

        CreateTodoTaskRequest.CreateTodoTaskRequestNotifyConfigs notifyConfigs = new CreateTodoTaskRequest.CreateTodoTaskRequestNotifyConfigs()
                .setDingNotify("1");
        CreateTodoTaskRequest.CreateTodoTaskRequestContentFieldList contentFieldList0 = new CreateTodoTaskRequest.CreateTodoTaskRequestContentFieldList();
        CreateTodoTaskRequest.CreateTodoTaskRequestDetailUrl detailUrl = new CreateTodoTaskRequest.CreateTodoTaskRequestDetailUrl()
                .setAppUrl("https://www.dingtalk.com")
                .setPcUrl("https://www.dingtalk.com");
        CreateTodoTaskRequest createTodoTaskRequest = new CreateTodoTaskRequest()
                .setOperatorId("GZXIcgIFwLVsYU56PjNEHAiEiE")
                .setSourceId("isv_dingtalkTodo1")
                .setSubject("test钉钉待办")
                .setCreatorId("GZXIcgIFwLVsYU56PjNEHAiEiE")
                .setDescription("应用可以调用该接口发起一个钉钉待办任务，该待办事项会出现在钉钉客户端“待办”页面，需要注意的是，通过开放接口发起的待办，目前仅支持直接跳转ISV应用详情页（ISV在调该接口时需传入自身应用详情页链接）。")
                .setDueTime(1715932106000L)
                .setExecutorIds(java.util.Arrays.asList(
                        "GZXIcgIFwLVsYU56PjNEHAiEiE"
                ))
                .setParticipantIds(java.util.Arrays.asList(
                        "GZXIcgIFwLVsYU56PjNEHAiEiE"
                ))
                .setDetailUrl(detailUrl)
                .setContentFieldList(java.util.Arrays.asList(
                        contentFieldList0
                ))
                .setIsOnlyShowExecutor(true)
                .setPriority(20)
                .setNotifyConfigs(notifyConfigs);
        try {
            System.out.printf("执行开始");
            CreateTodoTaskResponse response = client.createTodoTaskWithOptions("GZXIcgIFwLVsYU56PjNEHAiEiE", createTodoTaskRequest, createTodoTaskHeaders, new RuntimeOptions());
            System.out.printf("执行结束");
            System.out.printf(JSON.toJSONString(response.getBody()));
            return Result.success(response.getBody());
        } catch (TeaException err) {
            if (!com.aliyun.teautil.Common.empty(err.code) && !com.aliyun.teautil.Common.empty(err.message)) {
                // err 中含有 code 和 message 属性，可帮助开发定位问题
            }

        } catch (Exception _err) {
            TeaException err = new TeaException(_err.getMessage(), _err);
            if (!com.aliyun.teautil.Common.empty(err.code) && !com.aliyun.teautil.Common.empty(err.message)) {
                // err 中含有 code 和 message 属性，可帮助开发定位问题
            }

        }
        return null;
    }

    public static com.aliyun.dingtalktodo_1_0.Client createClient() throws Exception {
        Config config = new Config();
        config.protocol = "https";
        config.regionId = "central";
        return new com.aliyun.dingtalktodo_1_0.Client(config);
    }

}
