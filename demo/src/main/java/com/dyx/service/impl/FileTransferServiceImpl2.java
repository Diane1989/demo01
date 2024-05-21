package com.dyx.service.impl;

import com.alibaba.fastjson.JSON;
import com.aliyun.dingtalkstorage_1_0.models.CommitFileResponse;
import com.aliyun.dingtalkstorage_1_0.models.GetFileUploadInfoResponse;
import com.aliyun.dingtalkstorage_2_0.models.SearchDentriesResponse;
import com.aliyun.tea.TeaException;
import com.dyx.Result.Result;
import com.dyx.service.FileTransferService;
import com.dyx.service.FileTransferService2;
import com.dyx.util.AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Service
public class FileTransferServiceImpl2 implements FileTransferService2 {

    @Autowired
    private AccessToken accessToken;

    @Override
    public Result getUploadingFilesDetails() throws Exception {
        com.aliyun.dingtalkstorage_1_0.Client client = FileTransferServiceImpl2.createClient();
        com.aliyun.dingtalkstorage_1_0.models.GetFileUploadInfoHeaders getFileUploadInfoHeaders = new com.aliyun.dingtalkstorage_1_0.models.GetFileUploadInfoHeaders();
        getFileUploadInfoHeaders.xAcsDingtalkAccessToken = accessToken.getAccessToken();
        com.aliyun.dingtalkstorage_1_0.models.GetFileUploadInfoRequest.GetFileUploadInfoRequestOptionPreCheckParam optionPreCheckParam = new com.aliyun.dingtalkstorage_1_0.models.GetFileUploadInfoRequest.GetFileUploadInfoRequestOptionPreCheckParam()
                .setMd5("md5")
                .setSize(512L)
//                .setParentId("0")
                .setName("李漩测试");
        com.aliyun.dingtalkstorage_1_0.models.GetFileUploadInfoRequest.GetFileUploadInfoRequestOption option = new com.aliyun.dingtalkstorage_1_0.models.GetFileUploadInfoRequest.GetFileUploadInfoRequestOption()
                .setStorageDriver("DINGTALK")
                .setPreCheckParam(optionPreCheckParam)
                .setPreferRegion("ZHANGJIAKOU")
                .setPreferIntranet(true);
        com.aliyun.dingtalkstorage_1_0.models.GetFileUploadInfoRequest getFileUploadInfoRequest = new com.aliyun.dingtalkstorage_1_0.models.GetFileUploadInfoRequest()
                .setUnionId("GZXIcgIFwLVsYU56PjNEHAiEiE")
                .setProtocol("HEADER_SIGNATURE")
                .setMultipart(false)
                .setOption(option);
        try {
            GetFileUploadInfoResponse response = client.getFileUploadInfoWithOptions("24058095253", getFileUploadInfoRequest, getFileUploadInfoHeaders, new com.aliyun.teautil.models.RuntimeOptions());
            System.out.printf(JSON.toJSONString(response.getBody()));
            return Result.success(JSON.toJSONString(response.getBody()));
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
        return Result.error("获取文件上传信息失败");
    }

    @Override
    public Result uploadingFiles() throws Exception {
        // 从接口返回信息中拿到resourceUrls
        String resourceUrl = "https://zjk-dualstack.trans.dingtalk.com/yundisk0/iAEHAqRmaWxlA6h5dW5kaXNrMATOIT1zNwXNGxcGzOEHzmYl-q0IzQKV.file";
        // 从接口返回信息中拿到headers
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "OSS LTAIjmWpzHta71rc:1AR8l2c2DahGji1kVughCkaMyjw=");
        URL url = new URL(resourceUrl);
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        if (headers != null) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                connection.setRequestProperty(entry.getKey(), entry.getValue());
            }
        }
        connection.setDoOutput(true);
        connection.setRequestMethod("PUT");
        connection.setUseCaches(false);
        connection.setReadTimeout(10000);
        connection.setConnectTimeout(10000);
        connection.connect();
        OutputStream out = connection.getOutputStream();
        InputStream is = new FileInputStream(new File("/Users/diane/Desktop/11.xlsx"));
        byte[] b =new byte[1024];
        int temp;
        while ((temp=is.read(b))!=-1){
            out.write(b,0,temp);
        }
        out.flush();
        out.close();
        int responseCode = connection.getResponseCode();
        connection.disconnect();
        if (responseCode == 200) {
            System.out.println("上传成功");
            return Result.success("上传成功");
        } else {
            System.out.println("上传失败");
            return Result.error("上传失败");
        }
    }

    @Override
    public Result commitFiles() throws Exception {
        com.aliyun.dingtalkstorage_1_0.Client client = FileTransferServiceImpl2.createClient();
        com.aliyun.dingtalkstorage_1_0.models.CommitFileHeaders commitFileHeaders = new com.aliyun.dingtalkstorage_1_0.models.CommitFileHeaders();
        commitFileHeaders.xAcsDingtalkAccessToken = accessToken.getAccessToken();
        com.aliyun.dingtalkstorage_1_0.models.CommitFileRequest.CommitFileRequestOptionAppProperties optionAppProperties0 = new com.aliyun.dingtalkstorage_1_0.models.CommitFileRequest.CommitFileRequestOptionAppProperties()
                .setName("AA")
                .setValue("AA")
                .setVisibility("PUBLIC");
        com.aliyun.dingtalkstorage_1_0.models.CommitFileRequest.CommitFileRequestOption option = new com.aliyun.dingtalkstorage_1_0.models.CommitFileRequest.CommitFileRequestOption()
                .setSize(1024L)
                .setConflictStrategy("AUTO_RENAME")
                .setAppProperties(java.util.Arrays.asList(
                        optionAppProperties0
                ));
        com.aliyun.dingtalkstorage_1_0.models.CommitFileRequest commitFileRequest = new com.aliyun.dingtalkstorage_1_0.models.CommitFileRequest()
                .setUnionId("GZXIcgIFwLVsYU56PjNEHAiEiE")
                .setUploadKey("hgHPAAAABZn5ZpUCzwAAACAOBdp7AwEEoTcF2gA4I2lBRUhBcVJtYVd4bEE2aDVkVzVrYVhOck1BVE9JUVE4TndYTkhCOEdFd2ZPWmlZSHJnak5qWTAGqERJTkdUQUxL")
                .setName("11.xlsx")
                .setParentId("137666250450")
                .setOption(option);
        try {
            CommitFileResponse response = client.commitFileWithOptions("24058095253", commitFileRequest, commitFileHeaders, new com.aliyun.teautil.models.RuntimeOptions());
            System.out.printf(JSON.toJSONString(response.getBody()));
            return Result.success(JSON.toJSONString(response.getBody()));
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
        return Result.error("提交文件失败");
    }


    @Override
    public Result getUuid() throws Exception {
        com.aliyun.dingtalkstorage_2_0.Client client = FileTransferServiceImpl2.createClient2();
        com.aliyun.dingtalkstorage_2_0.models.SearchDentriesHeaders searchDentriesHeaders = new com.aliyun.dingtalkstorage_2_0.models.SearchDentriesHeaders();
        searchDentriesHeaders.xAcsDingtalkAccessToken = accessToken.getAccessToken();
        com.aliyun.dingtalkstorage_2_0.models.SearchDentriesRequest.SearchDentriesRequestOptionVisitTimeRange optionVisitTimeRange = new com.aliyun.dingtalkstorage_2_0.models.SearchDentriesRequest.SearchDentriesRequestOptionVisitTimeRange();
        com.aliyun.dingtalkstorage_2_0.models.SearchDentriesRequest.SearchDentriesRequestOptionCreateTimeRange optionCreateTimeRange = new com.aliyun.dingtalkstorage_2_0.models.SearchDentriesRequest.SearchDentriesRequestOptionCreateTimeRange();
        com.aliyun.dingtalkstorage_2_0.models.SearchDentriesRequest.SearchDentriesRequestOption option = new com.aliyun.dingtalkstorage_2_0.models.SearchDentriesRequest.SearchDentriesRequestOption()
//                .setNextToken("next_token")
                .setMaxResults(20)
                .setDentryCategories(java.util.Arrays.asList(
                        "category"
                ))
                .setCreatorIds(java.util.Arrays.asList(
                        "creator_id"
                ))
                .setModifierIds(java.util.Arrays.asList(
                        "modifier_id"
                ))
                .setCreateTimeRange(optionCreateTimeRange)
                .setVisitTimeRange(optionVisitTimeRange);
        com.aliyun.dingtalkstorage_2_0.models.SearchDentriesRequest searchDentriesRequest = new com.aliyun.dingtalkstorage_2_0.models.SearchDentriesRequest()
                .setOperatorId("020217234422848283")
//                .setKeyword("keyword")
                .setOption(option);
        try {
            SearchDentriesResponse response = client.searchDentriesWithOptions(searchDentriesRequest, searchDentriesHeaders, new com.aliyun.teautil.models.RuntimeOptions());
            System.out.printf(response.getBody().toString());
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
        return Result.success();
    }


    public static com.aliyun.dingtalkstorage_1_0.Client createClient() throws Exception {
        com.aliyun.teaopenapi.models.Config config = new com.aliyun.teaopenapi.models.Config();
        config.protocol = "https";
        config.regionId = "central";
        return new com.aliyun.dingtalkstorage_1_0.Client(config);
    }

    public static com.aliyun.dingtalkstorage_2_0.Client createClient2() throws Exception {
        com.aliyun.teaopenapi.models.Config config = new com.aliyun.teaopenapi.models.Config();
        config.protocol = "https";
        config.regionId = "central";
        return new com.aliyun.dingtalkstorage_2_0.Client(config);
    }

}
