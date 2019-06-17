package com.zhishulian.ucenter.service;

import com.zhishulian.framework.domain.ucenter.XcCompanyUser;
import com.zhishulian.framework.domain.ucenter.XcUser;
import com.zhishulian.framework.domain.ucenter.ext.XcUserExt;
import com.zhishulian.ucenter.dao.CompanyUserRepository;
import com.zhishulian.ucenter.dao.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 用户中心的服务层接口
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private CompanyUserRepository companyUserRepository;



    //根据用户的用户名来获取用户的基本信息
    public XcUser findXcUserByUsername(String username){
        XcUser xcUser= userRepository.findByUsername(username);
        return xcUser;
    }



    //根据用户的用户名来获取用户的基本信息
    public XcUserExt getXcUserExt(String username){
        XcUser xcUser = this.findXcUserByUsername(username);
        if(xcUser==null){
            return null;
        }
        //进行获取用户的公司名称
        XcCompanyUser xcCompanyUser=companyUserRepository.findByUserId(xcUser.getId());
        String companyId="";
        if(xcCompanyUser!=null&&xcCompanyUser.getCompanyId()!=null){
            companyId=xcCompanyUser.getCompanyId();
        }
        XcUserExt xcUserExt=new XcUserExt();
        BeanUtils.copyProperties(xcUser,xcUserExt);
        xcUserExt.setCompanyId(companyId);
        return xcUserExt;
    }



}
