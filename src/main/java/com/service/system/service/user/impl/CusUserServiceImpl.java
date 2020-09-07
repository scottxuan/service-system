package com.service.system.service.user.impl;

import com.scottxuan.core.base.BaseMapper;
import com.scottxuan.core.base.BaseServiceImpl;
import com.module.system.entity.CusUser;
import com.service.system.mapper.user.CusUserMapper;
import com.service.system.service.user.CusUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 客户账户 服务实现类
 * </p>
 *
 * @author scottxuan
 * @since 2020-09-04
 */
@Service
public class CusUserServiceImpl extends BaseServiceImpl<CusUser> implements CusUserService {
    @Autowired
    private CusUserMapper cusUserMapper;

    @Override
    protected BaseMapper<CusUser> getMapper() {
        return cusUserMapper;
    }
}
