package com.service.system.service.config.impl;

import com.module.system.entity.SysConfig;
import com.scottxuan.core.base.BaseMapper;
import com.scottxuan.core.base.BaseServiceImpl;

import com.service.system.mapper.config.SysConfigMapper;
import com.service.system.service.config.SysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统配置表 服务实现类
 * </p>
 *
 * @author scottxuan
 * @since 2020-08-18
 */
@Service
public class SysConfigServiceImpl extends BaseServiceImpl<SysConfig> implements SysConfigService {
    @Autowired
    private SysConfigMapper sysConfigMapper;

    @Override
    protected BaseMapper<SysConfig> getMapper() {
        return sysConfigMapper;
    }
}
