package com.thoughtworks.controller;

import com.google.gson.JsonArray;
import com.mongodb.util.JSON;
import com.thoughtworks.util.BookUtil;
import com.thoughtworks.util.MongodbUtil;
import com.thoughtworks.util.SessionUtil;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
@Scope("session")
public class AjaxController {

    @ResponseBody
    @RequestMapping(value = "/api/searchbytitle", produces = "text/plain;charset=UTF-8")
    public String getBookInfoByTitle(@RequestParam String title) {
        JsonArray books = title.equals("")?
                MongodbUtil.getMongodbInstance().getAllBooks():
                MongodbUtil.getMongodbInstance().getBookInfoByTitle(title);
        return books.toString();
    }

    @ResponseBody
    @RequestMapping(value = "/api/searchbyid", produces = "text/plain;charset=UTF-8")
    public String getBookById(@RequestParam String id) {
        JsonArray books = MongodbUtil.getMongodbInstance().getBookById(id);
        return books.toString();
    }

    @ResponseBody
    @RequestMapping(value = "/api/getshoppingmoney", produces = "text/plain;charset=UTF-8")
    public String getShoppingMoneyFromSession(HttpServletRequest request, HttpServletResponse response) {
        return request.getSession().getAttribute(SessionUtil.SESSION_SHOPPING_MONEY).toString();
    }

    @ResponseBody
    @RequestMapping(value = "/api/getshoppingcart", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
    public String getShoppingcartFromSession(HttpServletRequest request, HttpServletResponse response) {
        String shoppingcart = request.getSession().getAttribute(SessionUtil.SESSION_SHOPPING_ITEMS).toString();
        HashMap<String, String> hashMap = BookUtil.jsonToHashMap(shoppingcart);
        HashMap<String, HashMap<String, String >> result = new HashMap<>();
        for(Map.Entry<String, String> entry : hashMap.entrySet()){
            String id = entry.getKey();
            String quanity = entry.getValue();
            String bookinfo = MongodbUtil.getMongodbInstance().getBookById(id).toString();
            HashMap map = new HashMap();
            map.put("bookinfo", new Object[]{JSON.parse(bookinfo.substring(1, bookinfo.length()-1))});
            map.put("quanity", quanity);
            result.put(id, map);
        }
        return BookUtil.hashMapToJsonstring(result);
    }

    @ResponseBody
    @RequestMapping(value = "/api/getquanityandtotal", produces = "text/plain;charset=UTF-8")
    public String getQuanityAndTotal(HttpServletRequest request) {

        String shoppingItems = request.getSession().getAttribute(SessionUtil.SESSION_SHOPPING_ITEMS).toString();
        String quanity = BookUtil.calculateQuanity(shoppingItems);
        String total =  request.getSession().getAttribute(SessionUtil.SESSION_SHOPPING_MONEY).toString();

        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("quanity", quanity);
        hashMap.put("total", total);

        return BookUtil.hashMapToJsonstring(hashMap);

    }

}

