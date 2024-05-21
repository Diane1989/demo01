package com.dyx.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.dingtalkim_1_0.models.BatchQueryGroupMemberHeaders;
import com.aliyun.dingtalkim_1_0.models.BatchQueryGroupMemberRequest;
import com.aliyun.dingtalkim_1_0.models.BatchQueryGroupMemberResponse;
import com.aliyun.tea.TeaException;
import com.aliyun.teaopenapi.models.Config;
import com.aliyun.teautil.models.RuntimeOptions;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.*;
import com.dingtalk.api.response.*;
import com.dyx.Result.Result;
import com.dyx.service.OrganizationalService;
import com.dyx.util.AccessToken;
import com.taobao.api.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrganizationalServiceImpl implements OrganizationalService {

    @Autowired
    private AccessToken accessToken;

    @Override
    public Result getDeptIdList() throws Exception {
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/v2/department/listsub");
        OapiV2DepartmentListsubRequest req = new OapiV2DepartmentListsubRequest();
        req.setDeptId(876741356L);
        req.setLanguage("zh_CN");
        OapiV2DepartmentListsubResponse rsp = client.execute(req, accessToken.getAccessToken());
        System.out.println(rsp.getBody());
        return Result.success(JSON.toJSON(rsp.getBody()));
    }

    @Override
    public Result getParentDeptId() throws Exception {
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/v2/department/listparentbydept");
        OapiV2DepartmentListparentbydeptRequest req = new OapiV2DepartmentListparentbydeptRequest();
        req.setDeptId(925865029L);
        OapiV2DepartmentListparentbydeptResponse rsp = client.execute(req, accessToken.getAccessToken());
        System.out.println(rsp.getBody());
        return Result.success(JSON.toJSON(rsp.getBody()));
    }

    @Override
    public Result createDept() throws Exception {
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/v2/department/create");
        OapiV2DepartmentCreateRequest req = new OapiV2DepartmentCreateRequest();
        req.setParentId(1L);
        req.setOuterDept(true);
        req.setHideDept(false);
        req.setCreateDeptGroup(true);
        req.setOrder(1L);
        req.setName("HR");
        req.setSourceIdentifier("HR部门");
        req.setDeptPermits("3,4,5");
        req.setUserPermits("100,200");
        req.setOuterPermitUsers("500,600");
        req.setOuterPermitDepts("6,7,8");
        req.setOuterDeptOnlySelf(false);
        req.setSourceIdentifier("gs");
        OapiV2DepartmentCreateResponse rsp = client.execute(req, accessToken.getAccessToken());
        System.out.println(rsp.getBody());
        return Result.success(JSON.toJSON(rsp.getBody()));
    }

    @Override
    public Result getDeptDetails() throws Exception {
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/v2/department/get");
        OapiV2DepartmentGetRequest req = new OapiV2DepartmentGetRequest();
        req.setDeptId(925527239L);
        req.setLanguage("zh_CN");
        OapiV2DepartmentGetResponse rsp = client.execute(req, accessToken.getAccessToken());
        System.out.println(rsp.getBody());
        return Result.success(JSON.toJSON(rsp.getBody()));
    }

    @Override
    public Result getGroupMember() throws Exception {
        com.aliyun.dingtalkim_1_0.Client client = OrganizationalServiceImpl.createClient();
        BatchQueryGroupMemberHeaders batchQueryGroupMemberHeaders = new BatchQueryGroupMemberHeaders();
        batchQueryGroupMemberHeaders.xAcsDingtalkAccessToken = accessToken.getAccessToken();
        BatchQueryGroupMemberRequest batchQueryGroupMemberRequest = new BatchQueryGroupMemberRequest()
                .setOpenConversationId("cid6vhepDS5zxB+CXupymZgnw==")
//                .setCoolAppCode("COOLAPP_XXXXX")
                .setMaxResults(1000L);
        try {
            BatchQueryGroupMemberResponse response = client.batchQueryGroupMemberWithOptions(batchQueryGroupMemberRequest, batchQueryGroupMemberHeaders, new RuntimeOptions());
//            System.out.printf(JSON.toJSONString(response.getBody()));

            JSONObject responseBody = JSON.parseObject(JSON.toJSONString(response.getBody()));
            // 获取成员用户列表
            JSONArray memberUserIds = responseBody.getJSONArray("memberUserIds");
            List<String> falseUserIds = new ArrayList<>();
            for (int i = 0; i < memberUserIds.size(); i++) {
                String userId = memberUserIds.getString(i);
                boolean result = isOpenStepCount(userId);
                if (!result) {
                    getUserDetails(userId);
                    falseUserIds.add(userId);
                }
            }
//            System.out.println(falseUserIds);
            return Result.success(JSON.toJSON(response.getBody()));
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

    @Override
    public Result getStepCount() throws Exception {
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/health/stepinfo/listbyuserid");
        OapiHealthStepinfoListbyuseridRequest req = new OapiHealthStepinfoListbyuseridRequest();
        req.setUserids("66000193");
        req.setStatDate("20240505");
        OapiHealthStepinfoListbyuseridResponse rsp = client.execute(req, accessToken.getAccessToken());
        System.out.println(rsp.getBody());
        return Result.success(rsp.getBody());
    }

    public Result getUserDetails(String userId) throws Exception {
        try {
            DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/v2/user/get");
            OapiV2UserGetRequest req = new OapiV2UserGetRequest();
            req.setUserid(userId);
            req.setLanguage("zh_CN");
            OapiV2UserGetResponse rsp = client.execute(req, accessToken.getAccessToken());

            JSONObject responseBody = JSONObject.parseObject(rsp.getBody());

//            System.out.printf(String.valueOf(responseBody));
            JSONObject result = responseBody.getJSONObject("result");
            String name = result.getString("name");
            JSONArray deptIdList = result.getJSONArray("dept_id_list");

            System.out.println("Name: " + name);
            System.out.println("Dept ID List: " + deptIdList);

//            for (int i = 0; i < deptIdList.size(); i++) {
//                long deptId = deptIdList.getLong(i);
//                System.out.println("Dept ID: " + deptId);
//            }
            return Result.success();
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean isOpenStepCount(String userId) throws Exception {
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/health/stepinfo/getuserstatus");
        OapiHealthStepinfoGetuserstatusRequest req = new OapiHealthStepinfoGetuserstatusRequest();
        req.setUserid(userId);
        OapiHealthStepinfoGetuserstatusResponse rsp = client.execute(req, accessToken.getAccessToken());
        JSONObject responseBody = JSON.parseObject(rsp.getBody());
        boolean statusValue = responseBody.getBoolean("status");
        return statusValue;
    }

    @Override
    public Result isOpenStepCount() throws Exception {
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/health/stepinfo/getuserstatus");
        OapiHealthStepinfoGetuserstatusRequest req = new OapiHealthStepinfoGetuserstatusRequest();
        req.setUserid("66000193");
        OapiHealthStepinfoGetuserstatusResponse rsp = client.execute(req, accessToken.getAccessToken());
        System.out.println(rsp.getBody());
        return Result.success(rsp.getBody());
    }

    @Override
    public Result getUserDetails() throws Exception {
        try {
            DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/v2/user/get");
            OapiV2UserGetRequest req = new OapiV2UserGetRequest();
            req.setUserid("102119566437722657");
            req.setLanguage("zh_CN");
            OapiV2UserGetResponse rsp = client.execute(req, accessToken.getAccessToken());
            System.out.println(rsp.getBody());
            return Result.success();
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static com.aliyun.dingtalkim_1_0.Client createClient() throws Exception {
        Config config = new Config();
        config.protocol = "https";
        config.regionId = "central";
        return new com.aliyun.dingtalkim_1_0.Client(config);
    }


}
