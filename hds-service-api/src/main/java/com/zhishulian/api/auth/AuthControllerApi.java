package com.zhishulian.api.auth;

import com.zhishulian.framework.domain.ucenter.ext.XcUserExt;
import com.zhishulian.framework.domain.ucenter.request.LoginRequest;
import com.zhishulian.framework.domain.ucenter.response.JwtResult;
import com.zhishulian.framework.domain.ucenter.response.LoginResult;
import com.zhishulian.framework.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Administrator.
 */
@Api(value = "用户认证",description = "用户认证接口")
public interface AuthControllerApi {
    @ApiOperation("登录")
    public LoginResult login(LoginRequest loginRequest);

    @ApiOperation("退出")
    public ResponseResult logout();


    //增加页面信息
    @ApiOperation("根据cookie获取Jwt令牌住处")
    public JwtResult getJwt();

    @ApiOperation("根据cookie获取Jwt令牌住处")
    public JwtResult userjwt();
}
