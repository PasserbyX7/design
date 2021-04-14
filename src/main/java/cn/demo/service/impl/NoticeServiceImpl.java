package cn.demo.service.impl;

import java.util.List;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.stereotype.Service;

import cn.demo.dao.NoticeDao;
import cn.demo.entity.Notice;
import cn.demo.service.NoticeService;

@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeDao, Notice> implements NoticeService {

    @Override
    public List<Notice> getSwiperImg() {
        return page(new Page<Notice>(1, 3)).getRecords();
    }
}
