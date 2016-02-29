package com.thoughtworks.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class BookUtil {

    public static HashMap jsonToHashMap(String jsonstring){
       if(jsonstring.equals(""))
           return new HashMap();
        else
           return new Gson().fromJson(jsonstring, new TypeToken<HashMap>(){}.getType());
    }

    public static String hashMapToJsonstring(HashMap hashMap){
        return new Gson().toJson(hashMap);
    }

    public static String add(String session, String id){
        HashMap<String, String> hashMap = jsonToHashMap(session);
        if(hashMap.containsKey(id)){
            hashMap.put(id, String.valueOf(Integer.valueOf(hashMap.get(id)) +1));
        }else{
            hashMap.put(id, "1");
        }
        return hashMapToJsonstring(hashMap);
    }

    public static String sub(String session, String id){
        HashMap<String, String> hashMap = jsonToHashMap(session);
        int currentQuanity = Integer.valueOf(hashMap.get(id));
        if(currentQuanity > 1)
            hashMap.put(id, String.valueOf(currentQuanity - 1));
        else
            hashMap.remove(id);
        return hashMapToJsonstring(hashMap);
    }

    public static String calculateQuanity(String session) {
        HashMap<String, String> hashMap = jsonToHashMap(session);
        int quanity = 0;
        for(Map.Entry<String, String> entry : hashMap.entrySet()){
            quanity += Integer.valueOf(entry.getValue());
        }
        return String.valueOf(quanity);
    }

    /*public static void main(String[] args){
        BigDecimal a = new BigDecimal("15.00");
        BigDecimal b = new BigDecimal("24.80");
        a= a.add(b);
        a=a.add(b);
        a=a.add(b);
        a=a.add(b);
        a=a.subtract(b);
        System.out.println(a.toString());
        a=a.subtract(b);
        System.out.println(a.toString());
        a=a.subtract(b);
        System.out.println(a.toString());
        a=a.subtract(b);
        System.out.println(a.toString());

    }*/

}
