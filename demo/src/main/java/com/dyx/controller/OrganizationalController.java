package com.dyx.controller;

import com.dyx.Result.Result;
import com.dyx.domain.vo.ParmaVo;
import com.dyx.service.OrganizationalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ding")
@Tag(name="通讯录")
public class OrganizationalController {

    @Autowired
    private OrganizationalService organizationalService;

    @GetMapping("/getDeptIdList")
    @Operation(summary = "部门id", description = "name。。。")
    public Result getDeptIdList() throws Exception {
        return organizationalService.getDeptIdList();
    }

    @GetMapping("/getParentDeptId")
    @Operation(summary = "父部门id", description = "name。。。")
    public Result getParentDeptId() throws Exception {
        return organizationalService.getParentDeptId();
    }

    @GetMapping("/createDept")
    @Operation(summary = "创建部门", description = "name。。。")
    public Result createDept() throws Exception {
        return organizationalService.createDept();
    }

    @GetMapping("/getDeptDetails")
    @Operation(summary = "获取部门详情", description = "name。。。")
    public Result getDeptDetails() throws Exception {
        return organizationalService.getDeptDetails();
    }

    @GetMapping("/getGroupMember")
    @Operation(summary = "获取群成员", description = "name。。。")
    public Result getGroupMember() throws Exception {
        return organizationalService.getGroupMember();
    }

    @GetMapping("/getStepCount")
    @Operation(summary = "获取运动步数", description = "name。。。")
    public Result getStepCount() throws Exception {
        return organizationalService.getStepCount();
    }

    @GetMapping("/isOpenStepCount")
    @Operation(summary = "是否开启运动步数", description = "name。。。")
    public Result isOpenStepCount() throws Exception {
        return organizationalService.isOpenStepCount();
    }

    @GetMapping("/getUserDetails")
    @Operation(summary = "获取用户详情", description = "name。。。")
    public Result getUserDetails() throws Exception {
        return organizationalService.getUserDetails();
    }



}
