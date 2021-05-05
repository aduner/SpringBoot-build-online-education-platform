package com.aduner.eduService.controller;

import com.aduner.utils.Result;
import org.springframework.web.bind.annotation.*;

/**
 * @author Aduner
 */
@RestController
@RequestMapping("/eduService/user")
@CrossOrigin
public class EduLoginController {

    @PostMapping("login")
    public Result login() {
        return Result.ok().data("token", "admin");
    }

    @GetMapping("info")
    public Result info() {
        return Result.ok()
                .data("roles", "[admin]")
                .data("name", "admin")
                .data("avatar", "https://tva1.sinaimg.cn/large/008eGmZEly1gpj71xuatrj30om0om74e.jpg");
    }
}
