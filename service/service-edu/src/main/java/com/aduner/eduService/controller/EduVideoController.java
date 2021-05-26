package com.aduner.eduService.controller;


import com.aduner.eduService.client.VodClient;
import com.aduner.eduService.entity.EduVideo;
import com.aduner.eduService.service.EduVideoService;
import com.aduner.servicebase.handler.ExceptionHandler.AdunerException;
import com.aduner.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author aduner
 * @since 2021-05-24
 */
@RestController
@RequestMapping("/eduService/edu-video")
@CrossOrigin
@Api(tags = "课程小节管理")
public class EduVideoController {
    @Autowired
    private EduVideoService videoService;
    @Autowired
    private VodClient vodClient;

    @PostMapping("addVideo")
    @ApiOperation(value = "添加小节")
    public Result addVideo(@RequestBody EduVideo eduVideo) {
        videoService.save(eduVideo);
        return Result.ok();
    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "删除小节，删除对应阿里云视频")
    public Result deleteVideo(@PathVariable String id) {
        //根据小节id获取视频id，调用方法实现视频删除
        EduVideo eduVideo = videoService.getById(id);
        String videoSourceId = eduVideo.getVideoSourceId();
        //判断小节里面是否有视频id
        if (!StringUtils.isEmpty(videoSourceId)) {
            //根据视频id，远程调用实现视频删除
            Result result = vodClient.removeAlyVideo(videoSourceId);
            if (result.getCode() == 20001) {
                throw new AdunerException(20001, "删除视频失败，熔断...");
            }
        }
        //删除小节
        videoService.removeById(id);
        return Result.ok();
    }

    @ApiOperation(value = "根据id查询小节信息")
    @PostMapping("getVideoInfo/{videoId}")
    public Result getVideoInfo(@PathVariable String videoId) {
        EduVideo eduVideo = videoService.getById(videoId);
        return Result.ok().data("eVideo", eduVideo);
    }

    @ApiOperation(value = "修改小节")
    @PostMapping("updateVideo")
    public Result updateVideo(@RequestBody EduVideo eduVideo) {
        videoService.updateById(eduVideo);
        return Result.ok();
    }
}

