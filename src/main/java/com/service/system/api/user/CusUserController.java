package com.service.system.api.user;

import com.module.system.api.CusUserApi;
import com.module.system.dto.CusUserRegisterDto;
import com.module.system.entity.CusUser;
import com.scottxuan.web.base.BaseController;
import com.scottxuan.web.result.ResultDto;
import com.service.system.service.user.CusUserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 客户账户 前端控制器
 * </p>
 *
 * @author scottxuan
 * @since 2020-09-04
 */
@Api(tags = "20001--账户管理(客户)")
@RestController
@RequestMapping(CusUserApi.MAPPING)
public class CusUserController extends BaseController implements CusUserApi {
    @Autowired
    private CusUserService cusUserService;

    @Override
    public ResultDto<CusUser> findById(Integer id) {
        return null;
    }

    @Override
    public ResultDto<CusUser> findByOpenId(Integer openId) {
        return null;
    }

    @Override
    public ResultDto<CusUser> findByAccount(String account) {
        return null;
    }

    @Override
    public ResultDto<Boolean> register(CusUserRegisterDto dto) {
        return null;
    }
}
