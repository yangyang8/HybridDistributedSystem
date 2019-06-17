package com.zhishulian.api.ucenter;

import com.zhishulian.framework.domain.cms.CmsPage;
import com.zhishulian.framework.domain.cms.response.CmsPageResult;
import com.zhishulian.framework.domain.ucenter.ext.XcUserExt;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Api(value="用户管理接口",description = "用户个人中心管理接口")
public interface UCenterControllerApi {

    //增加页面信息
    @ApiOperation("根据用户名称获取用户实体对象")
    public XcUserExt getUserExt(@RequestParam("username") @ApiParam(name = "用户名") String username);
}
