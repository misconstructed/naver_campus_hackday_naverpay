package com.naver_pay.VO;

public class ResultVO {
    private String status;
    private String massage;

    public ResultVO() {}

    public ResultVO(String status) {
        this.status = status;
    }

    public ResultVO(String status, String massage) {
        this.status = status;
        this.massage = massage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }

    @Override
    public String toString() {
        return "ResultVO{" +
                "status='" + status + '\'' +
                ", massage='" + massage + '\'' +
                '}';
    }
}
