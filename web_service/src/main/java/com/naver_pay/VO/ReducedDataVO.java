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
    private int male;
    private int female;
    private int pointCount;
    private int maxPointPayment;
    private int minPointPayment;
    private int totalPointPayment;
    private int installmentCount;
    private int minInstallment;
    private int maxInstallment;
    private int totalInstallment;
    private int savedPointCount;
    private int maxSavedPoint;
    private int minSavedPoint;
    private int totalSavedPoint;

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

        male = 0;
        female = 0;

        pointCount = 0;
        minPointPayment = 0;
        maxPointPayment = 0;
        totalPointPayment = 0;

        installmentCount = 0;
        maxInstallment = 0;
        minInstallment = 0;
        totalInstallment = 0;

        savedPointCount = 0 ;
        maxSavedPoint = 0;
        minSavedPoint = 0;
        totalSavedPoint = 0;
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

        male = 0;
        female = 0;

        pointCount = 0;
        minPointPayment = 0;
        maxPointPayment = 0;
        totalPointPayment = 0;

        installmentCount = 0;
        maxInstallment = 0;
        minInstallment = 0;
        totalInstallment = 0;

        savedPointCount = 0 ;
        maxSavedPoint = 0;
        minSavedPoint = 0;
        totalSavedPoint = 0;
    }

    public void set(DataVO data) {
        if(data.getPaymentState().equals("결제")) {
            increaseCount();
            checkMaxPayment(data.getPaymentAmount() + data.getPointAmount());
            checkMinPayment(data.getPaymentAmount() + data.getPointAmount());
            addPayment(data.getPaymentAmount() + data.getPointAmount());

            //성별 통계(증가)
            //남성인 경우
            if(data.getUserSex() == 'm')
                increaseMale();
            //여성인 경우
            else
                increaseFemale();

            //포인트를 사용한 경우
            if(data.getPointAmount() != 0) {
                increasePointCount();
                checkMaxPointPayment(data.getPointAmount());
                checkMinPointPayment(data.getPointAmount());
                addPointPayment(data.getPointAmount());
            }
            //할부를 한 경우
            if(data.getInstallmentMonth() != 0) {
                increaseInstallmentCount();
                checkMaxInstallment(data.getInstallmentMonth());
                checkMinInstallment(data.getInstallmentMonth());
                addTotalInstallment(data.getInstallmentMonth());

            }
            //적립 포인트가 존재하는 경우
            if(data.getSavedPoint() != 0) {
                increaseSavedPointCount();
                checkMaxSavedPoint(data.getSavedPoint());
                checkMinSavedPoint(data.getSavedPoint());
                addTotalSavedPoint(data.getSavedPoint());
            }

            //광고id가 존재하는 경우
            if(!data.getAdId().equals("")) {
                increaseAdCount();
                checkMaxAdPayment(data.getPaymentAmount()+ data.getPointAmount());
                checkMinAdPayment(data.getPaymentAmount()+ data.getPointAmount());
                addAdPayment(data.getPaymentAmount()+ data.getPointAmount());
            }
        }

        //부분취소
        else if(data.getPaymentState().equals("부분취소")) {
           subPayment(data.getPaymentAmount()+ data.getPointAmount());
           //광고id 가 존재하는 경우
            if(!data.getAdId().equals("")) {
                subAdPayment(data.getPaymentAmount()+ data.getPointAmount());
            }

            //포인트를 사용한 경우
            if(data.getPointAmount() != 0) {
                subPointPayment(data.getPointAmount());
            }
            //적립 포인트가 존재하는 경우
            if(data.getSavedPoint() != 0) {
                subTotalSavedPoint(data.getSavedPoint());
            }

        }

        //전체 취소
        else if(data.getPaymentState().equals("전체취소")) {
            decreaseCount();
            subPayment(data.getPaymentAmount()+ data.getPointAmount());


            //성별 통계(감소)
            //남성인 경우
            if(data.getUserSex() == 'm')
                decreaseMale();
                //여성인 경우
            else
                decreaseFemale();

            //광고id가 존재하는 경우
            if(!data.getAdId().equals("")) {
                decreaseAdCount();
                subAdPayment(data.getPaymentAmount()+ data.getPointAmount());
            }

            //포인트를 사용한 경우
            if(data.getPointAmount() != 0) {
                decreasePointCount();
                subPointPayment(data.getPointAmount());
            }
            //할부를 한 경우
            if(data.getInstallmentMonth() != 0) {
                decreaseInstallmentCount();
                subTotalInstallment(data.getInstallmentMonth());
            }
            //적립 포인트가 존재하는 경우
            if(data.getSavedPoint() != 0) {
                decreaseSavedPointCount();
                subTotalSavedPoint(data.getSavedPoint());
            }

        }
    }

    private int increaseSavedPointCount() {
        savedPointCount++;
        return savedPointCount;
    }

    private int decreaseSavedPointCount() {
        savedPointCount--;
        if(savedPointCount < 0)
            savedPointCount = 0;
        return savedPointCount;
    }

    private int increaseInstallmentCount() {
        installmentCount++;
        return installmentCount;
    }

    private int decreaseInstallmentCount() {
        installmentCount--;
        if(installmentCount < 0)
            installmentCount = 0;
        return installmentCount;
    }

    private int increaseMale() {
        male++;
        return male;
    }

    private int decreaseMale() {
        male--;
        if(male < 0)
            male = 0;
        return male;
    }

    private int increaseFemale() {
        female++;
        return female;
    }

    private int decreaseFemale() {
        female--;
        if(female < 0)
            female = 0;
        return female;
    }

    private int increasePointCount() {
        pointCount++;
        return pointCount;
    }

    private int decreasePointCount() {
        pointCount--;
        if(pointCount < 0)
            pointCount = 0;
        return pointCount;
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

    private boolean checkMaxPointPayment(int input) {
        if(input > maxPointPayment) {
            maxPointPayment = input;
            return true;
        }
        else
            return false;
    }

    private boolean checkMinPointPayment(int input) {
        if(minPointPayment == 0) {
            minPointPayment = input;
            return true;
        }
        else if(input < minPointPayment) {
            minPointPayment = input;
            return true;
        }
        else
            return false;
    }

    private int addPointPayment(int input) {
        totalPointPayment += input;
        return totalPointPayment;
    }

    private int subPointPayment(int input) {
        totalPointPayment -= input;
        return totalPointPayment;
    }

    private boolean checkMaxInstallment(int input) {
        if(input > maxInstallment) {
            maxInstallment = input;
            return true;
        }
        else
            return false;
    }

    private boolean checkMinInstallment(int input) {
        if(minInstallment == 0) {
            minInstallment = input;
            return true;
        }
        else if(input < minInstallment) {
            minInstallment = input;
            return true;
        }
        else
            return false;
    }

    private int addTotalInstallment(int input) {
        totalInstallment += input;
        return totalInstallment;
    }

    private int subTotalInstallment(int input) {
        totalInstallment -= input;
        return totalInstallment;
    }


    private boolean checkMaxSavedPoint(int input) {
        if(input > maxSavedPoint) {
            maxSavedPoint = input;
            return true;
        }
        else
            return false;
    }

    private boolean checkMinSavedPoint(int input) {
        if(minSavedPoint == 0) {
            minSavedPoint = input;
            return true;
        }
        else if(input < minSavedPoint) {
            minSavedPoint = input;
            return true;
        }
        else
            return false;
    }

    private int addTotalSavedPoint(int input) {
        totalSavedPoint += input;
        return totalSavedPoint;
    }

    private int subTotalSavedPoint(int input) {
        totalSavedPoint -= input;
        return totalSavedPoint;
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

    public int getMale() {
        return male;
    }

    public void setMale(int male) {
        this.male = male;
    }

    public int getFemale() {
        return female;
    }

    public void setFemale(int female) {
        this.female = female;
    }

    public int getPointCount() {
        return pointCount;
    }

    public void setPointCount(int pointCount) {
        this.pointCount = pointCount;
    }

    public int getMaxPointPayment() {
        return maxPointPayment;
    }

    public void setMaxPointPayment(int maxPointPayment) {
        this.maxPointPayment = maxPointPayment;
    }

    public int getMinPointPayment() {
        return minPointPayment;
    }

    public void setMinPointPayment(int minPointPayment) {
        this.minPointPayment = minPointPayment;
    }

    public int getTotalPointPayment() {
        return totalPointPayment;
    }

    public void setTotalPointPayment(int totalPointPayment) {
        this.totalPointPayment = totalPointPayment;
    }

    public int getInstallmentCount() {
        return installmentCount;
    }

    public void setInstallmentCount(int installmentCount) {
        this.installmentCount = installmentCount;
    }

    public int getMinInstallment() {
        return minInstallment;
    }

    public void setMinInstallment(int minInstallment) {
        this.minInstallment = minInstallment;
    }

    public int getMaxInstallment() {
        return maxInstallment;
    }

    public void setMaxInstallment(int maxInstallment) {
        this.maxInstallment = maxInstallment;
    }

    public int getTotalInstallment() {
        return totalInstallment;
    }

    public void setTotalInstallment(int totalInstallment) {
        this.totalInstallment = totalInstallment;
    }

    public int getSavedPointCount() {
        return savedPointCount;
    }

    public void setSavedPointCount(int savedPointCount) {
        this.savedPointCount = savedPointCount;
    }

    public int getMaxSavedPoint() {
        return maxSavedPoint;
    }

    public void setMaxSavedPoint(int maxSavedPoint) {
        this.maxSavedPoint = maxSavedPoint;
    }

    public int getMinSavedPoint() {
        return minSavedPoint;
    }

    public void setMinSavedPoint(int minSavedPoint) {
        this.minSavedPoint = minSavedPoint;
    }

    public int getTotalSavedPoint() {
        return totalSavedPoint;
    }

    public void setTotalSavedPoint(int totalSavedPoint) {
        this.totalSavedPoint = totalSavedPoint;
    }

    @Override
    public String toString() {
        return "ReducedDataVO{" +
                "date='" + date + '\'' +
                ", branchName='" + branchName + '\'' +
                ", productName='" + productName + '\'' +
                ", count=" + count +
                ", maxPayment=" + maxPayment +
                ", minPayment=" + minPayment +
                ", totalPayment=" + totalPayment +
                ", adCount=" + adCount +
                ", adTotalPayment=" + adTotalPayment +
                ", adMaxPayment=" + adMaxPayment +
                ", adMinPayment=" + adMinPayment +
                ", male=" + male +
                ", female=" + female +
                ", pointCount=" + pointCount +
                ", maxPointPayment=" + maxPointPayment +
                ", minPointPayment=" + minPointPayment +
                ", totalPointPayment=" + totalPointPayment +
                ", installmentCount=" + installmentCount +
                ", minInstallment=" + minInstallment +
                ", maxInstallment=" + maxInstallment +
                ", totalInstallment=" + totalInstallment +
                ", savedPointCount=" + savedPointCount +
                ", maxSavedPoint=" + maxSavedPoint +
                ", minSavedPoint=" + minSavedPoint +
                ", totalSavedPoint=" + totalSavedPoint +
                '}';
    }
}
