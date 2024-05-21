package com.dyx.controller;


import com.dyx.Result.Result;
import com.dyx.service.JobNoticeService;
import com.dyx.domain.vo.ParmaVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notice")
@Tag(name="工作通知")
public class JobNoticeController {

    @Autowired
    private JobNoticeService jobNoticeService;

    @PostMapping("/submitMessage")
    @Operation(summary = "工作通知", description = "name。。。")
    public Result submitMessage(ParmaVo parmaVo) throws Exception {
        return jobNoticeService.submitMessage(parmaVo);
    }


}
