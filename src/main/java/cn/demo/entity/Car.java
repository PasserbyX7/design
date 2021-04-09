package cn.demo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import cn.demo.constant.CarStatusEnum;
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
    @TableField("`name`")
    private String name;
    private String license;
    private String img;
    @TableField("`status`")
    private CarStatusEnum status;
    @TableField("`desc`")
    private String desc;
    private Integer sort;
}
