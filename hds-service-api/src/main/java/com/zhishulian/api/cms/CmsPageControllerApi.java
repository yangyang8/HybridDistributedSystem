package com.zhishulian.api.cms;

import com.zhishulian.framework.domain.cms.CmsPage;
import com.zhishulian.framework.domain.cms.request.QueryPageRequest;
import com.zhishulian.framework.domain.cms.response.CmsPageResult;
import com.zhishulian.framework.model.response.QueryResponseResult;
import com.zhishulian.framework.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Api(value="cms页面管理接口",description = "cms页面管理接口，提供页面的增、删、改、查")
public interface CmsPageControllerApi {
    //页面查询
    @ApiOperation("分页查询页面列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码", required = true, paramType = "path", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页记录数", required = true, paramType = "path", dataType = "int")
    })
    public QueryResponseResult findList(@PathVariable("pageNum") Integer pageNum, @PathVariable("pageSize") Integer pageSize, QueryPageRequest queryPageRequest);



    //增加页面信息
    @ApiOperation("增加页面信息")
    public CmsPageResult add(@RequestBody CmsPage cmsPage);

    @ApiOperation("查询页面信息")
    public CmsPageResult view(@PathVariable("id") String id);


    @ApiOperation("增加页面信息")
    public ResponseResult del(@PathVariable("id") String id);

    @ApiOperation("修改页面信息")
    public CmsPageResult edit(@PathVariable("id") String id,@RequestBody CmsPage cmsPage);




}