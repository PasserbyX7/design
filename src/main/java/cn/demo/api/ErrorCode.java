package cn.demo.api;

import java.util.stream.Stream;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    // @formatter:off
    SUCCESS("00000","执行成功"),
    USER_ERROR("A0001","用户端错误"),
    SYS_ERROR("B0001","系统执行错误");
    // @formatter:on

    private final String code;
    private final String msg;

    @Override
    public String toString() {
        return String.format(" ErrorCode:{code=%s, msg=%s} ", code, msg);
    }

    /**
     * 将code转换为ErrorCode，如果该code未定义，则返回SUCCESS
     *
     * @param code
     * @return: ErrorCode
     * @Date: 2020-05-02 05:35:25
     */
    public static ErrorCode fromCode(String code) {
        // @formatter:off
        return Stream.of(ErrorCode.values())
                                .filter(e->e.code.equals(code))
                                .findFirst()
                                .orElse(SUCCESS);
        // @formatter:on
    }

}