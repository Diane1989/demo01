package com.dyx.controller;

import com.dyx.Result.Result;
import com.dyx.service.FileTransferService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/file")
@Tag(name="文件传输")
public class FileTransferController {


    @Autowired
    private FileTransferService fileTransferService;


    //第一步
    @GetMapping("/getUploadingFilesDetails")
    @Operation(summary = "获取文件上传信息", description = "name。。。")
    public Result getUploadingFilesDetails() throws Exception {
        return fileTransferService.getUploadingFilesDetails();
    }

    @PutMapping("/uploadingFiles")
    @Operation(summary = "上传文件", description = "name。。。")
    public Result uploadingFiles() throws Exception {
        return fileTransferService.uploadingFiles();
    }

    @GetMapping("/commitFiles")
    @Operation(summary = "提交文件", description = "name。。。")
    public Result commitFiles() throws Exception {
        return fileTransferService.commitFiles();
    }


    @GetMapping("/getUuid")
    @Operation(summary = "获取id", description = "name。。。")
    public Result getUuid() throws Exception {
        return fileTransferService.getUuid();
    }



}
