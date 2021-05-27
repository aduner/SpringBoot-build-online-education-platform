package com.aduner.eduCms.controller;


import com.aduner.eduCms.entity.CrmBanner;
import com.aduner.eduCms.service.CrmBannerService;
import com.aduner.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 前台bannber显示
 * </p>
 *
 * @author aduner
 * @since 2021-05-27
 */
@RestController
@RequestMapping("/eduCms/bannerFront")
@CrossOrigin
@Api(tags = "前台bannber显示")
public class BannerFrontController {

    @Autowired
    private CrmBannerService bannerService;

    @ApiOperation(value = "查询所有banner")
    @GetMapping("getAllBanner")
    public Result getAllBanner() {
        List<CrmBanner> list = bannerService.selectAllBanner();
        return Result.ok().data("list",list);
    }
}

