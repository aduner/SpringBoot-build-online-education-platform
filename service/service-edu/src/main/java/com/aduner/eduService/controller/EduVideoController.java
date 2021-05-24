package com.aduner.eduService.controller;


import com.aduner.eduService.entity.EduVideo;
import com.aduner.eduService.service.EduVideoService;
import com.aduner.utils.Result;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
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

    //添加小节
    @PostMapping("addVideo")
    public Result addVideo(@RequestBody EduVideo eduVideo) {
        videoService.save(eduVideo);
        return Result.ok();
    }

    //删除小节
    // TODO 后面这个方法需要完善：删除小节时候，同时把里面视频删除
    @DeleteMapping("{id}")
    public Result deleteVideo(@PathVariable String id) {
        videoService.removeById(id);
        return Result.ok();
    }
}

