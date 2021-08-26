package com.duzon.lulu.menu.controller;

import com.duzon.common.model.LuluResult;
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

    @GetMapping("/{menu_idx}")
    @ResponseBody
    public LuluResult getMenu(@PathVariable int menu_idx) { // meuu_idx는 정상적으로 전달되나 한글 리턴 시 ?로 출력중...
        LuluResult result = new LuluResult();
        HashMap<String, Object> map = new HashMap<>();
        map.put("menu_idx", menu_idx);
        result.setResultData(menuService.getMenu(map));
        return result;
    }

}