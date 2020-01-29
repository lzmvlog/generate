package com.shaojie.generate.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author： ShaoJie
 * @data： 2020年01月29日 17:07
 * @Description： 访问控制器
 */
@org.springframework.stereotype.Controller
public class Controller {

    @GetMapping("index")
    public String index(Model model){
        model.addAttribute("user","Zhangsan");
        return "index";
    }

}
