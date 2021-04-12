package cn.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.demo.api.R;
import cn.demo.entity.Car;
import cn.demo.service.CarService;

@RestController
@RequestMapping("/cars")
public class CarController {
    @Autowired
    private CarService carService;

    @PutMapping("/{carId}/actions/reserve")
    public R<Void> reserve(@PathVariable Long carId) {
        carService.reserved(carId);
        return R.ok();
    }

    @PutMapping("/{carId}/actions/cancel-reservation")
    public R<Void> cancelReservation(@PathVariable Long carId) {
        carService.cancelReservation(carId);
        return R.ok();
    }


    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping
    public R<List<Car>> list() {
        return R.ok(carService.list());
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/{carId}")
    public R<Car> getById(@PathVariable Long carId) {
        return R.ok(carService.getById(carId));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public R<Void> save(@RequestBody Car car) {
        carService.save(car);
        return R.ok();
    }

    @PutMapping
    public R<Void> update(@RequestBody Car car) {
        carService.updateById(car);
        return R.ok();
    }

    @DeleteMapping("/{carId}")
    public R<Void> remove(@PathVariable Long carId) {
        carService.removeById(carId);
        return R.ok();
    }
}
