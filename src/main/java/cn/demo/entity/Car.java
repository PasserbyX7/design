package cn.demo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@TableName
@AllArgsConstructor
@NoArgsConstructor
public class Car {
    @TableId
    private Long id;
    private String name;
    private String license;
    private String img;
    private Integer status;
    private String desc;
    private Integer sort;
}
