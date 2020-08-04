package com.service.user.api;

import com.scottxuan.web.base.BaseController;
import com.service.user.service.SysUserService;
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
@Api(tags = "系统账户")
@RestController
@RequestMapping("${api}/sysUser")
public class SysUserController extends BaseController {
    @Autowired
    private SysUserService sysUserService;

}
