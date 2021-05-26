package com.aduner.eduService.controller;

import com.aduner.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * @author Aduner
 */
@RestController
@RequestMapping("/eduService/user")
@CrossOrigin
@Api(tags = "登录")
public class EduLoginController {

    @ApiOperation(value = "登录")
    @PostMapping("login")
    public Result login() {
        return Result.ok().data("token", "admin");
    }

    @ApiOperation(value = "回显")
    @GetMapping("info")
    public Result info() {
        return Result.ok()
                .data("roles", "[admin]")
                .data("name", "admin")
                .data("avatar", "https://tva1.sinaimg.cn/large/008eGmZEly1gpj71xuatrj30om0om74e.jpg");
    }
}
