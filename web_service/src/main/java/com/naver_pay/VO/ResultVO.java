package com.naver_pay.VO;

public class ResultVO {
    private String status;
    private Object object;

    public ResultVO() {}

    public ResultVO(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    @Override
    public String toString() {
        return "ResultVO{" +
                "status='" + status + '\'' +
                ", object=" + object +
                '}';
    }
}
