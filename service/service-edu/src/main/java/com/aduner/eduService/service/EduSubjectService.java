package com.aduner.eduService.service;

import com.aduner.eduService.entity.EduSubject;
import com.aduner.eduService.entity.subject.OneSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author aduner
 * @since 2021-05-07
 */
public interface EduSubjectService extends IService<EduSubject> {

    void saveSubject(MultipartFile file, EduSubjectService subjectService);
    List<OneSubject> getAllOneTwoSubject();
}
