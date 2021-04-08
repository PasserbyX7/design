package cn.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.stereotype.Service;

import cn.demo.dao.CarDao;
import cn.demo.entity.Car;
import cn.demo.service.CarService;

@Service
public class CarServiceImpl extends ServiceImpl<CarDao, Car> implements CarService {
}
