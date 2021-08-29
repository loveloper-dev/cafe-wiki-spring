package com.duzon.lulu.star.controller;

import com.duzon.common.model.LuluResult;
import com.duzon.lulu.heart.service.RatingHeartService;
import com.duzon.lulu.star.service.RatingStarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/rating/star")
public class RatingStarController {

    @Autowired
    private RatingStarService ratingStarService;

    @PostMapping("/save")
    @ResponseBody
    public LuluResult insertStar(@RequestBody HashMap param) {
        return ratingStarService.insertStar(param);
    }

}