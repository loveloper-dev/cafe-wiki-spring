package com.duzon.lulu.user.controller;

import com.duzon.common.model.LuluResult;
import com.duzon.lulu.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    @ResponseBody
    public LuluResult login(@RequestBody HashMap param) throws Exception {
        return userService.login(param);
    }

    @GetMapping("/gen/token")
    public HashMap<String, Object> genToken(@RequestParam(value="subject") String subject) {
//        String token = loginService.createToken();
        HashMap<String, Object> map = new LinkedHashMap<String, Object>();
//        map.put("result", token);
//        return map;
        return null;
    }

    @GetMapping("/chk/token")
    public Map<String, Object> chkToken(@RequestParam(value="token") String token) {
//        return loginService.verifyJWT(token);
        return null;
    }

//    @ResponseBody
//    @GetMapping("/get/subject")
//    public HashMap<String, Object> getSubject(@RequestParam("token") String token) {
//        String subject = loginService.getSubject(token);
//        HashMap<String, Object> map = new LinkedHashMap<String, Object>();
//        map.put("result", subject);
//        return map;
//    }
}
