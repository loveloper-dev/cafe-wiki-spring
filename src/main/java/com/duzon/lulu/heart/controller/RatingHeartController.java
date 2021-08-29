package com.duzon.lulu.heart.controller;

import com.duzon.common.model.LuluResult;
import com.duzon.lulu.heart.service.RatingHeartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/rating/heart")
public class RatingHeartController {

    @Autowired
    private RatingHeartService ratingHeartService;

    @PostMapping("/save")
    @ResponseBody
    public LuluResult insertHeart(@RequestBody HashMap param) {
        return ratingHeartService.insertHeart(param);
    }

    @PostMapping("/cancel")
    @ResponseBody
    public LuluResult deleteHeart(@RequestBody HashMap param) {
        return ratingHeartService.deleteHeart(param);
    }

}