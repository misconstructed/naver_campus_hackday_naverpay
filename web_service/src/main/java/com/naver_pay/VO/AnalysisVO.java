package com.naver_pay.VO;

import java.util.ArrayList;

public class AnalysisVO {
    private ArrayList<ReducedDataVO> list;
    private long total;
    private long adTotal;
    private ArrayList<String> branchList;
    private ArrayList<String> dateList;

    public AnalysisVO() { }

    public AnalysisVO(ArrayList<ReducedDataVO> list) {
        this.list = list;
        total = 0;
        adTotal = 0;
        branchList = new ArrayList<>();
        dateList = new ArrayList<>();
        makeTotal();
        makeList();
    }

    public ArrayList<ReducedDataVO> getList() {
        return list;
    }

    public void setList(ArrayList<ReducedDataVO> list) {
        this.list = list;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getAdTotal() {
        return adTotal;
    }

    public void setAdTotal(long adTotal) {
        this.adTotal = adTotal;
    }

    public ArrayList<String> getBranchList() {
        return branchList;
    }

    public void setBranchList(ArrayList<String> branchList) {
        this.branchList = branchList;
    }

    public ArrayList<String> getDateList() {
        return dateList;
    }

    public void setDateList(ArrayList<String> dateList) {
        this.dateList = dateList;
    }


    private void makeTotal() {
        int i;
        ReducedDataVO data;
        for(i = 0; i < list.size(); i++) {
            data = list.get(i);
            total += data.getTotalPayment();
            adTotal += data.getAdTotalPayment();
        }
    }

    private void makeList() {
        int i;
        ReducedDataVO data;
        for(i = 0; i < list.size(); i++) {
            data = list.get(i);
            if(!branchList.contains(data.getBranchName()))
                branchList.add(data.getBranchName());
            if(!dateList.contains(data.getDate()))
                dateList.add(data.getDate());

        }
    }
}
