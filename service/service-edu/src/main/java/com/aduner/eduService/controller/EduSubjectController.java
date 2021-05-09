package com.aduner.eduService.controller;


import com.aduner.eduService.entity.subject.OneSubject;
import com.aduner.eduService.service.EduSubjectService;
import com.aduner.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author aduner
 * @since 2021-05-07
 */
@RestController
@RequestMapping("/eduService/edu-subject")
@CrossOrigin
public class EduSubjectController {
    @Autowired
    private EduSubjectService subjectService;

    //添加课程分类
    //获取上传过来文件，把文件内容读取出来
    @PostMapping("addSubject")
    public Result addSubject(MultipartFile file) {
        //上传过来excel文件
        subjectService.saveSubject(file,subjectService);
        return Result.ok();
    }

    //课程分类列表（树形）
    @GetMapping("getAllSubject")
    public Result getAllSubject() {
        //list集合泛型是一级分类
        List<OneSubject> list = subjectService.getAllOneTwoSubject();
        return Result.ok().data("list",list);
    }
}

