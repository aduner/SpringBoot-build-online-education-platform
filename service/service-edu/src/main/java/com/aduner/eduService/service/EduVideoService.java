package com.aduner.eduService.service;

import com.aduner.eduService.entity.EduVideo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author aduner
 * @since 2021-05-24
 */
public interface EduVideoService extends IService<EduVideo> {
    void removeVideoByCourseId(String courseId);
}
