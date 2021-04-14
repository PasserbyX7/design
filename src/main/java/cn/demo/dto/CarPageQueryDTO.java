package cn.demo.dto;

import java.io.Serializable;

import cn.demo.entity.Car;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class CarPageQueryDTO extends PageQueryDTO<Car> implements Serializable{
    private static final long serialVersionUID = -4707348042845021508L;
    /**
     * 查询关键字：车辆名模糊匹配
     */
    private String key;
}
