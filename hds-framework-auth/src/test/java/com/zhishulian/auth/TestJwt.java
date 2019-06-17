package com.zhishulian.auth;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.RsaSigner;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.interfaces.RSAPrivateKey;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestJwt {


    /**
     * 测试jwt令牌的生成
     */
    @Test
    public void testCreateJwt(){
        String xcKey="xc.keystore";

        String keyPass="xuechengkeystore";

        String pass="xuecheng";

        String alise="xckey";

        ClassPathResource classPathResource=new ClassPathResource(xcKey);
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(classPathResource, keyPass.toCharArray());
        KeyPair keyPair = keyStoreKeyFactory.getKeyPair(alise, pass.toCharArray());

        //获取私钥
        RSAPrivateKey aPrivate = (RSAPrivateKey)keyPair.getPrivate();
        Map map=new HashMap<String,String>();

        map.put("name","admin");
        map.put("age",23);
        String s = JSON.toJSONString(map);
        Jwt encode = JwtHelper.encode(s, new RsaSigner(aPrivate));

        System.out.println(encode);


    }


}
