package cn.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.demo.api.R;
import cn.demo.dto.UserDTO;
import cn.demo.service.MemberService;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private MemberService memberService;

    @PostMapping
    public R<String> login(@RequestBody UserDTO user) {
        return R.ok(memberService.login(user));
    }

}
