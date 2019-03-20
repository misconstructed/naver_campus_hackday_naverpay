package com.naver_pay.VO;

public class GraphVO {
    private String date;
    private int amount;

    public GraphVO() {
    }

    public GraphVO(String date, int amount) {
        this.date = date;
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "GraphVO{" +
                "date='" + date + '\'' +
                ", amount=" + amount +
                '}';
    }
}
