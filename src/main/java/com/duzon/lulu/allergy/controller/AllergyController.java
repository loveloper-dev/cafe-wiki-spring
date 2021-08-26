package com.duzon.lulu.allergy.controller;

import com.duzon.common.model.LuluResult;
import com.duzon.lulu.allergy.service.AllergyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/allergies")
public class AllergyController {

    @Autowired
    private AllergyService allergyService;

    @GetMapping("")
    @ResponseBody
    public LuluResult getAllergyList() {
        return allergyService.getAllergyList();
    }

}
