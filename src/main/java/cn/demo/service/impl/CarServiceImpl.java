package cn.demo.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.stereotype.Service;

import cn.demo.constant.CarStatusEnum;
import cn.demo.dao.CarDao;
import cn.demo.dto.CarPageQueryDTO;
import cn.demo.entity.Car;
import cn.demo.service.CarService;

@Service
public class CarServiceImpl extends ServiceImpl<CarDao, Car> implements CarService {

    @Override
    public void reserved(Long carId) {
        update(Wrappers.<Car>lambdaUpdate().eq(Car::getId, carId).set(Car::getStatus, CarStatusEnum.RESERVED));
    }

    @Override
    public void cancelReservation(Long carId) {
        update(Wrappers.<Car>lambdaUpdate().eq(Car::getId, carId).set(Car::getStatus, CarStatusEnum.NORMAL));
    }

    @Override
    public Page<Car> queryPage(CarPageQueryDTO carPageQueryDTO) {
        return page(carPageQueryDTO.page(), Wrappers.<Car>lambdaQuery().like(Car::getName, carPageQueryDTO.getKey()));
    }
}
