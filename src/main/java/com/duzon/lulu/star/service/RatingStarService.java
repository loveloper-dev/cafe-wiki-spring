package com.duzon.lulu.star.service;

import com.duzon.common.model.LuluResult;
import com.duzon.lulu.heart.mapper.RatingHeartMapper;
import com.duzon.lulu.menu.mapper.MenuMapper;
import com.duzon.lulu.star.mapper.RatingStarMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class RatingStarService {

    @Autowired
    private RatingStarMapper ratingStarMapper;

    @Autowired
    private MenuMapper menuMapper;

    public LuluResult insertStar(HashMap param) {
        LuluResult result = new LuluResult();
        try {
            int row = ratingStarMapper.insertStar(param);
            if(row > 0) {
                result.setResultData(menuMapper.getMenu(param));
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.setResultCode(500);
            result.setResultMsg("별점 저장에 실패하였습니다.");
        }

        return result;
    }
}
