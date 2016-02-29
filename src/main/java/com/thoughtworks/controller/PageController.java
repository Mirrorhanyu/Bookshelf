package com.thoughtworks.controller;

import com.thoughtworks.util.SessionUtil;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
@Scope("session")
public class PageController {

    @RequestMapping(value ="/", method = RequestMethod.GET)
    public String home(Model model, HttpServletRequest request) {
        request.getSession().setAttribute(SessionUtil.SESSION_SHOPPING_ITEMS, "");
        request.getSession().setAttribute(SessionUtil.SESSION_SHOPPING_MONEY, "0");
        return "home";
    }

    @RequestMapping(value ="/detail/**/", method = RequestMethod.GET)
    public String detail(Model model, HttpServletRequest request) {
        return "detail";
    }

    @RequestMapping(value ="/shoppingcart", method = RequestMethod.GET)
    public String shoppingcart(Model model, HttpServletRequest request) {
        return "shoppingcart";
    }

    @RequestMapping(value ="/success", method = RequestMethod.GET)
    public String success(Model model, HttpServletRequest request) {
        return "success";
    }

}
