package com.naver_pay.controller;

import com.naver_pay.VO.AnalysisVO;
import com.naver_pay.VO.DataVO;
import com.naver_pay.VO.UserVO;
import com.naver_pay.mapper.DataMapper;
import com.naver_pay.VO.ReducedDataVO;
import com.naver_pay.service.CsvReader;
import org.springframework.beans.factory.annotation.Autowired;
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
public class MainWebController {

    @Autowired
    private DataMapper dataMapper;

    private CsvReader reader;
    private HashMap<String, ReducedDataVO> total;
    private HashMap<String, ReducedDataVO> month;
    private HashMap<String, ReducedDataVO> day;
    private HashMap<String, ReducedDataVO> year;
    private AnalysisVO analysisVO;

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public @ResponseBody ModelAndView main(HttpServletRequest request,
                      @RequestParam(value = "branchName", required = false)String branchName) {
        ModelAndView modelAndView = null;
        HttpSession httpSession = request.getSession();
        UserVO userVO = (UserVO)httpSession.getAttribute("user");

        if(userVO == null) {
            Thread thread = new dbThread();
            thread.run();

            modelAndView = new ModelAndView("login");
        }

        //TODO : 기존에 로그인 한 경우 구현
        //기존에 관리자로 로그인 함
        else if(userVO.getState() == 0) {
            modelAndView = new ModelAndView("mainUser");
            //list = new ArrayList<>();
            if(branchName == null || branchName.equals("default")) {
                System.out.println("branch name == null");

                try {
                    ArrayList<ReducedDataVO> list = dataMapper.getTotal();
                    System.out.println(list.size()+"");
                    analysisVO = new AnalysisVO(list);
                    modelAndView.addObject("data", analysisVO);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            //branch로 검색한 경우
            else {
                System.out.println("branch name != null");

                try {
                    ArrayList<ReducedDataVO> list = dataMapper.getFiltered(branchName);
                    System.out.println(list.size()+"");
                    analysisVO = new AnalysisVO(list);
                    modelAndView.addObject("data", analysisVO);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                //modelAndView = filter(modelAndView, branchName);
            }
        }
        //기존에 가맹점주로 로그인 함
        else if(userVO.getState() == 1) {

        }
        //기존에 일반 사용자로 로그인 함
        else if(userVO.getState() == 2) {

        }
        //return "hello world";
        return modelAndView;
    }

    private void saveData(HashMap<String, ReducedDataVO> map, String option){
        ReducedDataVO data;
        String key;
        Iterator<String> iterator = map.keySet().iterator();
        while(iterator.hasNext()) {
            key = iterator.next();
            data = map.get(key);
            try {
                if(option.equals("day")) {
                    dataMapper.insertDay(data);
                } else if(option.equals("month")) {
                    dataMapper.insertMonth(data);

                } else if(option.equals("year")) {
                    dataMapper.insertYear(data);

                } else if(option.equals("total")) {
                    dataMapper.insertTotal(data);

                }
            } catch (DuplicateKeyException e) {
                System.out.println("duplicate input");
                break;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private class dbThread extends Thread {
        @Override
        public void run() {
            super.run();
            reader = new CsvReader("static/csv/data.csv");
            ArrayList<DataVO> list = reader.getList();

            total = reader.getMap();
            day = reader.getDay();
            month = reader.getMonth();
            year = reader.getYear();

            System.out.println("original size : "+ list.size());
            System.out.println("reduced total size : "+total.size());
            System.out.println("reduced year size : "+ year.size());
            System.out.println("reduced month size : "+ month.size());
            System.out.println("reduced day size : "+ day.size());

            //데이터 디비에 저장
            saveData(day, "day");
            saveData(month, "month");
            saveData(year, "year");
            saveData(total, "total");
        }
    }
}
