package com.zhishulian.ucenter.dao;

import com.zhishulian.framework.domain.ucenter.XcCompanyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 员工公司信息
 */
@Repository
public interface CompanyUserRepository extends JpaRepository<XcCompanyUser,String> {

    /**
     * 根据用户名来获取用户信息
     * @param userId
     * @return
     */
    XcCompanyUser findByUserId(String userId);
}
