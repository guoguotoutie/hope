package com.hopeshop.auth.test;

import com.hopeshop.auth.entity.UserInfo;
import com.hopeshop.auth.utils.JwtUtils;
import com.hopeshop.auth.utils.RsaUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * Created by silence on 2020/1/19.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JwtTest.class)
public class JwtTest {

    private static final String pubKeyPath = "D:\\tmp\\rsa\\rsa.pub";

    private static final String priKeyPath = "D:\\tmp\\rsa\\rsa.pri";

    private PublicKey publicKey;

    private PrivateKey privateKey;

    @Test
    public void testRsa() throws Exception {
        RsaUtils.generateKey(pubKeyPath, priKeyPath, "hopeshop");
    }
    @Before
    public void testGetRsa() throws Exception {
        this.publicKey = RsaUtils.getPublicKey(pubKeyPath);
        this.privateKey = RsaUtils.getPrivateKey(priKeyPath);
    }

    @Test
    public void testGenerateToken() throws Exception {
        // 生成token
        String token = JwtUtils.generateToken(new UserInfo(20L, "jack"), privateKey, 5);
        System.out.println("token = " + token);
    }

    @Test
    public void testParseToken() throws Exception {
        String token = "eyJhbGciOiJSUzI1NiJ9.eyJpZCI6MjAsInVzZXJuYW1lIjoiamFjayIsImV4cCI6MTYwMDkzMzc3M30.Yido7JMnhvnzQocjd2Z-lJ8r1B6fljf2edL1RTLpUnbg7Rdl_r-RqSdWZn7LSK4XMjuuyyhw-TdArFEs_lC7j2zlGKAseVL5D8IWlLRluYPE-xCfQLkrpnu8lhCyaaY2Qd7M5hL5Lu4ghXuH6ddEyuDLs5Fm5HwnXjFghFFeSDc";

        // 解析token
        UserInfo user = JwtUtils.getInfoFromToken(token, publicKey);
        System.out.println("id: " + user.getId());
        System.out.println("userName: " + user.getUsername());
    }
}
