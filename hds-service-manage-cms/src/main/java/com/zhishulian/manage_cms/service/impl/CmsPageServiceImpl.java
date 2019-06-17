package com.zhishulian.manage_cms.service.impl;

import com.zhishulian.framework.domain.cms.CmsPage;
import com.zhishulian.framework.domain.cms.request.QueryPageRequest;
import com.zhishulian.framework.domain.cms.response.CmsCode;
import com.zhishulian.framework.domain.cms.response.CmsPageResult;
import com.zhishulian.framework.exception.ExceptionCast;
import com.zhishulian.framework.model.response.CommonCode;
import com.zhishulian.framework.model.response.QueryResult;
import com.zhishulian.framework.model.response.ResponseResult;
import com.zhishulian.manage_cms.dao.CmsPageRepository;
import com.zhishulian.manage_cms.service.CmsPageService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CmsPageServiceImpl implements CmsPageService {


    @Autowired
    private CmsPageRepository cmsPageRepository;

    /**
     * 页面的分页查询操作
     * @param pageNum
     * @param pageSize
     * @param queryPageRequest
     * @return
     */
    @Override
    public QueryResult findPageList(Integer pageNum, Integer pageSize, QueryPageRequest queryPageRequest) {
        Pageable page= PageRequest.of(pageNum-1,pageSize);
        CmsPage cmsPage=new CmsPage();
        if(queryPageRequest!=null){
            if(StringUtils.isNotBlank(queryPageRequest.getSiteId())){
                cmsPage.setSiteId(queryPageRequest.getSiteId());
            }

            if(StringUtils.isNotBlank(queryPageRequest.getTemplateId())){
                cmsPage.setTemplateId(queryPageRequest.getTemplateId());
            }

            if(StringUtils.isNotBlank(queryPageRequest.getPageAliase())){
                cmsPage.setPageAliase(queryPageRequest.getPageAliase());
            }

        }

        ExampleMatcher exampleMatcher=ExampleMatcher.matching().withMatcher("pageAliase", ExampleMatcher.GenericPropertyMatchers.contains());
        Example<CmsPage> example=Example.of(cmsPage,exampleMatcher);
        Page<CmsPage> cmsPages = cmsPageRepository.findAll(page);
        QueryResult queryResult=new QueryResult();
        queryResult.setList(cmsPages.getContent());
        queryResult.setTotal(cmsPages.getTotalElements());
        return queryResult;




    }

    @Override
    public CmsPageResult editCmsPage(String id, CmsPage cmsPage) {
        Optional<CmsPage> byId = cmsPageRepository.findById(id);
        if(byId.isPresent()){
            CmsPage cmsPageDest = byId.get();
            //进行修改数据
            //准备更新数据
            //设置要修改的数据
            //更新模板id
            cmsPageDest.setTemplateId(cmsPage.getTemplateId());
            //更新所属站点
            cmsPageDest.setSiteId(cmsPage.getSiteId());
            //更新页面别名
            cmsPageDest.setPageAliase(cmsPage.getPageAliase());
            //更新页面名称
            cmsPageDest.setPageName(cmsPage.getPageName());
            //更新访问路径
            cmsPageDest.setPageWebPath(cmsPage.getPageWebPath());
            //更新物理路径
            cmsPageDest.setPagePhysicalPath(cmsPage.getPagePhysicalPath());
            cmsPageRepository.save(cmsPageDest);
            return new CmsPageResult(CommonCode.SUCCESS,cmsPageDest);
        }
        return new CmsPageResult(CommonCode.FAIL,null);
    }

    @Override
    public ResponseResult delCmsPage(String id) {
        Optional<CmsPage> byId = cmsPageRepository.findById(id);
        if(byId.isPresent()){
            CmsPage cmsPage = byId.get();
            cmsPageRepository.delete(cmsPage);
            return new ResponseResult(CommonCode.SUCCESS);
        }
        return new ResponseResult(CommonCode.FAIL);
    }

    @Override
    public CmsPageResult viewCmsPage(String id) {
        Optional<CmsPage> byId = cmsPageRepository.findById(id);
        if(byId.isPresent()){
            CmsPage cmsPage = byId.get();
            return new CmsPageResult(CommonCode.SUCCESS,cmsPage);
        }
        return new CmsPageResult(CommonCode.FAIL,null);
    }

    @Override
    public CmsPageResult addCmsPage(CmsPage cmsPage) {
        if(cmsPage!=null){

            CmsPage cms=cmsPageRepository.findByPageNameAndSiteIdAndPageWebPath(cmsPage.getPageName(),cmsPage.getSiteId(),cmsPage.getPageWebPath());
            if(cms==null){
                cmsPage.setPageId(null);
                cmsPageRepository.save(cmsPage);
                return new CmsPageResult(CommonCode.SUCCESS,cmsPage);
            }else{
                ExceptionCast.cast(CmsCode.CMS_ADDPAGE_EXISTSNAME);
            }
        }else{
            ExceptionCast.cast(CommonCode.SERVER_ERROR);
        }
        return new CmsPageResult(CommonCode.FAIL,null);
    }
}
