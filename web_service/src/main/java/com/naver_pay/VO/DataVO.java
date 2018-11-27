package com.naver_pay.VO;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataVO {
    private String date;
    private String id;
    private String userId;
    private String userBirth;
    private char userSex;
    private String branchName;
    private String productName;
    private String productType;
    private String paymentState;
    private String paymentType;
    private String mainPaymentType;
    private int paymentAmount;
    private int mainPaymentAmount;
    private int pointAmount;
    private String receiptNumber;
    private int installmentMonth;
    private String adId;
    private int savedPoint;

    public DataVO(String date, String id, String userId,
                  String userBirth, char userSex, String branchName,
                  String productName, String productType, String paymentState,
                  String paymentType, String mainPaymentType, int paymentAmount,
                  int mainPaymentAmount, int pointAmount, String receiptNumber,
                  int installmentMonth, String adId, int savedPoint) {

        this.date = date;
        this.id = id;
        this.userId = userId;
        this.userBirth = userBirth;
        this.userSex = userSex;
        this.branchName = branchName;
        this.productName = productName;
        this.productType = productType;
        this.paymentState = paymentState;
        this.paymentType = paymentType;
        this.mainPaymentType = mainPaymentType;
        this.paymentAmount = paymentAmount;
        this.mainPaymentAmount = mainPaymentAmount;
        this.pointAmount = pointAmount;
        this.receiptNumber = receiptNumber;
        this.installmentMonth = installmentMonth;
        this.adId = adId;
        this.savedPoint = savedPoint;
    }

    public DataVO(String date, String id, String branchName, String userId,
                  String userBirth, String userSex,
                  String productName, String productType, String paymentState,
                  String paymentType, String mainPaymentType,
                  String paymentAmount, String mainPaymentAmount, String pointAmount, String receiptNumber,
                  String installmentMonth, String adId, String savedPoint) {

        //System.out.println(date);
        //DateFormat format = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        //DateFormat format = new SimpleDateFormat("yyyyy.MM.dd HH:mm:ss");
       //this.date = format.parse(date);
        this.date = date;
        this.id = id;
        this.userId = userId;
        this.userBirth = userBirth;
        this.userSex = userSex.charAt(0);
        this.branchName = branchName;
        this.productName = productName;
        this.productType = productType;
        this.paymentState = paymentState;
        this.paymentType = paymentType;
        this.mainPaymentType = mainPaymentType;

        if(paymentAmount.equals(""))
            this.paymentAmount = 0;
        else
            this.paymentAmount = Integer.parseInt(paymentAmount);

        if(mainPaymentAmount.equals(""))
            this.mainPaymentAmount = 0;
        else
            this.mainPaymentAmount = Integer.parseInt(mainPaymentAmount);

        if(pointAmount.equals(""))
            this.pointAmount = 0;
        else
            this.pointAmount = Integer.parseInt(pointAmount);


        this.receiptNumber = receiptNumber;

        if(installmentMonth.equals(""))
            this.installmentMonth = 0;
        else
            this.installmentMonth = Integer.parseInt(installmentMonth);

        this.adId = adId;

        if(savedPoint.equals(""))
            this.savedPoint = 0;
        else
            this.savedPoint = Integer.parseInt(savedPoint);
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserBirth() {
        return userBirth;
    }

    public void setUserBirth(String userBirth) {
        this.userBirth = userBirth;
    }

    public char getUserSex() {
        return userSex;
    }

    public void setUserSex(char userSex) {
        this.userSex = userSex;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getPaymentState() {
        return paymentState;
    }

    public void setPaymentState(String paymentState) {
        this.paymentState = paymentState;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getMainPaymentType() {
        return mainPaymentType;
    }

    public void setMainPaymentType(String mainPaymentType) {
        this.mainPaymentType = mainPaymentType;
    }

    public int getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(int paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public int getMainPaymentAmount() {
        return mainPaymentAmount;
    }

    public void setMainPaymentAmount(int mainPaymentAmount) {
        this.mainPaymentAmount = mainPaymentAmount;
    }

    public int getPointAmount() {
        return pointAmount;
    }

    public void setPointAmount(int pointAmount) {
        this.pointAmount = pointAmount;
    }

    public String getReceiptNumber() {
        return receiptNumber;
    }

    public void setReceiptNumber(String receiptNumber) {
        this.receiptNumber = receiptNumber;
    }

    public int getInstallmentMonth() {
        return installmentMonth;
    }

    public void setInstallmentMonth(int installmentMonth) {
        this.installmentMonth = installmentMonth;
    }

    public String getAdId() {
        return adId;
    }

    public void setAdId(String adId) {
        this.adId = adId;
    }

    public int getSavedPoint() {
        return savedPoint;
    }

    public void setSavedPoint(int savedPoint) {
        this.savedPoint = savedPoint;
    }

    @Override
    public String toString() {
        return "DataVO{" +
                "date=" + date +
                ", id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", userBirth='" + userBirth + '\'' +
                ", userSex='" + userSex + '\'' +
                ", branchName='" + branchName + '\'' +
                ", productName='" + productName + '\'' +
                ", productType='" + productType + '\'' +
                ", paymentState='" + paymentState + '\'' +
                ", paymentType='" + paymentType + '\'' +
                ", mainPaymentType='" + mainPaymentType + '\'' +
                ", paymentAmount=" + paymentAmount +
                ", mainPaymentAmount=" + mainPaymentAmount +
                ", pointAmount=" + pointAmount +
                ", receiptNumber='" + receiptNumber + '\'' +
                ", installmentMonth=" + installmentMonth +
                ", adId='" + adId + '\'' +
                ", savedPoint=" + savedPoint +
                '}';
    }
}
