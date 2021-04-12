package cn.demo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import cn.demo.dto.CarPageQueryDTO;
import cn.demo.entity.Car;

public interface CarService extends IService<Car> {

    void reserved(Long carId);
    void cancelReservation(Long carId);
    Page<Car> queryPage(CarPageQueryDTO carPageQueryDTO);
}
