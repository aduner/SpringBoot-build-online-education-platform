package com.aduner.eduService.controller;


import com.aduner.eduService.entity.EduTeacher;
import com.aduner.eduService.entity.vo.TeacherQuery;
import com.aduner.eduService.service.EduTeacherService;
import com.aduner.utils.Result;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
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


    @ApiOperation(value = "根据ID删除讲师")
    @DeleteMapping("{id}")
    public Result removeById(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable String id){
        boolean result = eduTeacherService.removeById(id);
        if(result){
            return Result.ok();
        }else{
            return Result.error().message("删除失败");
        }
    }

    @ApiOperation(value = "分页讲师列表")
    @PostMapping("{page}/{limit}")
    public Result pageQuery(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,
            @ApiParam(name = "teacherQuery", value = "查询对象", required = false)
            @RequestBody TeacherQuery teacherQuery) {

        Page<EduTeacher> pageParam = new Page<>(page, limit);
        eduTeacherService.pageQuery(pageParam, teacherQuery);
        Map<String, Object> data = new HashMap();
        List<EduTeacher> records = pageParam.getRecords();
        long current = pageParam.getCurrent();
        long pages = pageParam.getPages();
        data.put("currentPage", current);
        data.put("pages", pages);
        data.put("total", pageParam.getTotal());
        data.put("rows", records);
        return Result.ok().data(data);
    }

    @ApiOperation(value = "新增讲师")
    @PostMapping("/add")
    public Result save(
            @ApiParam(name = "teacher", value = "讲师对象", required = true)
            @RequestBody EduTeacher teacher) {
        if (eduTeacherService.save(teacher)) {
            return Result.ok();
        } else {
            return Result.error().message("保存失败");
        }
    }

    @ApiOperation(value = "根据ID查询讲师")
    @GetMapping("/find/{id}")
    public Result getById(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable String id) {
        EduTeacher teacher = eduTeacherService.getById(id);
        return Result.ok().data("item", teacher);
    }

    @ApiOperation(value = "更新讲师")
    @PostMapping("/update")
    public Result updateById(
            @ApiParam(name = "teacher", value = "讲师对象", required = true)
            @RequestBody EduTeacher teacher) {
        teacher.setGmtCreate(null);
        teacher.setGmtModified(null);
        teacher.setIsDeleted(null);


        if (eduTeacherService.updateById(teacher)) {
            return Result.ok();
        } else {
            return Result.error().message("更新失败");
        }
    }
}

