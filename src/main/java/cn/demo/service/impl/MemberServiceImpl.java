package cn.demo.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import cn.demo.dao.MemberDao;
import cn.demo.dto.UserDTO;
import cn.demo.entity.Member;
import cn.demo.exception.PhoneExistException;
import cn.demo.exception.UsernameExistException;
import cn.demo.service.MemberService;

@Service
public class MemberServiceImpl extends ServiceImpl<MemberDao, Member> implements MemberService {

    @Override
    public void register(UserDTO user) {
        checkUsernameUnique(user.getUsername());
        save(user.convertToMember());
    }

    @Override
    public UserDetails getUserDetails(String username) {
        Member member = getOne(Wrappers.<Member>lambdaQuery().eq(Member::getUsername, username));
        member.setAuthorities(null);// TODO 用户权限待查出
        return member;
    }

    private void checkUsernameUnique(String username) throws UsernameExistException {
        if (count(Wrappers.<Member>lambdaQuery().eq(Member::getUsername, username)) != 0)
            throw new PhoneExistException();
    }

}
