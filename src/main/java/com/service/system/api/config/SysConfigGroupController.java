package com.service.system.api.config;

import com.module.system.api.SysConfigGroupApi;
import com.module.system.entity.SysConfigGroup;
import com.scottxuan.web.base.BaseController;
import com.scottxuan.web.result.ResultDto;
import com.service.system.service.config.SysConfigGroupService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 系统配置分组表 前端控制器
 * </p>
 *
 * @author scottxuan
 * @since 2020-08-18
 */
@Api(tags = "10001--分组管理")
@RestController
@RequestMapping(SysConfigGroupApi.MAPPING)
public class SysConfigGroupController extends BaseController implements SysConfigGroupApi {
    @Autowired
    private SysConfigGroupService sysConfigGroupService;

    @Override
    public ResultDto<SysConfigGroup> findByCode(String code) {
        return getResultDto(sysConfigGroupService.findByCode(code));
    }

    @Override
    public ResultDto<List<SysConfigGroup>> findAll() {
        return getResultDto(sysConfigGroupService.findAll());
    }
}
