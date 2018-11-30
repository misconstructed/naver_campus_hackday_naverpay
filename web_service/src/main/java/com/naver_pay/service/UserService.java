package com.naver_pay.service;

import com.naver_pay.VO.UserVO;
import com.naver_pay.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public boolean register(String id, String password, String name, int state) {
        try {
            if(userMapper.insertUser(id, password, name, state) == false) {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public UserVO login(String id, String password) {
        UserVO userVO = null;
        try{
            userVO = userMapper.selectUser(id, password);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return userVO;
    }
}
