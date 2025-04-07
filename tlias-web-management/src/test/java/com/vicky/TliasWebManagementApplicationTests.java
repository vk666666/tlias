package com.vicky;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static io.jsonwebtoken.SignatureAlgorithm.HS256;

/*@SpringBootTest*/
//单独测试可以注释掉SpringBootTest，否则加载整个环境配置
class TliasWebManagementApplicationTests {
    @Test
    public void testUuid(){
        for (int i = 0; i < 10; i++) {
            String uuid = UUID.randomUUID().toString();
            System.out.println("uuid = " + uuid);
        }
    }
    //测试jwt令牌生成
    @Test
    public void testGenJwt(){
        Map<String,Object> claims = new HashMap<>();
        claims.put("id",1);
        claims.put("username","hello");

        String jwt = Jwts.builder()
                .signWith(HS256, "vicky")//签名算法
                .setClaims(claims) //自定义内容（载荷）
                .setExpiration(new Date(System.currentTimeMillis()))//有效期1小时 + 3600 * 1000
                .compact();
        System.out.println(jwt);
    }
    //解析jwt
    @Test
    public void testParseJwt(){
        Claims claims = Jwts.parser()
                .setSigningKey("vicky")
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwiZXhwIjoxNzQzNTg5MTcxLCJ1c2VybmFtZSI6ImhlbGxvIn0.KvmQFZcETbvNQ0UTkArvOeq75M7gTQfrOSqxw0B4DrE")
                .getBody();
        System.out.println(claims);
    }
}
