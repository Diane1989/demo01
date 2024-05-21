package com.dyx.controller;

import com.dyx.Result.Result;
import com.dyx.domain.vo.ParmaVo;
import com.dyx.service.OaApprovalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/oa")
@Tag(name="OA审批")
public class OaApprovalController {

    @Autowired
    private OaApprovalService oaApprovalService;

    @PostMapping("/getApprovalInstanceIdList")
    @Operation(summary = "获取审批实例ID列表", description = "name。。。")
    public Result getApprovalInstanceIdList() throws Exception {
        return oaApprovalService.getApprovalInstanceIdList();
    }

    @GetMapping("/getApprovalInstanceDetails")
    @Operation(summary = "获取审批详情", description = "name。。。")
    public Result getApprovalInstanceDetails() throws Exception {
        return oaApprovalService.getApprovalInstanceDetails();
    }

    @GetMapping("/printApprovalDetails")
    @Operation(summary = "打印审批详情", description = "name。。。")
    public void printApprovalDetails(HttpServletResponse response) throws Exception {
        oaApprovalService.printApprovalDetails(response);
    }


    @GetMapping("/printApprovalDetailsToPdf")
    @Operation(summary = "打印PDF", description = "name。。。")
    public void printApprovalDetailsToPdf(HttpServletResponse response) throws Exception {
        oaApprovalService.printApprovalDetailsToPdf(response);
    }



}
