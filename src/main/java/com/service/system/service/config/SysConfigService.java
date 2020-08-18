package com.service.system.service.config;

import com.module.system.dto.SysConfigDto;
import com.module.system.entity.SysConfig;
import com.scottxuan.base.result.ResultBo;
import com.scottxuan.core.base.BaseService;

import java.util.List;

/**
 * <p>
 * 系统配置表 服务类
 * </p>
 *
 * @author scottxuan
 * @since 2020-08-18
 */
public interface SysConfigService extends BaseService<SysConfig> {

    /**
     * 根据groupCode查询
     * @param groupCode
     * @return
     */
    List<SysConfig> findByGroupCode(String groupCode);

    /**
     * 根据code查询
     * @param code
     * @return
     */
    SysConfig findByCode(String code);

    /**
     * config to dto
     * @param config
     * @return
     */
    SysConfigDto convertToDto(SysConfig config);

    /**
     * 更新
     * @param dtos
     * @return
     */
    ResultBo<Boolean> updateByDto(List<SysConfigDto> dtos);
}
