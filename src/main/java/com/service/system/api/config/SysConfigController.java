package com.service.system.api.config;

import com.scottxuan.web.base.BaseController;
import com.service.system.service.config.SysConfigService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 系统配置表 前端控制器
 * </p>
 *
 * @author scottxuan
 */
@Api(tags = "10000--系统配置管理")
@RestController
@RequestMapping("${api}/sysConfig")
public class SysConfigController extends BaseController {
    @Autowired
    private SysConfigService sysConfigService;

}
