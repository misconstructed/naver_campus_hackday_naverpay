package com.naver_pay.VO;

import com.naver_pay.VO.DataVO;

public class ReducedDataVO {
    private String date;
    private String branchName;
    private String productName;
    private int count;
    private int maxPayment;
    private int minPayment;
    private int totalPayment;
    private int adCount;
    private int adTotalPayment;
    private int adMaxPayment;
    private int adMinPayment;

    public ReducedDataVO() { }

    public ReducedDataVO(String branchName, String productName) {
        this.date = null;
        this.branchName = branchName;
        this.productName = productName;
        count = 0;
        maxPayment = 0;
        minPayment = 0;
        totalPayment = 0;
        adCount = 0;
        adTotalPayment = 0;
        adMaxPayment = 0;
        adMinPayment = 0;
    }

    public ReducedDataVO(String date, String branchName, String productName) {
        this.date = date;
        this.branchName = branchName;
        this.productName = productName;
        count = 0;
        maxPayment = 0;
        minPayment = 0;
        totalPayment = 0;
        adCount = 0;
        adTotalPayment = 0;
        adMaxPayment = 0;
        adMinPayment = 0;
    }

    public void set(DataVO data) {
        if(data.getPaymentState().equals("결제")) {
            increaseCount();
            checkMaxPayment(data.getPaymentAmount() + data.getPointAmount());
            checkMinPayment(data.getPaymentAmount() + data.getPointAmount());
            addPayment(data.getPaymentAmount() + data.getPointAmount());

            //광고id가 존재하는 경우
            if(!data.getAdId().equals("")) {
                increaseAdCount();
                checkMaxAdPayment(data.getPaymentAmount()+ data.getPointAmount());
                checkMinAdPayment(data.getPaymentAmount()+ data.getPointAmount());
                addAdPayment(data.getPaymentAmount()+ data.getPointAmount());
            }
        }
        else if(data.getPaymentState().equals("부분취소")) {
           subPayment(data.getPaymentAmount()+ data.getPointAmount());
           //광고id 가 존재하는 경우
            if(!data.getAdId().equals("")) {
                subAdPayment(data.getPaymentAmount()+ data.getPointAmount());
            }
        }
        else if(data.getPaymentState().equals("전체취소")) {
            decreaseCount();
            subPayment(data.getPaymentAmount()+ data.getPointAmount());

            //광고id가 존재하는 경우
            if(!data.getAdId().equals("")) {
                decreaseAdCount();
                subAdPayment(data.getPaymentAmount()+ data.getPointAmount());
            }
       }
    }

    //전체 count 증가
    private int increaseCount() {
        count++;
        return count;
    }

    private int decreaseCount() {
        count--;
        if(count < 0)
            count = 0;
        return count;
    }

    //광고 횟수 증가
    private int increaseAdCount() {
        adCount++;
        return  adCount;
    }

    private int decreaseAdCount() {
        adCount--;
        if(adCount < 0)
            adCount = 0;
        return adCount;
    }

    //최대 결제 금액 확인
    private boolean checkMaxPayment(int input) {
        if(input > maxPayment) {
            maxPayment = input;
            return true;
        }
        else
            return false;
    }

    //최소 결제 금액 확인
    private boolean checkMinPayment(int input) {
        if(minPayment == 0) {
            minPayment = input;
            return true;
        }
        else if(input < minPayment) {
            minPayment = input;
            return true;
        }
        else
            return false;
    }

    //결제 한 경우 전체 결제 금액에 포함
    private int addPayment(int input) {
        totalPayment += input;
        return totalPayment;
    }

    //취소 또는 부분취소 한 경우 전체 결제 금액에서 제외
    private int subPayment(int input) {
        totalPayment -= input;
        return totalPayment;
    }

    private int addAdPayment(int input) {
        adTotalPayment += input;
        return adTotalPayment;
    }

    private int subAdPayment(int input) {
        adTotalPayment -= input;
        return adTotalPayment;
    }

    private boolean checkMinAdPayment(int input) {
        if(adMinPayment == 0) {
            adMinPayment = input;
            return true;
        }
        else if(input < adMinPayment) {
            adMinPayment = input;
            return true;
        }
        else
            return false;
    }

    private boolean checkMaxAdPayment(int input) {
        if(input > adMaxPayment) {
            adMaxPayment = input;
            return true;
        }
        else
            return false;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getMaxPayment() {
        return maxPayment;
    }

    public void setMaxPayment(int maxPayment) {
        this.maxPayment = maxPayment;
    }

    public int getMinPayment() {
        return minPayment;
    }

    public void setMinPayment(int minPayment) {
        this.minPayment = minPayment;
    }

    public int getTotalPayment() {
        return totalPayment;
    }

    public void setTotalPayment(int totalPayment) {
        this.totalPayment = totalPayment;
    }

    public int getAdCount() {
        return adCount;
    }

    public void setAdCount(int adCount) {
        this.adCount = adCount;
    }

    public int getAdTotalPayment() {
        return adTotalPayment;
    }

    public void setAdTotalPayment(int adTotalPayment) {
        this.adTotalPayment = adTotalPayment;
    }

    public int getAdMaxPayment() {
        return adMaxPayment;
    }

    public void setAdMaxPayment(int adMaxPayment) {
        this.adMaxPayment = adMaxPayment;
    }

    public int getAdMinPayment() {
        return adMinPayment;
    }

    public void setAdMinPayment(int adMinPayment) {
        this.adMinPayment = adMinPayment;
    }

    @Override
    public String toString() {
        return "ReducedDataVO{" +
                "date='" + date + '\'' +
                ", branchName='" + branchName + '\'' +
                ", count=" + count +
                ", maxPayment=" + maxPayment +
                ", minPayment=" + minPayment +
                ", totalPayment=" + totalPayment +
                ", adCount=" + adCount +
                ", adTotalPayment=" + adTotalPayment +
                ", adMaxPayment=" + adMaxPayment +
                ", adMinPayment=" + adMinPayment +
                '}';
    }
}
