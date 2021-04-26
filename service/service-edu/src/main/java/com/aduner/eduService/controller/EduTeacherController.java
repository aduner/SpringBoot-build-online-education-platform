package com.aduner.eduService.controller;


import com.aduner.eduService.entity.EduTeacher;
import com.aduner.eduService.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author aduner
 * @since 2021-04-25
 */
@RestController
@RequestMapping("/eduService/edu-teacher")
@CrossOrigin
@Api(tags = "讲师管理")
public class EduTeacherController {
    @Autowired
    private EduTeacherService eduTeacherService;

    @ApiOperation(value = "所有讲师")
    @GetMapping("findAll")
    public List<EduTeacher> findAllTeacher(){
        List<EduTeacher> list = eduTeacherService.list(null);
        return list;
    }

    @ApiOperation("根据ID删除讲师")
    @DeleteMapping("{id}")
    public boolean removeById(@PathVariable String id){
        return eduTeacherService.removeById(id);
    }
}

