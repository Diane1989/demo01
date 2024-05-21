package com.dyx.controller;

import com.dyx.Result.Result;
import com.dyx.service.OaApprovalService;
import com.dyx.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/task")
@Tag(name="代办")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/createTask")
    @Operation(summary = "创建代办", description = "name。。。")
    public Result createTask() throws Exception {
        return taskService.createTask();
    }



}
