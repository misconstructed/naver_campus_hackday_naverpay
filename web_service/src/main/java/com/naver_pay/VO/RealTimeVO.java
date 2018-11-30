package com.naver_pay.VO;

public class RealTimeVO {
    private String date;
    private String payId;
    private String userId;
    private String userBirth;
    private String userSex;
    private String merchantName;
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

    public RealTimeVO() {

    }
    public RealTimeVO(String date, String payId, String userId, String userBirth, String userSex, String merchantName, String productName, String productType, String paymentState, String paymentType, String mainPaymentType, int paymentAmount, int mainPaymentAmount, int pointAmount, String receiptNumber, int installmentMonth, String adId, int savedPoint) {
        this.date = date;
        this.payId = payId;
        this.userId = userId;
        this.userBirth = userBirth;
        this.userSex = userSex;
        this.merchantName = merchantName;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPayId() {
        return payId;
    }

    public void setPayId(String payId) {
        this.payId = payId;
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

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
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
        return "RealTimeVO{" +
                "date='" + date + '\'' +
                ", payId='" + payId + '\'' +
                ", userId='" + userId + '\'' +
                ", userBirth='" + userBirth + '\'' +
                ", userSex='" + userSex + '\'' +
                ", merchantName='" + merchantName + '\'' +
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
