package com.naver_pay.mapper;

import com.naver_pay.VO.DataVO;
import com.naver_pay.VO.GraphVO;
import com.naver_pay.VO.ReducedDataVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

@Mapper
public interface DataMapper {

    @Insert("insert into original values (\"${date}\", \"${id}\", \"${userId}\", \"${userBirth}\"," +
            "\"${userSex}\", \"${branchName}\", \"${productName}\", \"${productType}\"," +
            "\"${paymentState}\", \"${paymentType}\", \"${mainPaymentType}\" , \"${paymentAmount}\" ," +
            "\"${mainPaymentAmount}\", \"${pointAmount}\", \"${receiptNumber}\", \"${installmentMonth}\", " +
            "\"${adId}\", \"${savedPoint}\")")
    public boolean insertOriginal(DataVO data) throws Exception;

    @Insert("insert into daily values (\"${date}\", \"${branchName}\", \"${count}\", \"${maxPayment}\"," +
            "\"${minPayment}\", \"${totalPayment}\", \"${adCount}\", \"${adTotalPayment}\"," +
            "\"${adMaxPayment}\", \"${adMinPayment}\", \"${productName}\" , \"${male}\" , \"${female}\"," +
            "\"${pointCount}\", \"${maxPointPayment}\", \"${minPointPayment}\", \"${totalPointPayment}\", " +
            "\"${installmentCount}\", \"${maxInstallment}\", \"${minInstallment}\", \"${totalInstallment}\", " +
            "\"${savedPointCount}\", \"${maxSavedPoint}\", \"${minSavedPoint}\", \"${totalSavedPoint}\")")
    public boolean insertDay(ReducedDataVO data) throws Exception;

    @Insert("insert into monthly values (\"${date}\", \"${branchName}\", \"${count}\", \"${maxPayment}\"," +
            "\"${minPayment}\", \"${totalPayment}\", \"${adCount}\", \"${adTotalPayment}\"," +
            "\"${adMaxPayment}\", \"${adMinPayment}\", \"${productName}\", \"${male}\" , \"${female}\"," +
            "\"${pointCount}\", \"${maxPointPayment}\", \"${minPointPayment}\", \"${totalPointPayment}\", " +
            "\"${installmentCount}\", \"${maxInstallment}\", \"${minInstallment}\", \"${totalInstallment}\", " +
            "\"${savedPointCount}\", \"${maxSavedPoint}\", \"${minSavedPoint}\", \"${totalSavedPoint}\")")
    public boolean insertMonth(ReducedDataVO data) throws Exception;

    @Insert("insert into yearly values (\"${date}\", \"${branchName}\", \"${count}\", \"${maxPayment}\"," +
            "\"${minPayment}\", \"${totalPayment}\", \"${adCount}\", \"${adTotalPayment}\"," +
            "\"${adMaxPayment}\", \"${adMinPayment}\", \"${productName}\", \"${male}\" , \"${female}\"," +
            "\"${pointCount}\", \"${maxPointPayment}\", \"${minPointPayment}\", \"${totalPointPayment}\", " +
            "\"${installmentCount}\", \"${maxInstallment}\", \"${minInstallment}\", \"${totalInstallment}\", " +
            "\"${savedPointCount}\", \"${maxSavedPoint}\", \"${minSavedPoint}\", \"${totalSavedPoint}\")")
    public boolean insertYear(ReducedDataVO data) throws Exception;

    @Insert("insert into total values (\"${date}\", \"${branchName}\", \"${count}\", \"${maxPayment}\"," +
            "\"${minPayment}\", \"${totalPayment}\", \"${adCount}\", \"${adTotalPayment}\"," +
            "\"${adMaxPayment}\", \"${adMinPayment}\", \"${productName}\", \"${male}\" , \"${female}\"," +
            "\"${pointCount}\", \"${maxPointPayment}\", \"${minPointPayment}\", \"${totalPointPayment}\", " +
            "\"${installmentCount}\", \"${maxInstallment}\", \"${minInstallment}\", \"${totalInstallment}\", " +
            "\"${savedPointCount}\", \"${maxSavedPoint}\", \"${minSavedPoint}\", \"${totalSavedPoint}\")")
    public boolean insertTotal(ReducedDataVO data) throws Exception;

    @Select("select * from total order by branchName, totalPayment desc")
    public ArrayList<ReducedDataVO> getTotal() throws Exception;

    @Select("select * from total where branchName = \"${branchName}\" order by totalPayment desc")
    public ArrayList<ReducedDataVO> getFiltered(@Param("branchName") String branchName) throws Exception;

    @Select("select * from daily where date = \"${date}\" order by date asc, totalPayment desc")
    public ArrayList<ReducedDataVO> getDaily( @Param("date") String date) throws Exception;

    @Select("select * from daily where branchName = \"${branchName}\" and " +
            "date = \"${date}\" order by date asc, totalPayment desc")
    public ArrayList<ReducedDataVO> getDailyBranchFiltered(@Param("branchName") String branchName,
                                                           @Param("date") String date) throws Exception;

    @Select("select * from monthly where date = \"${date}\" order by date asc, totalPayment desc")
    public ArrayList<ReducedDataVO> getMonthly(@Param("date") String date) throws Exception;

    @Select("select * from monthly where branchName = \"${branchName}\" and " +
            "date = \"${date}\" order by date asc, totalPayment desc")
    public ArrayList<ReducedDataVO> getMonthlyBranchFiltered(@Param("branchName") String branchName,
                                                            @Param("date") String date) throws Exception;

    @Select("select * from yearly where date = \"${date}\" order by date asc, totalPayment desc")
    public ArrayList<ReducedDataVO> getYearly(@Param("date") String date) throws Exception;

    @Select("select * from yearly where branchName = \"${branchName}\" and " +
            "date = \"${date}\" order by date asc, totalPayment desc")
    public ArrayList<ReducedDataVO> getYearlyBranchFiltered(@Param("branchName") String branchName,
                                                            @Param("date") String date) throws Exception;

    @Select("select * from total order by totalPayment desc limit 5")
    public ArrayList<ReducedDataVO> getTotalProduct() throws Exception;

    @Select("select * from total where branchName = \"${branchName}\" order by totalPayment desc limit 5")
    public ArrayList<ReducedDataVO> getFilteredProduct(@Param("branchName") String branchName) throws Exception;


    @Select("select * from yearly where date = \"${date}\" order by totalPayment desc limit 5")
    public ArrayList<ReducedDataVO> getYearlyProduct(@Param("date") String date) throws Exception;

    @Select("select * from yearly where branchName = \"${branchName}\" and " +
            "date = \"${date}\" order by totalPayment desc limit 5")
    public ArrayList<ReducedDataVO> getYearlyBranchFilteredProduct(@Param("branchName") String branchName,
                                                            @Param("date") String date) throws Exception;

    @Select("select * from yearly where date = \"${date}\" order by totalPayment desc limit 5")
    public ArrayList<ReducedDataVO> getMonthlyProduct(@Param("date") String date) throws Exception;

    @Select("select * from yearly where branchName = \"${branchName}\" and " +
            "date = \"${date}\" order by totalPayment desc limit 5")
    public ArrayList<ReducedDataVO> getMonthlyBranchFilteredProduct(@Param("branchName") String branchName,
                                                                   @Param("date") String date) throws Exception;

    @Select("select distinct branchName from total order by branchName")
    public ArrayList<String> getBranchList() throws Exception;

    @Select("select date, sum(totalPayment) from yearly group by date order by date asc")
    public ArrayList<GraphVO> getYearlyGraph() throws Exception;

    @Select("select date, sum(totalPayment) from yearly where branchName = \"${branchName}\" group by date order by date asc")
    public ArrayList<GraphVO> getFilteredYearlyGraph(@Param("branchName") String branchName) throws Exception;


    @Select("select date, sum(totalPayment) from monthly where date like \"${date}%\" group by date order by date asc")
    public ArrayList<GraphVO> getMonthlyGraph(@Param("date") String date) throws Exception;

    @Select("select date, sum(totalPayment) from daily where branchName = \"${branchName}\"" +
            " and date like \"${date}%\" group by date order by date asc")
    public ArrayList<GraphVO> getMonthlyFilteredGraph(@Param("branchName") String branchName,
                                                      @Param("date") String date) throws Exception;

    @Select("select date, sum(totalPayment) from monthly where date like \"${date}%\" group by date order by date asc")
    public ArrayList<GraphVO> getDailyGraph(@Param("date") String date) throws Exception;

    @Select("select date, sum(totalPayment) from daily where branchName = \"${branchName}\"" +
            " and date like \"${date}%\" group by date order by date asc")
    public ArrayList<GraphVO> getDailyFilteredGraph(@Param("branchName") String branchName,
                                                      @Param("date") String date) throws Exception;
}

