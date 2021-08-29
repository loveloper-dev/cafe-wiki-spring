package com.duzon.lulu.allergy.service;

import com.duzon.common.model.LuluResult;
import com.duzon.lulu.allergy.mapper.AllergyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class AllergyService {

    @Autowired
    private AllergyMapper allergyMapper;

    public LuluResult getAllergyList() {
        LuluResult result = new LuluResult();
        result.setResultData(allergyMapper.getAllergyList());
        return result;
    }

    public LuluResult codingAllergy() {
        LuluResult result = new LuluResult();
        try {
            List<HashMap> allergyMapListFromMenu = allergyMapper.getAllergiesFromMenu();
            for(HashMap allergyMapFromMenu : allergyMapListFromMenu) {
                String[] menu_allergy_arr = allergyMapFromMenu.get("menu_allergy").toString().split("@");
                String coded_menu_allergy= "";
                for(int i = 0; i < menu_allergy_arr.length; i++) {
                    HashMap param = new HashMap();
                    param.put("menu_allergy", menu_allergy_arr[i]);
                    if(i > 0) {
                        coded_menu_allergy += ",";
                    }
                    coded_menu_allergy += allergyMapper.getAllergyIdxMap(param);
                }
                allergyMapFromMenu.put("menu_allergy", coded_menu_allergy);

                allergyMapper.updateAllergyStringToCode(allergyMapFromMenu);
            }

            result.setResultMsg("success");
        } catch (Exception e) {
            e.printStackTrace();
            result.setResultMsg("failed");
        }
        return result;
    }


}
