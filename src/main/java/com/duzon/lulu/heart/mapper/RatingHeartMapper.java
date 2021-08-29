package com.duzon.lulu.heart.mapper;

import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public interface RatingHeartMapper {
    int insertHeart(HashMap param);

    int deleteHeart(HashMap param);
}
