package com.service.system.service.config;

import com.module.system.entity.SysConfigGroup;
import com.scottxuan.base.result.ResultBo;
import com.scottxuan.core.base.BaseService;

import java.util.List;

/**
 * <p>
 * 系统配置分组表 服务类
 * </p>
 *
 * @author scottxuan
 * @since 2020-08-18
 */
public interface SysConfigGroupService extends BaseService<SysConfigGroup> {

    ResultBo<SysConfigGroup> findByCode(String code);

    List<SysConfigGroup> findAll();
}
