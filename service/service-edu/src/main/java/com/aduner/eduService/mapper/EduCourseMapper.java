package com.aduner.eduService.mapper;

import com.aduner.eduService.entity.EduCourse;
import com.aduner.eduService.entity.vo.CoursePublishVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author aduner
 * @since 2021-05-24
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {

    CoursePublishVo getPublishCourseInfo(String id);
}
