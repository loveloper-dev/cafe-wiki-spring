package com.duzon.lulu.user.mapper;

import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public interface UserMapper {
    HashMap getUserInfo(HashMap<String, Object> param);
}
