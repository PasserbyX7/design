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
public class Role {
    @TableId
    private Long id;
    private String name;
    private String enname;
    private String description;
}
