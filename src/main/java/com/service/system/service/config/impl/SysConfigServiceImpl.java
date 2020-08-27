package com.service.system.service.config.impl;

import com.google.common.collect.Lists;
import com.module.common.constants.CacheConstant;
import com.module.system.dto.SysConfigDto;
import com.module.system.entity.SysConfig;
import com.module.system.enums.SysConfigTypeEnum;
import com.scottxuan.base.exception.Assert;
import com.scottxuan.base.pair.Pair;
import com.scottxuan.base.result.ResultBo;
import com.scottxuan.core.base.BaseMapper;
import com.scottxuan.core.base.BaseServiceImpl;

import com.scottxuan.core.redis.CacheService;
import com.scottxuan.core.utils.EntityUtils;
import com.service.system.mapper.config.SysConfigMapper;
import com.service.system.service.config.SysConfigService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
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
        if (StringUtils.isBlank(groupCode)) {
            return Lists.newArrayList();
        }
        if (CacheService.containKey(CacheConstant.CONFIG_GROUP_CODE + groupCode)) {
            return (List<SysConfig>) CacheService.get(CacheConstant.CONFIG_GROUP_CODE + groupCode);
        }
        SysConfig var1 = new SysConfig();
        var1.setGroupCode(groupCode);
        var1.setIsDeleted(Boolean.FALSE);
        List<SysConfig> configs = sysConfigMapper.select(var1);
        CacheService.set(CacheConstant.CONFIG_GROUP_CODE + groupCode,configs);
        return configs;
    }

    @Override
    public SysConfig findByCode(String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        }
        if (CacheService.containKey(CacheConstant.CONFIG_GROUP_CODE + code)) {
            return (SysConfig) CacheService.get(CacheConstant.CONFIG_GROUP_CODE + code);
        }
        SysConfig var1 = new SysConfig();
        var1.setCode(code);
        var1.setIsDeleted(Boolean.FALSE);
        SysConfig config = sysConfigMapper.selectOne(var1);
        CacheService.set(CacheConstant.CONFIG_GROUP_CODE + code,config);
        return config;
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
            List<Pair<String,String>> pairs = var1.stream().map(var -> new Pair<>(var.split(MARK2)[0], var.split(MARK2)[1])).collect(Collectors.toList());
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
    public ResultBo<Integer> updateByDto(List<SysConfigDto> dtos) {
        AtomicInteger count = new AtomicInteger(0);
        dtos.forEach(dto -> {
            List<String> selectValueList = dto.getSelectValueList();
            String value = selectValueList.isEmpty() ? "" : StringUtils.join(selectValueList, MARK1);
            SysConfig config = findByCode(dto.getCode());
            if (config != null) {
                config.setValue(value);
                sysConfigMapper.updateByPrimaryKeySelective(config);
                count.getAndIncrement();
            }
        });
        CacheService.clear(CacheConstant.CONFIG_GROUP_CODE);
        CacheService.clear(CacheConstant.CONFIG_CODE);
        return ResultBo.of(count.get());
    }

    @Override
    public ResultBo<Boolean> clearCache() {
        CacheService.clear(CacheConstant.CONFIG_GROUP_CODE);
        CacheService.clear(CacheConstant.CONFIG_CODE);
        return ResultBo.of(Boolean.TRUE);
    }

    @Override
    public ResultBo<String> findTextByCode(String code) {
        if (StringUtils.isNotBlank(code)) {
            SysConfig config = findByCode(code);
            if (config != null) {
                return ResultBo.of(config.getValue());
            }
        }
        return ResultBo.empty();
    }

    @Override
    public ResultBo<String> findSingleByCode(String code) {
        if (StringUtils.isNotBlank(code)) {
            SysConfig config = findByCode(code);
            if (config != null) {
                return ResultBo.of(config.getValue());
            }
        }
        return ResultBo.empty();
    }

    @Override
    public ResultBo<List<String>> findMultipleByCode(String code) {
        if (StringUtils.isNotBlank(code)) {
            SysConfig config = findByCode(code);
            if (config != null) {
                return ResultBo.of(Lists.newArrayList(config.getValue().split(MARK1)));
            }
        }
        return ResultBo.of(Lists.newArrayList());
    }

    @Override
    public ResultBo<Boolean> findBooleanByCode(String code) {
        if (StringUtils.isNotBlank(code)) {
            SysConfig config = findByCode(code);
            if (config != null) {
                return ResultBo.of("1".equals(config.getValue()));
            }
        }
        return ResultBo.of(Boolean.FALSE);
    }
}
