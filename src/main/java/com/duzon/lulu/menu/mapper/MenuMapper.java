package com.duzon.lulu.menu.mapper;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public interface MenuMapper {
    List<HashMap> getMenuList(HashMap param);

    HashMap getMenu(HashMap param);
}
