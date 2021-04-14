package cn.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.demo.api.R;
import cn.demo.entity.Member;
import cn.demo.service.MemberService;

@RestController
@RequestMapping("/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public R<List<Member>> listWithRole() {
        return R.ok(memberService.listWithRole());
    }

    @GetMapping("/{MemberId}")
    public R<Member> getById(@PathVariable Long MemberId) {
        return R.ok(memberService.getById(MemberId));
    }

    @PostMapping
    public R<Void> save(@RequestBody Member member) {
        if (member.getPassword() != null)
            member.setPassword(passwordEncoder.encode(member.getPassword()));
        memberService.save(member);
        return R.ok();
    }

    @PutMapping
    public R<Void> update(@RequestBody Member member) {
        if (member.getPassword() != null)
            member.setPassword(passwordEncoder.encode(member.getPassword()));
        memberService.updateById(member);
        return R.ok();
    }

    @DeleteMapping("/{memberId}")
    public R<Void> remove(@PathVariable Long memberId) {
        memberService.removeById(memberId);
        return R.ok();
    }
}
