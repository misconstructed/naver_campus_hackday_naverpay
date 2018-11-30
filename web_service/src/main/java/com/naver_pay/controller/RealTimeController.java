package com.naver_pay.controller;

import com.naver_pay.VO.*;
import com.naver_pay.mapper.DataMapper;
import com.naver_pay.mapper.RealTimeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@EnableAutoConfiguration
public class RealTimeController {

    @Autowired
    RealTimeMapper mapper;

    @RequestMapping(value = "/realtime", method = GET)
    public @ResponseBody
    ModelAndView realTimeMain(HttpServletRequest request,
                              @RequestBody HashMap<String, String> map) {

        HttpSession httpSession = request.getSession();
        UserVO userVO = (UserVO) httpSession.getAttribute("user");
        ModelAndView modelAndView = null;
        ArrayList<GraphVO> list = null;

        if (userVO == null) {
            //TODO : 사용자 세션이 없는 경우 redirect
            return new ModelAndView("redirect:/main");

        } else if (userVO.getState() == 0) {
            modelAndView = new ModelAndView("analysisUser");
            try {
                DataVO data = new DataVO(map.get("date"), map.get("payId"), map.get("userID"),
                        map.get("userBirth"), map.get("userSex"), map.get("merchantName"),
                        map.get("productName"), map.get("productType"), map.get("paymentState"),
                        map.get("paymentType"), map.get("mainPaymentType"), map.get("paymentAmount"),
                        map.get("mainPaymentAmount"), map.get("pointAmount"), map.get("receiptNumber"),
                        map.get("installmentMonth"), map.get("adId"), map.get("savedPoint"));

                mapper.insertOriginal(data);
                System.out.println(data.toString());
                saveReduced(data);
                // RealTimeVO data = new RealTimeVO(map);
                // System.out.println();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            //TODO : 권한이 없는 경우 redirect
            return new ModelAndView("redirect:/main");
        }

        return modelAndView;
    }

    private void saveReduced(DataVO data) {
        try {
            saveTotal(data);
            saveYear(data);
            saveMonth(data);
            saveDay(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void saveTotal(DataVO data) throws Exception {
        String branchName = data.getBranchName();
        String productname = data.getProductName();
        ReducedDataVO tmp = mapper.getTotal(branchName, productname);
        tmp.set(data);
        mapper.setTotal(tmp);
    }

    private void saveYear(DataVO data) throws Exception {
        String dateTmp = data.getDate().replace(".", " ");
        String[] dateString = dateTmp.split(" ");
        String yearString = dateString[0];
        String branchName = data.getBranchName();
        String productname = data.getProductName();

        String date = yearString;

        ReducedDataVO tmp = mapper.getYear(branchName, productname, date);
        tmp.set(data);
        mapper.setTotal(tmp);
    }

    private void saveMonth(DataVO data) throws Exception {
        String dateTmp = data.getDate().replace(".", " ");
        String[] dateString = dateTmp.split(" ");
        String yearString = dateString[0];
        String monthString = yearString + "." + dateString[1];
        String branchName = data.getBranchName();
        String productname = data.getProductName();

        String date = monthString;

        ReducedDataVO tmp = mapper.getMonth(branchName, productname, date);
        tmp.set(data);
        mapper.setTotal(tmp);
    }

    private void saveDay(DataVO data) throws Exception {
        String dateTmp = data.getDate().replace(".", " ");
        String[] dateString = dateTmp.split(" ");
        String yearString = dateString[0];
        String monthString = yearString + "." + dateString[1];
        String dayString = monthString + "." + dateString[2];
        String branchName = data.getBranchName();
        String productname = data.getProductName();

        String date = dayString;

        ReducedDataVO tmp = mapper.getDay(branchName, productname, date);
        tmp.set(data);
        mapper.setTotal(tmp);
    }
}
