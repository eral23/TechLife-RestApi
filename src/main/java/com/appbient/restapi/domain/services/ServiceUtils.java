package com.appbient.restapi.domain.services;

import java.util.UUID;

public class ServiceUtils {
    public static Integer generateUniqueId() {      
        UUID idOne = UUID.randomUUID();
        String str=""+idOne;        
        int uid=str.hashCode();
        String filterStr=""+uid;
        str=filterStr.replaceAll("-", "");
        System.out.println("HOla" + Integer.parseInt(str));
        return Integer.parseInt(str);
    }
}
