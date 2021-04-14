package cn.demo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import cn.demo.dao.MemberDao;
import cn.demo.dto.UserDTO;
import cn.demo.entity.Member;
import cn.demo.entity.MemberRoleRelation;
import cn.demo.entity.Role;
import cn.demo.exception.PhoneExistException;
import cn.demo.exception.UsernameExistException;
import cn.demo.service.MemberRoleRelationService;
import cn.demo.service.MemberService;
import cn.demo.service.RoleService;

@Service
public class MemberServiceImpl extends ServiceImpl<MemberDao, Member> implements MemberService {

    @Autowired
    private RoleService roleService;

    @Autowired
    private MemberRoleRelationService memberRoleRelationService;

    @Override
    public void register(UserDTO user) {
        checkUsernameUnique(user.getUsername());
        var member = user.convertToMember();
        var userRoleId = roleService.getOne(Wrappers.<Role>lambdaQuery().eq(Role::getEnname, "USER")).getId();
        save(member);
        var mrr = new MemberRoleRelation();
        mrr.setMemberId(member.getId());
        mrr.setRoleId(userRoleId);
        memberRoleRelationService.save(mrr);
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
