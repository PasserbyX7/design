package cn.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;

import cn.demo.entity.Leasehold;

public interface LeaseholdService extends IService<Leasehold>{
    void lend(Long userId,Long carId);
    void remand(Long leaseholdId);
}

