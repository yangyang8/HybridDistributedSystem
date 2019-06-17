package com.zhishulian.manage_cms.service;

import com.zhishulian.framework.domain.cms.CmsPage;
import com.zhishulian.framework.domain.cms.request.QueryPageRequest;
import com.zhishulian.framework.domain.cms.response.CmsPageResult;
import com.zhishulian.framework.model.response.QueryResult;
import com.zhishulian.framework.model.response.ResponseResult;

public interface CmsPageService {

    /**
     * 页面的分页查询操作
     * @param pageNum
     * @param pageSize
     * @param queryPageRequest
     * @return
     */
    QueryResult findPageList(Integer pageNum, Integer pageSize, QueryPageRequest queryPageRequest);

    CmsPageResult editCmsPage(String id, CmsPage cmsPage);

    ResponseResult delCmsPage(String id);

    CmsPageResult viewCmsPage(String id);

    CmsPageResult addCmsPage(CmsPage cmsPage);
}
