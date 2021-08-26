package com.duzon.lulu.menu.service;

import com.duzon.lulu.menu.mapper.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class MenuService {

    @Autowired
    private MenuMapper menuMapper;

    public List<HashMap> getMenuList(HashMap param) {
        return menuMapper.getMenuList(param);
    }

    public HashMap getMenu(HashMap param) {
        return menuMapper.getMenu(param);
    }
}
