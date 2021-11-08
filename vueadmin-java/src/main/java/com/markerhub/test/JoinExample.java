package com.markerhub.test;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JoinExample {
    private class A extends Thread {
        @Override
        public void run() {
            System.out.println("A");
        }
    }

    private class B extends Thread {

        private A a;

        B(A a) {
            this.a = a;
        }

        @Override
        public void run() {
            try {
                a.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("B");
        }
    }

    public void test() {
        A a = new A();
        B b = new B(a);
        b.start();
        a.start();
    }

    public void createToken(){
        Map<String, Object> headerMap = new HashMap<String, Object>();
        //HS256加密方式
        headerMap.put("alg", "HS256");
        headerMap.put("type", "JWT");

        //有效载荷
        Map<String, Object> payloadMap = new HashMap<String, Object>();
        payloadMap.put("name", "user");
        payloadMap.put("password", "111111");


        //失效时间
        long timeMillis = System.currentTimeMillis();
        //设置1分钟
        long endTime = timeMillis + 31536000;

        //签证,签名
        String token =null;
        try {
            token= Jwts.builder()
                    .setHeader(headerMap)
                    .setClaims(payloadMap)
                    .setExpiration(new Date(endTime))
                    .signWith(SignatureAlgorithm.HS256,"rtet")
                    .compact();
            System.out.println(token);
        } catch (ExpiredJwtException e) {
            e.getClaims();
        }
        System.out.println(token);

    }

    public static void main(String[] args) {
        JoinExample example = new JoinExample();
        example.createToken();
//        example.test();
    }
}
