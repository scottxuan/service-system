package com.service.system.service.user.impl;

import com.scottxuan.base.result.ResultBo;
import com.scottxuan.core.base.BaseMapper;
import com.scottxuan.core.base.BaseServiceImpl;
import com.module.system.entity.SysUser;
import com.service.system.mapper.user.SysUserMapper;
import com.service.system.service.user.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统账户 服务实现类
 * </p>
 *
 * @author scottxuan
 * @since 2020-08-04
 */
@Service
public class SysUserServiceImpl extends BaseServiceImpl<SysUser> implements SysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    protected BaseMapper<SysUser> getMapper() {
        return sysUserMapper;
    }

    @Override
    public ResultBo<SysUser> findByAccount(String account) {
        SysUser user = new SysUser();
        user.setIsDeleted(Boolean.FALSE);
        user.setAccount(account);
        return ResultBo.of(sysUserMapper.selectOne(user));
    }

    @Override
    public ResultBo<SysUser> findByAccountContainsDelete(String account) {
        SysUser user = new SysUser();
        user.setAccount(account);
        return ResultBo.of(sysUserMapper.selectOne(user));
    }
}
