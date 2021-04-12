package cn.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;

import cn.demo.entity.Car;

public interface CarService extends IService<Car> {

    void reserved(Long carId);
    void cancelReservation(Long carId);
}
