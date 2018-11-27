package com.naver_pay.service;

import com.naver_pay.VO.DataVO;
import com.naver_pay.VO.ReducedDataVO;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.*;

public class CsvReader {
    private String path;
    private File file;
    private BufferedReader br;
    private ArrayList<DataVO> list;
    private HashMap<String, ReducedDataVO> map;
    private HashMap<String, ReducedDataVO> day;
    private HashMap<String, ReducedDataVO> year;
    private HashMap<String, ReducedDataVO> month;
    private long total;

    public CsvReader() { }

    public CsvReader(String path) {
        this.path = path;

       //System.out.println(path);
        list = new ArrayList<DataVO>();
        day = new HashMap<String, ReducedDataVO>();
        year = new HashMap<String, ReducedDataVO>();
        month = new HashMap<String, ReducedDataVO>();
        map = new HashMap<String, ReducedDataVO>();

        try {
            file = ResourceUtils.getFile("classpath:"+path);
            br  =  new BufferedReader(new InputStreamReader(new FileInputStream(file),"euc-kr"));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        read();
    }

    private void read(){
        String line;
        String[] field;
        String split = ",";

        try {
            br.readLine();
            while((line = br.readLine()) != null) {
                field = line.split(split, -1);

                String[]tmp = field[0].split(" ");

                DataVO dataVO = new DataVO(field[0], field[1], field[2], field[3], field[4],
                        field[5], field[6], field[7], field[8], field[9], field[10],
                        field[11], field[12], field[13],field[14], field[15], field[16], field[17]);
                list.add(dataVO);

                reduce(dataVO);
                dayReduce(dataVO, tmp[0]);
                monthReduce(dataVO, tmp[0]);
                yearReduce(dataVO, tmp[0]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void reduce(DataVO data) {
        ReducedDataVO tmp;
        String key = data.getBranchName()+data.getProductName();
        if((tmp = map.get(key)) != null) {
            tmp.set(data);
        }
        //최초 날짜인 경우
        else {
            tmp = new ReducedDataVO(data.getBranchName(), data.getProductName());
            tmp.set(data);
            map.put(key, tmp);
        }

    }

    private void dayReduce(DataVO data, String date) {
        ReducedDataVO tmp;
        String key = date+data.getBranchName()+data.getProductName();
        if((tmp = day.get(key)) != null) {
            tmp.set(data);
        }
        //최초 날짜인 경우
        else {
            tmp = new ReducedDataVO(date, data.getBranchName(), data.getProductName());
            tmp.set(data);
            day.put(key, tmp);
        }

    }

    private void monthReduce(DataVO data, String date) {

        date = date.replace(".", " ");
        String[] split = date.split(" ");
        String yearString = split[0];
        String monthString = split[1];
        String dateString = yearString+"."+monthString;

        ReducedDataVO tmp;
        String key = dateString+data.getBranchName()+data.getProductName();
        if((tmp = month.get(key)) != null) {
            tmp.set(data);
        }
        //최초 날짜인 경우
        else {
            tmp = new ReducedDataVO(dateString, data.getBranchName(), data.getProductName());
            tmp.set(data);
            month.put(key, tmp);
        }

    }

    private void yearReduce(DataVO data, String date) {
        date = date.replace(".", " ");
        String[] dateString = date.split(" ");
        String yearString = dateString[0];
        ReducedDataVO tmp;
        String key = yearString+data.getBranchName()+data.getProductName();
        if((tmp = year.get(key)) != null) {
            tmp.set(data);
        }
        //최초 날짜인 경우
        else {
            tmp = new ReducedDataVO(yearString, data.getBranchName(), data.getProductName());
            tmp.set(data);
            year.put(key, tmp);
        }
    }

    public HashMap<String, ReducedDataVO> getMap() {
        return map;
    }

    public HashMap<String, ReducedDataVO> getYear() {
        return year;
    }


    public HashMap<String, ReducedDataVO> getMonth() {
        return month;
    }

    public HashMap<String, ReducedDataVO> getDay() {
        return day;
    }

    public ArrayList<DataVO> getList() {
        return list;
    }

    public long getTotal() {
        ReducedDataVO data;
        String key;
        Iterator<String> iterator = map.keySet().iterator();
        total = 0;

        while(iterator.hasNext()) {
            key = iterator.next();
            data = map.get(key);
            total += data.getTotalPayment();
        }
        return total;
    }
}
