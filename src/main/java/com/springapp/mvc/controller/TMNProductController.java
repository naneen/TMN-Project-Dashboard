package com.springapp.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TMNProductController {

    @RequestMapping(value = "/",method = RequestMethod.GET )
    public String product(ModelMap model) {
        model.addAttribute("msg", "TMN Product Dashboard");
//        return "TMNProduct";
        return "TempKiosk";
    }

}
