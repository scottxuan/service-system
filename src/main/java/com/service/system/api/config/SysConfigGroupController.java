package com.service.system.api.config;

import com.google.common.collect.Lists;
import com.module.system.api.SysConfigGroupApi;
import com.module.system.entity.SysConfigGroup;
import com.scottxuan.web.base.BaseController;
import com.scottxuan.web.result.ResultDto;
import com.service.system.service.config.SysConfigGroupService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 系统配置分组表 前端控制器
 * </p>
 *
 * @author scottxuan
 * @since 2020-08-18
 */
@Api(tags = "10001--系统配置分组管理")
@RestController
public class SysConfigGroupController extends BaseController implements SysConfigGroupApi {
    @Autowired
    private SysConfigGroupService sysConfigGroupService;

    @Override
    public ResultDto<SysConfigGroup> findByCode(String code) {
        SysConfigGroup group = new SysConfigGroup();
        group.setCode(code);
        group.setIsDeleted(Boolean.FALSE);
        return getResultDto(sysConfigGroupService.selectOne(group));
    }

    @Override
    public ResultDto<List<SysConfigGroup>> findAll() {
        SysConfigGroup group = new SysConfigGroup();
        group.setIsDeleted(Boolean.FALSE);
        List<SysConfigGroup> groups = sysConfigGroupService.select(group);
        if (groups.isEmpty()) {
            return getResultDto(Lists.newArrayList());
        }
        return getResultDto(groups.stream().sorted(Comparator.comparing(SysConfigGroup::getOrdinal)).collect(Collectors.toList()));
    }
}
