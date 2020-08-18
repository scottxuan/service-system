package com.service.system.service.user;

import com.scottxuan.base.result.ResultBo;
import com.scottxuan.core.base.BaseService;
import com.module.system.entity.SysUser;

/**
 * <p>
 * 系统账户 服务类
 * </p>
 *
 * @author scottxuan
 * @since 2020-08-04
 */
public interface SysUserService extends BaseService<SysUser> {
    /**
     * 根据账户查询用户
     * @param account
     * @return
     */
    ResultBo<SysUser> findByAccount(String account);

    /**
     * 根据账户查询用户
     * @param account
     * @return
     */
    ResultBo<SysUser> findByAccountContainsDelete(String account);
}
