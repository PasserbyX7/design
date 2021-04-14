package cn.demo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import cn.demo.dao.MemberDao;
import cn.demo.dto.UserDTO;
import cn.demo.entity.Member;
import cn.demo.entity.Role;
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
    public List<Member> listWithRole() {
        return list().stream().map(e -> e.setRoles(listRoleByUserId(e.getId()))).collect(Collectors.toList());
    }

    @Override
    public UserDetails getUserDetails(String username) {
        Member member = getOne(Wrappers.<Member>lambdaQuery().eq(Member::getUsername, username));
        // @formatter:off
        member.setAuthorities(
            listRoleByUserId(member.getId())
                .stream()
                .map(Role::getEnname)
                .map(e->"ROLE_"+e)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList())
        );
        // @formatter:on
        return member;
    }

    @Override
    public List<Role> listRoleByUserId(Long memberId) {
        return baseMapper.listRoleByUserId(memberId);
    }

    private void checkUsernameUnique(String username) throws UsernameExistException {
        if (count(Wrappers.<Member>lambdaQuery().eq(Member::getUsername, username)) != 0)
            throw new PhoneExistException();
    }

}
