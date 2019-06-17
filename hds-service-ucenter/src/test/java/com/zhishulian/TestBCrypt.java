package com.zhishulian;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class TestBCrypt {


    /**
     * 进行测试高级加密方式
     */
    @Test
    public void testBCrypt(){
        String password="123456";
        BCryptPasswordEncoder cryptPasswordEncoder=new BCryptPasswordEncoder();
        for(int i=0;i<10;i++){
            String encode = cryptPasswordEncoder.encode(password);
            System.out.println(encode);
            boolean matches = cryptPasswordEncoder.matches(password, encode);
            System.out.println("结果:"+matches);
        }

    }

}
