package com.aduner.vod.controller;

import com.aduner.servicebase.handler.ExceptionHandler.AdunerException;
import com.aduner.utils.Result;
import com.aduner.vod.Utils.ConstantVodUtils;
import com.aduner.vod.Utils.InitVodCilent;
import com.aduner.vod.service.VodService;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/eduVod")
@CrossOrigin
@Api(tags = "视频点播")
public class VodController {

    @Autowired
    private VodService vodService;

    @PostMapping("uploadAlyiVideo")
    @ApiOperation(value = "上传视频到阿里云")
    public Result uploadAlyiVideo(MultipartFile file) {
        //返回上传视频id
        String videoId = vodService.uploadVideoAly(file);
        return Result.ok().data("videoId",videoId);
    }

    @DeleteMapping("removeAlyVideo/{id}")
    @ApiOperation(value = "根据视频id删除阿里云视频")
    public Result removeAlyVideo(@PathVariable String id) {
        try {
            //初始化对象
            DefaultAcsClient client = InitVodCilent.initVodClient(ConstantVodUtils.ACCESS_KEY_ID, ConstantVodUtils.ACCESS_KEY_SECRET);
            //创建删除视频request对象
            DeleteVideoRequest request = new DeleteVideoRequest();
            //向request设置视频id
            request.setVideoIds(id);
            //调用初始化对象的方法实现删除
            client.getAcsResponse(request);
            return Result.ok();
        }catch(Exception e) {
            e.printStackTrace();
            throw new AdunerException(20001,"删除视频失败");
        }
    }

    @DeleteMapping("delete-batch")
    @ApiOperation(value = "删除多个阿里云视频")
    public Result deleteBatch(@RequestParam("videoIdList") List<String> videoIdList){
        vodService.removeMoreAlyVideo(videoIdList);
        return Result.ok();
    }
}
