package com.duzon.lulu.menu.service;

import com.duzon.common.model.LuluResult;
import com.duzon.lulu.menu.mapper.MenuMapper;
import com.duzon.lulu.user.service.UserService;
import org.apache.commons.collections.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MenuService {

    @Autowired
    private UserService userService;

    @Autowired
    private MenuMapper menuMapper;

    public LuluResult getMenuList(HashMap param) {
        LuluResult result = new LuluResult();
        try {
            if((Boolean)param.get("isLogin")) {
                HashMap userInfo = (HashMap) param.get("userInfo");
                if(userService.verifyJWT(userInfo.get("jwt").toString())) {
                    List<HashMap> resultList = new ArrayList<>();

                    param.put("user_idx", Integer.parseInt(userInfo.get("user_idx").toString()));
                    List<HashMap> menuList = menuMapper.getMenuList(param);
                    if((Boolean)param.get("isAllergyFiltering")) {

                        List<String> user_allergy_arr = new ArrayList<>(Arrays.asList(userInfo.get("user_allergy").toString().split(",")));
                        for(HashMap menu : menuList) {
                            List<String> menu_allergy_arr = new ArrayList<>(Arrays.asList(menu.get("menu_allergy").toString().split(",")));

                            List<String> unionList = ListUtils.union(user_allergy_arr, menu_allergy_arr);
                            HashSet<String> unionSet = new HashSet<String>(unionList);
                            if(unionList.size() <= unionSet.size()) {
                                menu.put("isDanger", false);
                                resultList.add(menu);
                            }
                        }
                    } else {

                        List<String> user_allergy_arr = new ArrayList<>(Arrays.asList(userInfo.get("user_allergy").toString().split(",")));
                        for(HashMap menu : menuList) {
                            List<String> menu_allergy_arr = new ArrayList<>(Arrays.asList(menu.get("menu_allergy").toString().split(",")));

                            List<String> unionList = ListUtils.union(user_allergy_arr, menu_allergy_arr);
                            HashSet<String> unionSet = new HashSet<String>(unionList);
                            if(unionList.size() <= unionSet.size()) {
                                menu.put("isDanger", false);
                            } else {
                                menu.put("isDanger", true);
                            }
                            resultList.add(menu);
                        }

//                        resultList = menuList;
                    }

                    result.setResultData(resultList);
                } else {
                    result.setResultCode(500);
                    result.setResultMsg("expiredToken");
                }
            } else {
                result.setResultData(menuMapper.getMenuList(param));
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public HashMap getMenu(HashMap param) {
        return menuMapper.getMenu(param);
    }
}
