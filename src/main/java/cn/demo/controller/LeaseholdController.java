package cn.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.demo.api.R;
import cn.demo.entity.Leasehold;
import cn.demo.service.LeaseholdService;

@RestController
@RequestMapping("/leaseholds")
public class LeaseholdController {
    @Autowired
    private LeaseholdService leaseholdService;

    @PostMapping("/users/{userId}/cars/{carId}/actions/lend")
    public R<Void> lend(@PathVariable Long userId, @PathVariable Long carId) {
        leaseholdService.lend(userId, carId);
        return R.ok();
    }

    @PostMapping("/{leaseholdId}/actions/remand")
    public R<Void> remand(@PathVariable Long leaseholdId) {
        leaseholdService.remand(leaseholdId);
        return R.ok();
    }

    @GetMapping
    public R<List<Leasehold>> list() {
        return R.ok(leaseholdService.list());
    }
}
