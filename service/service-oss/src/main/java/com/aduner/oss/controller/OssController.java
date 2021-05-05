package com.aduner.oss.controller;

import com.aduner.oss.serivce.OssService;
import com.aduner.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Aduner
 */
@Api(tags="阿里云文件管理")
@CrossOrigin
@RestController
@RequestMapping("/eduOss")
public class OssController {
    @Autowired
    private OssService ossService;
    /**
     * 文件上传
     * @param file
     */
    @ApiOperation(value = "文件上传")
    @PostMapping("/upload")
    public Result upload(
            @ApiParam(name = "file", value = "文件", required = true)
            @RequestParam("file") MultipartFile file) {
        String uploadUrl = ossService.uploadFileAvatar(file);
        return Result.ok().message("文件上传成功").data("url", uploadUrl);
    }
}