package com.aduner.eduService.controller;


import com.aduner.eduService.entity.EduCourse;
import com.aduner.eduService.entity.vo.CourseInfoVo;
import com.aduner.eduService.entity.vo.CoursePublishVo;
import com.aduner.eduService.service.EduCourseService;
import com.aduner.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @authoResult aduner
 * @since 2021-05-24
 */
@RestController
@RequestMapping("/eduService/edu-course")
@CrossOrigin
@Api(tags = "课程管理")
public class EduCourseController {
    @Autowired
    private EduCourseService courseService;

    @ApiOperation(value = "添加课程基本信息的方法")
    @PostMapping("addCourseInfo")
    public Result addCourseInfo(@RequestBody CourseInfoVo courseInfoVo) {
        //返回添加之后课程id，为了后面添加大纲使用
        String id = courseService.saveCourseInfo(courseInfoVo);
        return Result.ok().data("courseId",id);
    }

    @ApiOperation(value = "根据课程id查询课程基本信息")
    @GetMapping("getCourseInfo/{courseId}")
    public Result getCourseInfo(@PathVariable String courseId) {
        CourseInfoVo courseInfoVo = courseService.getCourseInfo(courseId);
        return Result.ok().data("courseInfoVo",courseInfoVo);
    }

    @ApiOperation(value = "修改课程信息")
    @PostMapping("updateCourseInfo")
    public Result updateCourseInfo(@RequestBody CourseInfoVo courseInfoVo) {
        courseService.updateCourseInfo(courseInfoVo);
        return Result.ok();
    }
    @ApiOperation(value = "根据课程id查询课程确认信息")
    @GetMapping("getPublishCourseInfo/{id}")
    public Result getPublishCourseInfo(@PathVariable String id) {
        CoursePublishVo coursePublishVo = courseService.publishCourseInfo(id);
        return Result.ok().data("publishCourse",coursePublishVo);
    }

    @ApiOperation(value = "课程最终发布")
    @PostMapping("publishCourse/{id}")
    public Result publishCourse(@PathVariable String id) {
        EduCourse eduCourse = new EduCourse();
        eduCourse.setId(id);
        eduCourse.setStatus("Normal");//设置课程发布状态
        courseService.updateById(eduCourse);
        return Result.ok();
    }

    @ApiOperation(value = "删除课程")
    @DeleteMapping("{courseId}")
    public Result deleteCourse(@PathVariable String courseId) {
        courseService.removeCourse(courseId);
        return Result.ok();
    }
}

