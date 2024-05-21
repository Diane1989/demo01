package com.dyx.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
@Tag(name="测试")
public class TestController {

    @PostMapping("/login")
    public String login() {
        System.out.println("登录成功");
        return "登录成功";
    }

    // 测试登录  ---- http://localhost:8081/acc/doLogin?name=zhang&pwd=123456
    @PostMapping("doLogin")
    @Operation(summary = "登录", description = "name, pwd")
    public SaResult doLogin(String name, String pwd) {
        // 此处仅作模拟示例，真实项目需要从数据库中查询数据进行比对
        if("zhang".equals(name) && "123456".equals(pwd)) {
            StpUtil.login(10001);
            return SaResult.ok("登录成功");
        }
        return SaResult.error("登录失败");
    }

    // 查询登录状态  ---- http://localhost:8081/acc/isLogin
    @PostMapping("isLogin")
    @Operation(summary = "是否登录", description = "name, pwd")
    public SaResult isLogin() {
        return SaResult.ok("是否登录：" + StpUtil.isLogin());
    }

    // 查询 Token 信息  ---- http://localhost:8081/acc/tokenInfo
    @PostMapping("tokenInfo")
    @Operation(summary = "查token", description = "name, pwd")
    public SaResult tokenInfo() {
        return SaResult.data(StpUtil.getTokenInfo());
    }

    // 测试注销  ---- http://localhost:8081/acc/logout
    @PostMapping("logout")
    @Operation(summary = "退出", description = "name, pwd")
    public SaResult logout() {
        StpUtil.logout();
        return SaResult.ok();
    }

}
