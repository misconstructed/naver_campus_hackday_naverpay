package com.naver_pay.controller;

import com.naver_pay.VO.AnalysisVO;
import com.naver_pay.VO.ReducedDataVO;
import com.naver_pay.VO.UserVO;
import com.naver_pay.mapper.DataMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
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
public class BranchController {

    @Autowired
    DataMapper dataMapper;

    @RequestMapping(value = "/branch", method = GET)
    public @ResponseBody ModelAndView branchMain(HttpServletRequest request,
                                                 @RequestParam(value = "branchName", required = false) String brancHName,
                                                 @RequestParam(value = "year", required = false) String year,
                                                 @RequestParam(value = "month", required = false) String month,
                                                 @RequestParam(value = "day", required = false) String day) {

        HttpSession httpSession = request.getSession();
        UserVO userVO = (UserVO)httpSession.getAttribute("user");
        ModelAndView modelAndView = null;
        AnalysisVO analysisVO = null;
        if(userVO == null) {
            //TODO : redirect
        } else if(userVO.getState() == 0) {
            modelAndView = new ModelAndView("branchUser");

            try {
                if(year == null) {
                    ArrayList<ReducedDataVO> list = dataMapper.getDaily();
                    System.out.println(list.size()+"");
                    analysisVO = new AnalysisVO(list);

                    modelAndView.addObject("data", analysisVO);
                } else if(month == null) {

                } else if(day == null) {

                } else {

                }
            } catch (Exception e) {
                e.printStackTrace();
            }


        } else {
            //TODO : redirect
        }


        return  modelAndView;
    }



}
