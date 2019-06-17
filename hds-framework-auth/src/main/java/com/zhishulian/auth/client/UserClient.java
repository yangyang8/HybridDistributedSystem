package com.zhishulian.auth.client;

import com.zhishulian.framework.client.XcServiceList;
import com.zhishulian.framework.domain.ucenter.ext.XcUserExt;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = XcServiceList.XC_SERVICE_UCENTER)
public interface UserClient {

    @GetMapping("/ucenter/getXcUserExt")
    public XcUserExt getUserExt(@RequestParam("username") String username);
}
