package com.thoughtworks.controller;

import com.thoughtworks.util.BookUtil;
import com.thoughtworks.util.SessionUtil;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.HashMap;

@Controller
@Scope("session")
public class BookController {

    @ResponseBody
    @RequestMapping(value = "/api/add", produces = "text/plain;charset=UTF-8")
    public String add(@RequestParam String id, @RequestParam String price, HttpServletRequest request) {

        //update sesson of shopping items
        String shoppingItems = request.getSession().getAttribute(SessionUtil.SESSION_SHOPPING_ITEMS).toString();
        String newshoppingItems = BookUtil.add(shoppingItems, id);
        request.getSession().setAttribute(SessionUtil.SESSION_SHOPPING_ITEMS, newshoppingItems);

        String quanity = BookUtil.calculateQuanity(newshoppingItems);

        BigDecimal total = new BigDecimal(request.getSession().getAttribute(SessionUtil.SESSION_SHOPPING_MONEY).toString());
        total = total.add(new BigDecimal(price));
        request.getSession().setAttribute(SessionUtil.SESSION_SHOPPING_MONEY, total.toString());

        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("quanity", quanity);
        hashMap.put("total", total.toString());
        return BookUtil.hashMapToJsonstring(hashMap);

    }

    @ResponseBody
    @RequestMapping(value = "/api/sub", produces = "text/plain;charset=UTF-8")
    public String sub(@RequestParam String id, @RequestParam String price, HttpServletRequest request) {

        //update sesson of shopping items
        String shoppingItems = request.getSession().getAttribute(SessionUtil.SESSION_SHOPPING_ITEMS).toString();
        String newshoppingItems = BookUtil.sub(shoppingItems, id);
        request.getSession().setAttribute(SessionUtil.SESSION_SHOPPING_ITEMS, newshoppingItems);

        String quanity = BookUtil.calculateQuanity(newshoppingItems);

        BigDecimal total = new BigDecimal(request.getSession().getAttribute(SessionUtil.SESSION_SHOPPING_MONEY).toString());
        total = total.subtract(new BigDecimal(price));
        request.getSession().setAttribute(SessionUtil.SESSION_SHOPPING_MONEY, total.toString());

        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("quanity", quanity);
        hashMap.put("total", total.toString());
        return BookUtil.hashMapToJsonstring(hashMap);

    }

}

