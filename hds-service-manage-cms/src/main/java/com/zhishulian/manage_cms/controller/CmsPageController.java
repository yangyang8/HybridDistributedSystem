package com.zhishulian.manage_cms.controller;

import com.zhishulian.api.cms.CmsPageControllerApi;
import com.zhishulian.framework.domain.cms.CmsPage;
import com.zhishulian.framework.domain.cms.request.QueryPageRequest;
import com.zhishulian.framework.domain.cms.response.CmsPageResult;
import com.zhishulian.framework.model.response.CommonCode;
import com.zhishulian.framework.model.response.QueryResponseResult;
import com.zhishulian.framework.model.response.QueryResult;
import com.zhishulian.framework.model.response.ResponseResult;
import com.zhishulian.manage_cms.service.CmsPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/cms/page")
public class CmsPageController implements CmsPageControllerApi {

    @Autowired
    private CmsPageService cmsPageService;

    @GetMapping("/list/{pageNum}/{pageSize}")
    @Override
    public QueryResponseResult findList(@PathVariable("pageNum") Integer pageNum,@PathVariable("pageSize") Integer pageSize, QueryPageRequest queryPageRequest) {
        QueryResult queryResult=cmsPageService.findPageList(pageNum,pageSize,queryPageRequest);
        QueryResponseResult queryResponseResult = new QueryResponseResult(CommonCode.SUCCESS,queryResult);
        return queryResponseResult;
    }


    /**
     * 增加
     * @param cmsPage
     * @return
     */
    @Override
    @PostMapping("/add")
    public CmsPageResult add(@RequestBody CmsPage cmsPage) {
        return cmsPageService.addCmsPage(cmsPage);
    }

    @Override
    @GetMapping("/view/{id}")
    public CmsPageResult view(@PathVariable("id") String id) {
        return cmsPageService.viewCmsPage(id);
    }

    @Override
    @DeleteMapping("/del/{id}")
    public ResponseResult del(@PathVariable("id") String id) {
        return cmsPageService.delCmsPage(id);
    }

    @Override
    @PutMapping("/edit/{id}")
    public CmsPageResult edit(@PathVariable("id") String id, @RequestBody CmsPage cmsPage) {
        return cmsPageService.editCmsPage(id,cmsPage);
    }
}
