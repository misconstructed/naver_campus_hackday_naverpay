package com.naver_pay.service;

import com.naver_pay.VO.AnalysisVO;
import com.naver_pay.VO.ReducedDataVO;
import com.naver_pay.mapper.DataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ProductService {

    @Autowired
    private DataMapper dataMapper;

    public AnalysisVO getData(String branchName, String year, String month, String day) {
        AnalysisVO analysisVO = null;
        String date = null;

        try {
            if (year == null) {
                if (branchName == null || branchName.equals("default")) {
                    ArrayList<ReducedDataVO> list = dataMapper.getTotalProduct();
                    changeDate(list);
                    analysisVO = new AnalysisVO(list);
                } else {
                    ArrayList<ReducedDataVO> list = dataMapper.getFilteredProduct(branchName);
                    changeDate(list);
                    analysisVO = new AnalysisVO(list);
                }

            } else if (month == null) {
                date = year;
                if (branchName == null || branchName.equals("default")) {
                    ArrayList<ReducedDataVO> list = dataMapper.getYearlyProduct(date);
                    analysisVO = new AnalysisVO(list);
                } else {
                    ArrayList<ReducedDataVO> list = dataMapper.getYearlyBranchFilteredProduct(branchName, date);
                    analysisVO = new AnalysisVO(list);
                }

            } else if (day == null) {
                date = year + "." + month;
                if (branchName == null || branchName.equals("default")) {
                    ArrayList<ReducedDataVO> list = dataMapper.getMonthlyProduct(date);
                    analysisVO = new AnalysisVO(list);
                } else {
                    ArrayList<ReducedDataVO> list = dataMapper.getMonthlyBranchFilteredProduct(branchName, date);
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

    public ArrayList<String> getBranch() {
        ArrayList<String> branchList = null;

        try {
            branchList = dataMapper.getBranchList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return branchList;
    }


    private void changeDate(ArrayList<ReducedDataVO> list) {
        int i;
        ReducedDataVO data;
        for (i = 0; i < list.size(); i++) {
            data = list.get(i);
            data.setDate("-");
        }
    }
}
