package com.naver_pay.controller;

import com.naver_pay.VO.ResultVO;
import com.naver_pay.VO.UserVO;
import com.naver_pay.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Controller
@EnableAutoConfiguration
public class UserController {

    @Autowired
    UserMapper userMapper;

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public @ResponseBody ModelAndView main(HttpServletRequest request) {
        ModelAndView modelAndView = null;
        HttpSession httpSession = request.getSession();
        UserVO userVO = (UserVO)httpSession.getAttribute("user");

        if(userVO == null) {
            modelAndView = new ModelAndView("signup");
        }

        //error
        else {
            System.out.println("user info alread exists");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public @ResponseBody
    ResultVO register(HttpServletRequest request,
                      @RequestBody HashMap<String, String> map) {
        ResultVO resultVO = null;

        try {
            String id = map.get("id");
            String password = map.get("password");
            String name = map.get("name");
            int state = Integer.parseInt(map.get("state"));

            resultVO = new ResultVO("success", "회원가입 성공");

            if(userMapper.insertUser(id, password, name, state) == false) {
                resultVO.setStatus("error");
                resultVO.setMassage("아이디 중복");
            }
        } catch (Exception e){
            e.printStackTrace();
            resultVO = new ResultVO("error", "회원가입 오류");
        }

        return resultVO;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public @ResponseBody
    ResultVO login(HttpServletRequest request,
                   @RequestBody HashMap<String, String> map) {
        ResultVO resultVO = null;
        UserVO userVO = null;
        try {
            String id = map.get("id");
            String password = map.get("password");

            resultVO = new ResultVO("error");
            resultVO.setMassage("로그인 실패");

            if((userVO = userMapper.selectUser(id, password)) != null) {
                resultVO.setStatus("success");
                resultVO.setMassage("로그인 성공");
                request.getSession().setAttribute("user", userVO);
            }

        } catch (Exception e){
            e.printStackTrace();
            resultVO = new ResultVO("error", "로그인 오류");
        }

        return resultVO;
    }

}
