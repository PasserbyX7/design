package cn.demo.constant;

import com.baomidou.mybatisplus.core.enums.IEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CarStatusEnum implements IEnum<Integer> {
    NORMAL(0, "正常"), LEND(1, "正常"), MAINTAIN(2, "维修"), RESERVED(3, "已预定");

    private final int value;
    private final String msg;

    @Override
    public Integer getValue() {
        return value;
    }
}
