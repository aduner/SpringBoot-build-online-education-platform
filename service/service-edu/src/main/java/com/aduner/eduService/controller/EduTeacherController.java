package com.aduner.eduService.controller;


import com.aduner.eduService.entity.EduTeacher;
import com.aduner.eduService.service.EduTeacherService;
import com.aduner.utils.Result;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @ApiOperation(value = "所有讲师列表")
    @GetMapping("findAll")
    public Result findAllTeacher() {
        List<EduTeacher> list = eduTeacherService.list(null);
        return Result.ok().data("items", list);
    }

    @ApiOperation(value = "分页讲师列表")
    @GetMapping("{page}/{limit}")
    public Result pageList(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit){
        Page<EduTeacher> pageParam = new Page<>(page, limit);
        eduTeacherService.page(pageParam, null);
        Map<String,Object> data = new HashMap();
        long current=pageParam.getCurrent();
        long pages=pageParam.getPages();
        data.put("currentPage",current);
        data.put("pages",pages);
        data.put("total",pageParam.getTotal());
        data.put("rows",pageParam.getRecords());
        if(current<=pages)
            return Result.ok().data(data);
        else
            return Result.error().data(data);
    }

    @ApiOperation("根据ID删除讲师")
    @DeleteMapping("{id}")
    public Result removeById(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable String id) {
        eduTeacherService.removeById(id);
        return Result.ok();
    }

}

