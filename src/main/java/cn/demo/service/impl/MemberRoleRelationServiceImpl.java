package cn.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.stereotype.Service;

import cn.demo.dao.MemberRoleRelationDao;
import cn.demo.entity.MemberRoleRelation;
import cn.demo.service.MemberRoleRelationService;

@Service
public class MemberRoleRelationServiceImpl extends ServiceImpl<MemberRoleRelationDao, MemberRoleRelation>
        implements MemberRoleRelationService {
}
