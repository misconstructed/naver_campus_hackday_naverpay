package com.naver_pay.controller;

import com.naver_pay.VO.AnalysisVO;
import com.naver_pay.VO.DataVO;
import com.naver_pay.VO.UserVO;
import com.naver_pay.VO.ReducedDataVO;
import com.naver_pay.service.CsvReader;
import com.naver_pay.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

@Controller
@EnableAutoConfiguration
public class MainWebController {

    @Autowired
    private MainService mainService;

    private AnalysisVO analysisVO;

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public @ResponseBody
    ModelAndView main(HttpServletRequest request,
                      @RequestParam(value = "branchName", required = false) String branchName) {
        ModelAndView modelAndView = null;
        HttpSession httpSession = request.getSession();
        UserVO userVO = (UserVO) httpSession.getAttribute("user");

        if (userVO == null) {
            //기본 데이터 저장
            //mainService.saveCsv();
            modelAndView = new ModelAndView("login");
        }

        //TODO : 기존에 로그인 한 경우 구현
        //기존에 관리자로 로그인 함
        else if (userVO.getState() == 0) {
            modelAndView = new ModelAndView("mainUser");
            //list = new ArrayList<>();
            if (branchName == null || branchName.equals("default")) {
                try {
                    analysisVO = mainService.getTotal();
                    modelAndView.addObject("data", analysisVO);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            //branch로 검색한 경우
            else {
                try {
                    analysisVO = mainService.getFiltered(branchName);
                    modelAndView.addObject("data", analysisVO);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        //기존에 가맹점주로 로그인 함
        else if (userVO.getState() == 1) {

        }
        //기존에 일반 사용자로 로그인 함
        else if (userVO.getState() == 2) {

        }

        return modelAndView;
    }
}
