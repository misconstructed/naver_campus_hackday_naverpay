package com.naver_pay.mapper;

import com.naver_pay.VO.DataVO;
import com.naver_pay.VO.ReducedDataVO;
import com.naver_pay.VO.UserVO;
import org.apache.ibatis.annotations.*;

@Mapper
public interface RealTimeMapper {

   @Insert("insert into original values (\"${date}\", \"${id}\", \"${userId}\", \"${userBirth}\"," +
           "\"${userSex}\", \"${branchName}\", \"${productName}\", \"${productType}\"," +
           "\"${paymentState}\", \"${paymentType}\", \"${mainPaymentType}\" , \"${paymentAmount}\" ," +
           "\"${mainPaymentAmount}\", \"${pointAmount}\", \"${receiptNumber}\", \"${installmentMonth}\", " +
           "\"${adId}\", \"${savedPoint}\")")
   public boolean insertOriginal(DataVO data) throws Exception;

   @Select("select * from total where branchName = \"${branchName}\" and productName = \"${productName}\"")
   public ReducedDataVO getTotal(@Param("branchName") String branchName,
                                 @Param("productName") String productName) throws Exception;

   @Update("update total set count = \"${count}\", maxPayment = \"${maxPayment}\", minPayment = \"${minPayment}\", totalPayment = \"${totalPayment}\"" +
           ", adCount = \"${adCount}\", adTotalPayment = \"${adTotalPayment}\", adMaxPayment = \"${adMaxPayment}\", adMinPayment = \"${adMinPayment}\"" +
           ", pointCount = \"${pointCount}\", maxPointPayment = \"${maxPointPayment}\", minPointPayment = \"${minPointPayment}\", totalPointPayment = \"${totalPointPayment}\"" +
           ", installmentCount = \"${installmentCount}\", maxinstallment = \"${maxInstallment}\", minInstallment = \"${minInstallment}\", totalInstallment = \"${totalInstallment}\"" +
           ", savedPointCount = \"${savedPointCount}\", maxSavedPoint = \"${maxSavedPoint}\", minSavedPoint = \"${minSavedPoint}\", totalSavedPoint = \"${totalSavedPoint}\"" +
           "male = \"${male}\", female = \"${female}\" where branchName = \"${branchName}\" and productName = \"${productName}\"")
   public boolean setTotal(ReducedDataVO data) throws Exception;


   @Select("select * from year where branchName = \"${branchName}\" and productName = \"${productName}\" and date = \"${date}\"")
   public ReducedDataVO getYear(@Param("branchName") String branchName,
                                 @Param("productName") String productName,
                                @Param("date") String date) throws Exception;

   @Select("select * from month where branchName = \"${branchName}\" and productName = \"${productName}\" and date = \"${date}\"")
   public ReducedDataVO getMonth(@Param("branchName") String branchName,
                                 @Param("productName") String productName,
                                 @Param("date") String date) throws Exception;

   @Select("select * from day where branchName = \"${branchName}\" and productName = \"${productName}\" and date = \"${date}\"")
   public ReducedDataVO getDay(@Param("branchName") String branchName,
                                 @Param("productName") String productName,
                               @Param("date") String date) throws Exception;

}
