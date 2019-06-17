package com.zhishulian.ucenter.dao;

import com.zhishulian.framework.domain.ucenter.XcUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * 基本的用户信息接口
 */
@Repository
public interface UserRepository extends JpaRepository<XcUser,String> {


    /**
     * 根据用户来获取用户信息
     * @param username
     * @return
     */
    XcUser findByUsername(String username);

}
