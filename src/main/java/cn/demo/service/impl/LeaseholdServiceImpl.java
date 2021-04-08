package cn.demo.service.impl;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.demo.constant.CarStatusEnum;
import cn.demo.dao.LeaseholdDao;
import cn.demo.entity.Car;
import cn.demo.entity.Leasehold;
import cn.demo.service.CarService;
import cn.demo.service.LeaseholdService;

@Service
public class LeaseholdServiceImpl extends ServiceImpl<LeaseholdDao, Leasehold> implements LeaseholdService {

    @Autowired
    private CarService carService;

    @Override
    public void lend(Long userId, Long carId) {
        var leasehold = new Leasehold(null, userId, carId, LocalDateTime.now(), null);
        this.save(leasehold);
        carService.update(Wrappers.<Car>lambdaUpdate().eq(Car::getId, carId).set(Car::getStatus, CarStatusEnum.LEND));
    }

    @Override
    public void remand(Long leaseholdId) {
        var leasehold = this.getById(leaseholdId);
        leasehold.setRemandTime(LocalDateTime.now());
        updateById(leasehold);
        carService.update(Wrappers.<Car>lambdaUpdate().eq(Car::getId, leasehold.getCarId()).set(Car::getStatus,
                CarStatusEnum.NORMAL));
    }
}
