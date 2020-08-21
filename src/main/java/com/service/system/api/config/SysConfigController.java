package com.service.system.api.config;

import com.google.common.collect.Lists;
import com.module.system.api.SysConfigApi;
import com.module.system.dto.SysConfigDto;
import com.module.system.entity.SysConfig;
import com.scottxuan.web.base.BaseController;
import com.scottxuan.web.result.ResultDto;
import com.service.system.service.config.SysConfigService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 系统配置表 前端控制器
 * </p>
 *
 * @author scottxuan
 */
@Api(tags = "10000--配置管理")
@RestController
@RequestMapping("${api}/system/config")
public class SysConfigController extends BaseController implements SysConfigApi {
    @Autowired
    private SysConfigService sysConfigService;

    @Override
    public ResultDto<List<SysConfigDto>> findByGroupCode(String groupCode) {
        List<SysConfig> configs = sysConfigService.findByGroupCode(groupCode);
        List<SysConfigDto> configDtos = configs.stream().map(var -> sysConfigService.convertToDto(var)).sorted(Comparator.comparing(SysConfigDto::getOrdinal)).collect(Collectors.toList());
        return getResultDto(configDtos);
    }

    @Override
    public ResultDto<SysConfigDto> findByCode(String code) {
        SysConfig config = sysConfigService.findByCode(code);
        return getResultDto(sysConfigService.convertToDto(config));
    }

    @Override
    public ResultDto<Integer> updateByDto(List<SysConfigDto> dtos) {
        return getResultDto(sysConfigService.updateByDto(dtos));
    }

    @Override
    public ResultDto<Boolean> clearCache() {
        return getResultDto(sysConfigService.clearCache());
    }
}
