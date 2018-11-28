package com.naver_pay.controller;

import com.naver_pay.VO.AnalysisVO;
import com.naver_pay.VO.ReducedDataVO;
import com.naver_pay.VO.UserVO;
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

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@EnableAutoConfiguration
public class ProductController {

    @Autowired
    DataMapper dataMapper;

    @RequestMapping(value = "/product", method = GET)
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
            modelAndView = new ModelAndView("productUser");
            String date = null;
            try {
                ArrayList<String> branchList = dataMapper.getBranchList();
                if(year == null) {
                    if(branchName == null || branchName.equals("default")) {
                        ArrayList<ReducedDataVO> list = dataMapper.getTotalProduct();
                        changeDate(list);
                        System.out.println("daily all branch : "+ list.size());
                        analysisVO = new AnalysisVO(list);
                    } else {
                        ArrayList<ReducedDataVO> list = dataMapper.getFilteredProduct(branchName);
                        changeDate(list);
                        System.out.println("daily "+branchName+" : "+list.size());
                        analysisVO = new AnalysisVO(list);
                    }

                } else if(month == null) {
                    date = year;
                    if(branchName == null || branchName.equals("default")) {
                        ArrayList<ReducedDataVO> list = dataMapper.getYearlyProduct(date);
                        System.out.println("yearly "+date+" : "+list.size());
                        analysisVO = new AnalysisVO(list);
                    } else {
                        ArrayList<ReducedDataVO> list = dataMapper.getYearlyBranchFilteredProduct(branchName, date);
                        System.out.println("yearly "+date+" "+branchName+" : "+ list.size());
                        analysisVO = new AnalysisVO(list);
                    }

                } else if(day == null) {
                    date = year+"."+month;
                    if(branchName == null || branchName.equals("default")) {
                        ArrayList<ReducedDataVO> list = dataMapper.getMonthlyProduct(date);
                        System.out.println("monthly "+date+" : "+list.size());
                        analysisVO = new AnalysisVO(list);
                    } else {
                        ArrayList<ReducedDataVO> list = dataMapper.getMonthlyBranchFilteredProduct(branchName, date);
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

                modelAndView.addObject("branchList", branchList);
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
