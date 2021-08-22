package com.duzon.lulu.menu.controller;

import com.duzon.lulu.menu.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/menus")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping("")
    @ResponseBody
    public List<HashMap> getMenuList(@RequestParam HashMap param) {
        return menuService.getMenuList(param);
    }

}
