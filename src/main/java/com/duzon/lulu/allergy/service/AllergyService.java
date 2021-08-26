package com.duzon.lulu.allergy.service;

import com.duzon.common.model.LuluResult;
import com.duzon.lulu.allergy.mapper.AllergyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AllergyService {

    @Autowired
    private AllergyMapper allergyMapper;

    public LuluResult getAllergyList() {
        LuluResult result = new LuluResult();
        result.setResultData(allergyMapper.getAllergyList());
        return result;
    }
}
