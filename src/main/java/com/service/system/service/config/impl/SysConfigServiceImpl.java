package com.service.system.service.config.impl;

import com.google.common.collect.Lists;
import com.module.system.dto.SysConfigDto;
import com.module.system.entity.SysConfig;
import com.module.system.enums.SysConfigTypeEnum;
import com.scottxuan.base.pair.CodeNamePair;
import com.scottxuan.base.result.ResultBo;
import com.scottxuan.core.base.BaseMapper;
import com.scottxuan.core.base.BaseServiceImpl;

import com.scottxuan.core.utils.EntityUtils;
import com.service.system.mapper.config.SysConfigMapper;
import com.service.system.service.config.SysConfigService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 系统配置表 服务实现类
 * </p>
 *
 * @author scottxuan
 * @since 2020-08-18
 */
@Service
public class SysConfigServiceImpl extends BaseServiceImpl<SysConfig> implements SysConfigService {

    private final static String MARK1 = ";";
    private final static String MARK2 = "#";

    @Autowired
    private SysConfigMapper sysConfigMapper;

    @Override
    protected BaseMapper<SysConfig> getMapper() {
        return sysConfigMapper;
    }

    @Override
    public List<SysConfig> findByGroupCode(String groupCode) {
        SysConfig var1 = new SysConfig();
        var1.setGroupCode(groupCode);
        var1.setIsDeleted(Boolean.FALSE);
        List<SysConfig> configs = sysConfigMapper.select(var1);
        return configs.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public SysConfig findByCode(String code) {
        SysConfig var1 = new SysConfig();
        var1.setCode(code);
        var1.setIsDeleted(Boolean.FALSE);
        SysConfig config = sysConfigMapper.selectOne(var1);
        return convertToDto(config);
    }

    @Override
    public SysConfigDto convertToDto(SysConfig config) {
        if (config == null) {
            return null;
        }
        SysConfigDto dto = null;
        if (SysConfigTypeEnum.TEXT.type == config.getType()
                || SysConfigTypeEnum.SINGLE.type == config.getType()
                || SysConfigTypeEnum.BOOLEAN.type == config.getType()) {
            dto = new SysConfigDto();
            EntityUtils.copyPropertiesIgnoreNull(config, dto);
            dto.setSelectValueList(Lists.newArrayList(config.getValue()));
        }
        if (SysConfigTypeEnum.SINGLE.type == config.getType()
                || SysConfigTypeEnum.MULTIPLE.type == config.getType()) {
            if (dto == null) {
                dto = new SysConfigDto();
                EntityUtils.copyPropertiesIgnoreNull(config, dto);
            }
            List<String> var1 = Lists.newArrayList(config.getAllValue().split(MARK1));
            List<CodeNamePair> pairs = var1.stream().map(var -> new CodeNamePair(var.split(MARK2)[0], var.split(MARK2)[1])).collect(Collectors.toList());
            dto.setValueList(pairs);
        }
        if (SysConfigTypeEnum.MULTIPLE.type == config.getType()) {
            if (dto == null) {
                dto = new SysConfigDto();
                EntityUtils.copyPropertiesIgnoreNull(config, dto);
            }
            dto.setSelectValueList(Lists.newArrayList(config.getValue().split(MARK1)));
        }
        return dto;
    }

    @Override
    public ResultBo<Boolean> updateByDto(List<SysConfigDto> dtos) {
        dtos.forEach(dto -> {
            List<String> selectValueList = dto.getSelectValueList();
            String value = selectValueList.isEmpty() ? "" : StringUtils.join(selectValueList, MARK1);
            SysConfig config = findByCode(dto.getCode());
            if (config != null) {
                config.setValue(value);
                sysConfigMapper.updateByPrimaryKeySelective(config);
            }
        });
        return ResultBo.of(Boolean.TRUE);
    }
}
