package com.duzon.lulu.user.service;

import com.duzon.common.model.LuluResult;
import com.duzon.lulu.user.mapper.UserMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    final String key = "Bamdule";

    public LuluResult login(HashMap param) {

        LuluResult result = new LuluResult();

//        String[] requiredKeys = {"userEmail", "userPswd"};
//        if (!ValidationUtil.checkContainsKeys(requiredKeys, param)) {
//            result.setResultCode(400);
//            result.setResultMsg("Parameter is not valid");
//        } else {
            // email, bcrypt(pswd) 넘겨서 로그인 정보 받아옴
        try {
            param.put("pswd", param.get("pswd").toString());
            HashMap<String, Object> userInfo = userMapper.getUserInfo(param);
            if(userInfo.containsKey("user_idx")) { // 로그인 성공
                String jwt = createToken(userInfo); // token 발행
                result.setResultData(jwt); //  resultData에 token 넣어보냄
            } else { // 로그인 실패 - 정보 없음
                result.setResultCode(400);
                result.setResultMsg("invalid user information");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

//        }

        // 유저 정보를 다 가지고 리턴
        return result;
    }


    //토큰 생성
    public String createToken(HashMap userInfo) {

        //Header 부분 설정
        HashMap<String, Object> headers = new HashMap<>();
        headers.put("typ", "JWT");
        headers.put("alg", "HS256");

        //payload 부분 설정
        HashMap<String, Object> payloads = new HashMap<>();
        payloads.put("userInfo", userInfo);

        Long expiredTime = 1000 * 60L * 60L * 2L; // 토큰 유효 시간 (2시간)
//        Long expiredTime = 1000 * 60L; //

        Date ext = new Date(); // 토큰 만료 시간
        ext.setTime(ext.getTime() + expiredTime);

        // 토큰 Builder
        String jwt = Jwts.builder()
                .setHeader(headers) // Headers 설정
                .setClaims(payloads) // Claims 설정
                .setSubject("user") // 토큰 용도
                .setExpiration(ext) // 토큰 만료 시간 설정
                .signWith(SignatureAlgorithm.HS256, key.getBytes()) // HS256과 Key로 Sign
                .compact(); // 토큰 생성

        return jwt;
    }

    //토큰 검증
    public Map<String, Object> verifyJWT(String jwt) {
        Map<String, Object> claimMap = null;
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(key.getBytes("UTF-8")) // Set Key
                    .parseClaimsJws(jwt) // 파싱 및 검증, 실패 시 에러
                    .getBody();

            claimMap = claims;

        } catch (ExpiredJwtException e) { // 토큰이 만료되었을 경우
            claimMap = new HashMap<>();
            claimMap.put("error", "token 만료");
        } catch (Exception e) { // 그외 에러났을 경우
            System.out.println(e);
        }
        return claimMap;
    }

    public LuluResult join(HashMap param) {
        LuluResult result = new LuluResult();
        try {
            boolean isSuccess = userMapper.join(param) > 0 ;
            if(isSuccess) {
                result.setResultMsg("회원가입에 성공하였습니다.");
            } else {
                result.setResultCode(500);
                result.setResultMsg("회원가입 요청은 정상 실행되었으나 저장에 실패하였습니다.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.setResultMsg("회원가입 요청에 실패하였습니다.");
        }

        return result;
    }

}