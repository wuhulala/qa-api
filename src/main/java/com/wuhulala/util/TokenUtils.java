package com.wuhulala.util;

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

    /**
     * 生成jwT
     *
     * @return Token字符串
     */
     public static String generateToken(String name , String roles){
         return Jwts.builder().setSubject(name).claim("roles", roles).setIssuedAt(new Date())
                 .signWith(SignatureAlgorithm.HS256, "secretkey").compact();
     }

    /**
     * 解析Token
     *
     * @return 存入token的数据
     */
     public static Token parseToken(String token){
         String result = new String(ThreeDes.decryptMode(ThreeDes.keyBytes, ThreeDes.hexStringToBytes(token)));
         String results[] = result.split("&&");
         if(results.length == 4) {
             return new Token(Long.valueOf(results[0]),Long.valueOf(results[1]),results[2],results[3]);
         }else{
             return null;
         }
     }

    public static void main(String[] args){

        String token = TokenUtils.generateToken("wuhulala", "user");
        System.out.println(token);
    }
}
