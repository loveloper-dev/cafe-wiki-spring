package com.duzon.lulu.heart.service;

import com.duzon.common.model.LuluResult;
import com.duzon.lulu.heart.mapper.RatingHeartMapper;
import com.duzon.lulu.menu.mapper.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class RatingHeartService {

    @Autowired
    private RatingHeartMapper ratingHeartMapper;

    @Autowired
    private MenuMapper menuMapper;

    public LuluResult insertHeart(HashMap param) {
        LuluResult result = new LuluResult();
        try {
            int row = ratingHeartMapper.insertHeart(param);
            if(row > 0) {
                result.setResultData(menuMapper.getMenu(param));
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.setResultCode(500);
            result.setResultMsg("하트 저장에 실패하였습니다.");
        }

        return result;
    }

    public LuluResult deleteHeart(HashMap param) {
        LuluResult result = new LuluResult();
        try {
            int row = ratingHeartMapper.deleteHeart(param);
            if(row > 0) {
                result.setResultData(menuMapper.getMenu(param));
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.setResultCode(500);
            result.setResultMsg("하트 취소에 실패하였습니다.");
        }

        return result;
    }
}
