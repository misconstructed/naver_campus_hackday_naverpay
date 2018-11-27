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
            String date = null;
            try {
                if(year == null) {
                    System.out.println(branchName);
                    if(branchName == null || branchName.equals("default")) {
                        ArrayList<ReducedDataVO> list = dataMapper.getTotal();
                        changeDate(list);
                        System.out.println("daily all branch : "+ list.size());
                        analysisVO = new AnalysisVO(list);
                    } else {
                        ArrayList<ReducedDataVO> list = dataMapper.getFiltered(branchName);
                        changeDate(list);
                        System.out.println("daily "+branchName+" : "+list.size());
                        analysisVO = new AnalysisVO(list);
                    }

                } else if(month == null) {
                    date = year;
                    if(branchName == null || branchName.equals("default")) {
                        ArrayList<ReducedDataVO> list = dataMapper.getYearly(date);
                        System.out.println("yearly "+date+" : "+list.size());
                        analysisVO = new AnalysisVO(list);
                    } else {
                        ArrayList<ReducedDataVO> list = dataMapper.getYearlyBranchFiltered(branchName, date);
                        System.out.println("yearly "+date+" "+branchName+" : "+ list.size());
                        analysisVO = new AnalysisVO(list);
                    }

                } else if(day == null) {
                    date = year+"."+month;
                    if(branchName == null || branchName.equals("default")) {
                        ArrayList<ReducedDataVO> list = dataMapper.getMonthly(date);
                        System.out.println("monthly "+date+" : "+list.size());
                        analysisVO = new AnalysisVO(list);
                    } else {
                        ArrayList<ReducedDataVO> list = dataMapper.getMonthlyBranchFiltered(branchName, date);
                        System.out.println("yearly "+date+" "+branchName+" : "+ list.size());
                        analysisVO = new AnalysisVO(list);
                    }

                } else {
                    date = year+"."+month+"."+day;
                    if(branchName == null || branchName.equals("default")) {
                        ArrayList<ReducedDataVO> list = dataMapper.getDaily(date);
                        System.out.println("daily "+date+" : "+list.size());
                        analysisVO = new AnalysisVO(list);
                    } else {
                        ArrayList<ReducedDataVO> list = dataMapper.getDailyBranchFiltered(branchName, date);
                        System.out.println("daily "+date+" "+branchName+" : "+ list.size());
                        analysisVO = new AnalysisVO(list);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            modelAndView.addObject("data", analysisVO);
        } else {
            //TODO : 권한이 없는 경우 redirect
            return new ModelAndView("redirect:/main");
        }

        return  modelAndView;
    }

    private void changeDate(ArrayList<ReducedDataVO> list) {
        int i;
        ReducedDataVO data;
        for(i = 0; i < list.size(); i++) {
            data = list.get(i);
            data.setDate("-");
        }
    }

}
