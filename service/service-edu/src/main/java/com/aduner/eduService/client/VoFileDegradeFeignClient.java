package com.aduner.eduService.client;

import com.aduner.utils.Result;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VoFileDegradeFeignClient implements VodClient{
    /**
     * 容错方法，删除单个视频失败后执行
     * @param id
     * @return
     */
    @Override
    public Result removeAlyVideo(String id) {
        return Result.error().message("删除视频出错！");
    }

    /**
     * 容错方法，删除多个视频失败后执行
     * @param videoIdList
     * @return
     */
    @Override
    public Result deleteBatch(List<String> videoIdList) {
        return Result.error().message("删除多个视频出错！");
    }
}
