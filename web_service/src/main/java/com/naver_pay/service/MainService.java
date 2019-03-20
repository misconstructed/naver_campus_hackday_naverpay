package com.naver_pay.service;

import com.naver_pay.VO.AnalysisVO;
import com.naver_pay.VO.DataVO;
import com.naver_pay.VO.ReducedDataVO;
import com.naver_pay.mapper.DataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

@Service
public class MainService {

    @Autowired
    private DataMapper dataMapper;

    public AnalysisVO getTotal() {
        ArrayList<ReducedDataVO> list = null;
        AnalysisVO analysisVO = null;
        try {
            list =  dataMapper.getTotal();
            analysisVO = new AnalysisVO(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return analysisVO;
    }

    public AnalysisVO getFiltered(String branchName) {
        ArrayList<ReducedDataVO> list =null;
        AnalysisVO analysisVO = null;

        try {
            list = dataMapper.getFiltered(branchName);
            analysisVO = new AnalysisVO(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return analysisVO;
    }

    public void saveCsv() {
        dbThread thread = new dbThread();
        thread.run();
    }

    private class dbThread extends Thread {
        private CsvReader reader;
        private HashMap<String, ReducedDataVO> total;
        private HashMap<String, ReducedDataVO> month;
        private HashMap<String, ReducedDataVO> day;
        private HashMap<String, ReducedDataVO> year;
        private ArrayList<DataVO> list;

        @Override
        public void run() {
            super.run();
            reader = new CsvReader("static/csv/data.csv");
            list = reader.getList();
            total = reader.getMap();
            day = reader.getDay();
            month = reader.getMonth();
            year = reader.getYear();

            //원본 데이터 저장
            saveOriginal(list);

            //데이터 디비에 저장
            saveData(day, "day");
            saveData(month, "month");
            saveData(year, "year");
            saveData(total, "total");
        }
    }

    private void saveOriginal(ArrayList<DataVO> list) {
        DataVO data;

        Iterator<DataVO> iterator = list.iterator();
        while (iterator.hasNext()) {
            data = iterator.next();
            try {
                dataMapper.insertOriginal(data);
            } catch (DuplicateKeyException e) {
                break;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void saveData(HashMap<String, ReducedDataVO> map, String option) {
        ReducedDataVO data;
        String key;
        Iterator<String> iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            key = iterator.next();
            data = map.get(key);
            try {
                if (option.equals("day")) {
                    dataMapper.insertDay(data);
                } else if (option.equals("month")) {
                    dataMapper.insertMonth(data);

                } else if (option.equals("year")) {
                    dataMapper.insertYear(data);

                } else if (option.equals("total")) {
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
}
