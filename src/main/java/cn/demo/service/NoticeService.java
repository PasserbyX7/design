package cn.demo.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

import cn.demo.entity.Notice;

public interface NoticeService extends IService<Notice>{

    List<Notice> getSwiperImg();
}
