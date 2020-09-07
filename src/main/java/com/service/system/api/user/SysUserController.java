package com.service.system.api.user;

import com.module.system.api.SysUserApi;
import com.module.system.entity.SysUser;
import com.scottxuan.web.base.BaseController;
import com.scottxuan.web.result.ResultDto;
import com.service.system.service.user.SysUserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 系统账户 前端控制器
 * </p>
 *
 * @author scottxuan
 * @since 2020-08-04
 */
@Api(tags = "20000--账户管理(系统)")
@RestController
@RequestMapping(SysUserApi.MAPPING)
public class SysUserController extends BaseController implements SysUserApi {
    @Autowired
    private SysUserService sysUserService;

    @Override
    public ResultDto<SysUser> findByAccount(String account) {
        return getResultDto(sysUserService.findByAccount(account));
    }

    @Override
    public ResultDto<SysUser> findById(Integer id) {
        return getResultDto(sysUserService.selectById(id));
    }
}
