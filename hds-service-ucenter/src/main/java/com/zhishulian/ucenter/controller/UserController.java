package com.zhishulian.ucenter.controller;

import com.zhishulian.api.ucenter.UCenterControllerApi;
import com.zhishulian.framework.domain.ucenter.ext.XcUserExt;
import com.zhishulian.ucenter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ucenter")
public class UserController implements UCenterControllerApi {

    @Autowired
    private UserService userService;


    @Override
    @GetMapping("/getXcUserExt")
    public XcUserExt getUserExt(@RequestParam("username") String username) {
        XcUserExt xcUserExt = userService.getXcUserExt(username);
//        if(xcUserExt==null){
//            return null;//用户名不存在
//        }
        return xcUserExt;
    }
}
