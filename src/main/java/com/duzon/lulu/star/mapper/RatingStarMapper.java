package com.duzon.lulu.star.mapper;

import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public interface RatingStarMapper {
    int insertStar(HashMap param);
}
