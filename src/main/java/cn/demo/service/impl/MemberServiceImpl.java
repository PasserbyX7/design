package cn.demo.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import cn.demo.dao.MemberDao;
import cn.demo.dto.UserDTO;
import cn.demo.entity.Member;
import cn.demo.exception.PhoneExistException;
import cn.demo.service.MemberService;

@Service
public class MemberServiceImpl extends ServiceImpl<MemberDao, Member> implements MemberService {

    @Override
    public void register(UserDTO user) {
        checkPhoneUnique(user.getPhone());
        save(user.convertToMember());
    }

    @Override
    public UserDetails getUserDetails(String phone) {
        Member member = getOne(Wrappers.<Member>lambdaQuery().eq(Member::getPhone, phone));
        member.setAuthorities(null);// TODO 用户权限待查出
        return member;
    }

    private void checkPhoneUnique(String phone) throws PhoneExistException {
        if (count(Wrappers.<Member>lambdaQuery().eq(Member::getPhone, phone)) != 0)
            throw new PhoneExistException();
    }

}
