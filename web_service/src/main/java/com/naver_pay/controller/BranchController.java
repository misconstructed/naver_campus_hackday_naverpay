package com.naver_pay.controller;

import com.naver_pay.VO.AnalysisVO;
import com.naver_pay.VO.ReducedDataVO;
import com.naver_pay.VO.UserVO;
import com.naver_pay.mapper.DataMapper;
import com.naver_pay.service.BranchService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@EnableAutoConfiguration
public class BranchController {

    @Autowired
    private BranchService branchService;

    @RequestMapping(value = "/branch", method = GET)
    public @ResponseBody ModelAndView branchMain(HttpServletRequest request,
                                                 @RequestParam(value = "branchName", required = false) String branchName,
                                                 @RequestParam(value = "year", required = false) String year,
                                                 @RequestParam(value = "month", required = false) String month,
                                                 @RequestParam(value = "day", required = false) String day) {

        HttpSession httpSession = request.getSession();
        UserVO userVO = (UserVO)httpSession.getAttribute("user");
        ModelAndView modelAndView = null;
        AnalysisVO analysisVO = null;

        if(userVO == null) {
            //TODO : 사용자 세션이 없는 경우 redirect
            return new ModelAndView("redirect:/main");

        } else if(userVO.getState() == 0) {
            modelAndView = new ModelAndView("branchUser");

            analysisVO = branchService.getData(branchName, year, month, day);

            modelAndView.addObject("data", analysisVO);
        } else {
            //TODO : 권한이 없는 경우 redirect
            return new ModelAndView("redirect:/main");
        }

        return  modelAndView;
    }
}
