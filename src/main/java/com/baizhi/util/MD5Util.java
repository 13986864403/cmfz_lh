package com.baizhi.util;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.Random;

public class MD5Util {
    public static String getSalt(){
        String s="abcdefghijklmnopqrstuvwxyz1234567890";
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<4;i++){
            Random r=new Random();
            int d=r.nextInt(s.length());
            String ss = s.substring(d,d+1);
            sb.append(ss);
        }
        return sb.toString();
    }

    public static String getMD5(String password,String salt){
        String s = DigestUtils.md5Hex(password + "" + salt);
        return s;
    }
}



