package cn.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.demo.api.R;
import cn.demo.entity.Notice;
import cn.demo.service.NoticeService;

@RestController
@RequestMapping("/notices")
public class NoticeController {
    @Autowired
    private NoticeService noticeService;

    @GetMapping
    public R<List<Notice>> list() {
        return R.ok(noticeService.list());
    }

    @GetMapping("/{noticeId}")
    public R<Notice> getById(@PathVariable Long noticeId) {
        return R.ok(noticeService.getById(noticeId));
    }

    @PostMapping
    public R<Void> save(@RequestBody Notice notice) {
        noticeService.save(notice);
        return R.ok();
    }

    @PutMapping
    public R<Void> update(@RequestBody Notice notice) {
        noticeService.updateById(notice);
        return R.ok();
    }

    @DeleteMapping("/{noticeId}")
    public R<Void> remove(@PathVariable Long noticeId) {
        noticeService.removeById(noticeId);
        return R.ok();
    }
}
