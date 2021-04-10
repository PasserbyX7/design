package cn.demo.dao;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import org.apache.ibatis.annotations.Select;

import cn.demo.entity.Member;
import cn.demo.entity.Role;

public interface MemberDao extends BaseMapper<Member> {
    @Select("""
    SELECT
        r.*
    FROM
        `member` AS m
        LEFT JOIN `member_role_relation` AS mrr ON m.id = mrr.member_id
        LEFT JOIN `role` AS r ON r.id = mrr.role_id
    WHERE
        m.id = #{memberId}
    """)
    List<Role> listRoleByUserId(Long memberId);
}
