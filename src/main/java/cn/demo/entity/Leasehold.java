package cn.demo.entity;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@TableName
@AllArgsConstructor
@NoArgsConstructor
public class Leasehold {
    @TableId
    private Long id;
    private Long userId;
    private Long carId;
    private LocalDateTime lendTime;
    private LocalDateTime remandTime;
}
