package com.wuhulala.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;


/**
 * Token 生成-解析 器
 *
 * @author Wuhulala
 * @version 1.0
 * @updateTime 2017/1/29
 */
public class TokenUtils {

    public static final int EXP_TIMES = 60 * 60 * 12 ;
    /**
     * 生成jwT
     *
     * @return Token字符串
     */
    public static String generateToken(Long id, String name, String roles) {
        Date nowDate = new Date();
        return Jwts.builder()
                .setSubject(id + "")
                .claim("name", name)
                .claim("roles", roles)
                .setIssuedAt(nowDate)
                .signWith(SignatureAlgorithm.HS256, "secretkey")
                .compact();
    }

    /**
     * 解析Token
     *
     * @return 存入token的数据
     */
    public static Claims parseToken(String token) {
        if (token == null) return null;
        return Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody();
    }

    public static void main(String[] args) {

        String token = TokenUtils.generateToken(1L, "wuhulala", "user");
        System.out.println(token);
    }
}
