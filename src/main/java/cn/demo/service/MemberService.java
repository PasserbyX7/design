package cn.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;

import org.springframework.security.core.userdetails.UserDetails;

import cn.demo.dto.UserDTO;
import cn.demo.entity.Member;

public interface MemberService extends IService<Member>{
    void register(UserDTO user);
    String login(UserDTO user);
    UserDetails getUserDetails(String phone);
}