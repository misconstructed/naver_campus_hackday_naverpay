package com.naver_pay.service;

import com.naver_pay.VO.AnalysisVO;
import com.naver_pay.VO.ReducedDataVO;
import com.naver_pay.mapper.DataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class BranchService {

    @Autowired
    private DataMapper dataMapper;

    public AnalysisVO getData(String branchName, String year, String month, String day) {
        String date = null;
        AnalysisVO analysisVO = null;
        try {
            if (year == null) {
                System.out.println(branchName);
                if (branchName == null || branchName.equals("default")) {
                    ArrayList<ReducedDataVO> list = dataMapper.getTotal();
                    changeDate(list);
                    analysisVO = new AnalysisVO(list);
                } else {
                    ArrayList<ReducedDataVO> list = dataMapper.getFiltered(branchName);
                    changeDate(list);
                    analysisVO = new AnalysisVO(list);
                }

            } else if (month == null) {
                date = year;
                if (branchName == null || branchName.equals("default")) {
                    ArrayList<ReducedDataVO> list = dataMapper.getYearly(date);
                    analysisVO = new AnalysisVO(list);
                } else {
                    ArrayList<ReducedDataVO> list = dataMapper.getYearlyBranchFiltered(branchName, date);
                    analysisVO = new AnalysisVO(list);
                }

            } else if (day == null) {
                date = year + "." + month;
                if (branchName == null || branchName.equals("default")) {
                    ArrayList<ReducedDataVO> list = dataMapper.getMonthly(date);
                    analysisVO = new AnalysisVO(list);
                } else {
                    ArrayList<ReducedDataVO> list = dataMapper.getMonthlyBranchFiltered(branchName, date);
                    analysisVO = new AnalysisVO(list);
                }

            } else {
                date = year + "." + month + "." + day;
                if (branchName == null || branchName.equals("default")) {
                    ArrayList<ReducedDataVO> list = dataMapper.getDaily(date);
                    analysisVO = new AnalysisVO(list);
                } else {
                    ArrayList<ReducedDataVO> list = dataMapper.getDailyBranchFiltered(branchName, date);
                    analysisVO = new AnalysisVO(list);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return analysisVO;
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
