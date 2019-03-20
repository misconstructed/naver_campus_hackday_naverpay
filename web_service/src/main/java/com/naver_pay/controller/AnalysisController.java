package com.naver_pay.controller;

import com.naver_pay.VO.*;
import com.naver_pay.mapper.DataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@EnableAutoConfiguration
public class AnalysisController {

    @Autowired
    DataMapper dataMapper;

    @RequestMapping(value = "/overall", method = GET)
    public @ResponseBody
    ModelAndView analysisMain(HttpServletRequest request,
                              @RequestParam(value = "branchName", required = false) String branchName,
                              @RequestParam(value = "year", required = false) String year,
                              @RequestParam(value = "month", required = false) String month) {

        HttpSession httpSession = request.getSession();
        UserVO userVO = (UserVO) httpSession.getAttribute("user");
        ModelAndView modelAndView = null;
        ArrayList<GraphVO> list = null;

        if (userVO == null) {
            //TODO : 사용자 세션이 없는 경우 redirect
            return new ModelAndView("redirect:/main");

        } else if (userVO.getState() == 0) {
            modelAndView = new ModelAndView("analysisUser");
            String date = null;
            try {
                ArrayList<String> branchList = dataMapper.getBranchList();

                if (year == null) {
                    if (branchName == null || branchName.equals("default")) {
                        list = dataMapper.getYearlyGraph();
                        System.out.println("yearly all branch : " + list.size());
                        for (GraphVO tmp: list) {
                            System.out.println(tmp.toString());
                        }
                    } else {
                        list = dataMapper.getFilteredYearlyGraph(branchName);
                        System.out.println("yearly " + branchName + " : " + list.size());
                    }

                } else if (month == null) {
                    date = year;
                    if (branchName == null || branchName.equals("default")) {
                        list = dataMapper.getMonthlyGraph(date);
                        System.out.println("monthly " + date + " : " + list.size());
                    } else {
                        list = dataMapper.getMonthlyFilteredGraph(branchName, date);
                        System.out.println("monthly " + date + " " + branchName + " : " + list.size());
                    }

                } else {
                    date = year + "." + month;
                    if (branchName == null || branchName.equals("default")) {
                        list = dataMapper.getDailyGraph(date);
                        System.out.println("daily " + date + " : " + list.size());
                    } else {
                        list = dataMapper.getDailyFilteredGraph(branchName, date);
                        System.out.println("daily " + date + " " + branchName + " : " + list.size());
                    }
                }

                modelAndView.addObject("branchList", branchList);
                modelAndView.addObject("data", list);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            //TODO : 권한이 없는 경우 redirect
            return new ModelAndView("redirect:/main");
        }

        return modelAndView;
    }
}

