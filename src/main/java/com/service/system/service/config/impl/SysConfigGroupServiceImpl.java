package com.service.system.service.config.impl;

import com.google.common.collect.Lists;
import com.module.system.entity.SysConfigGroup;
import com.scottxuan.base.result.ResultBo;
import com.scottxuan.core.base.BaseMapper;
import com.scottxuan.core.base.BaseServiceImpl;

import com.service.system.mapper.config.SysConfigGroupMapper;
import com.service.system.service.config.SysConfigGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 系统配置分组表 服务实现类
 * </p>
 *
 * @author scottxuan
 * @since 2020-08-18
 */
@Service
public class SysConfigGroupServiceImpl extends BaseServiceImpl<SysConfigGroup> implements SysConfigGroupService {
    @Autowired
    private SysConfigGroupMapper sysConfigGroupMapper;

    @Override
    protected BaseMapper<SysConfigGroup> getMapper() {
        return sysConfigGroupMapper;
    }

    @Override
    public ResultBo<SysConfigGroup> findByCode(String code) {
        SysConfigGroup group = new SysConfigGroup();
        group.setCode(code);
        group.setIsDeleted(Boolean.FALSE);
        return ResultBo.of(sysConfigGroupMapper.selectOne(group));
    }

    @Override
    public List<SysConfigGroup> findAll() {
        SysConfigGroup group = new SysConfigGroup();
        group.setIsDeleted(Boolean.FALSE);
        List<SysConfigGroup> groups = sysConfigGroupMapper.select(group);
        if (groups.isEmpty()) {
            return Lists.newArrayList();
        }
        return groups.stream().sorted(Comparator.comparing(SysConfigGroup::getOrdinal)).collect(Collectors.toList());
    }
}
