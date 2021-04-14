package cn.demo.dto;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import lombok.Data;

@Data
public abstract class PageQueryDTO<T> {
    /**
     * 每页显示条数，默认 10
     */
    private Long size = 10L;

    /**
     * 当前页，默认1
     */
    private Long current = 1L;

    public Page<T> page() {
        return new Page<T>(current, size);
    }
}