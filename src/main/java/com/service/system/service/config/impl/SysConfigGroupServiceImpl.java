package com.service.system.service.config.impl;

import com.module.system.entity.SysConfigGroup;
import com.scottxuan.core.base.BaseMapper;
import com.scottxuan.core.base.BaseServiceImpl;

import com.service.system.mapper.config.SysConfigGroupMapper;
import com.service.system.service.config.SysConfigGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
