package com.zhishulian.manage_cms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;


/**
 * 运行内容管理模块
 */
@SpringBootApplication
@EntityScan("com.zhishulian.framework.domain.cms")
@ComponentScan("com.zhishulian.api")
@ComponentScan("com.zhishulian.manage_cms")
@ComponentScan("com.zhishulian.framework")
public class CmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CmsApplication.class,args);
    }

}
