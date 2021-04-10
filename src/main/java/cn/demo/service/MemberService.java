package cn.demo.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

import org.springframework.security.core.userdetails.UserDetails;

import cn.demo.dto.UserDTO;
import cn.demo.entity.Member;
import cn.demo.entity.Role;

public interface MemberService extends IService<Member>{
    void register(UserDTO user);
    UserDetails getUserDetails(String phone);
    List<Role> listRoleByUserId(Long memberId);
}