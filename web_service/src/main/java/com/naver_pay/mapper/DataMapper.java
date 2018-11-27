package com.naver_pay.mapper;

import com.naver_pay.VO.ReducedDataVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

@Mapper
public interface DataMapper {

    @Insert("insert into daily values (\"${date}\", \"${branchName}\", \"${count}\", \"${maxPayment}\"," +
            "\"${minPayment}\", \"${totalPayment}\", \"${adCount}\", \"${adTotalPayment}\"," +
            "\"${adMaxPayment}\", \"${adMinPayment}\", \"${productName}\")")
    public boolean insertDay(ReducedDataVO data) throws Exception;

    @Insert("insert into monthly values (\"${date}\", \"${branchName}\", \"${count}\", \"${maxPayment}\"," +
            "\"${minPayment}\", \"${totalPayment}\", \"${adCount}\", \"${adTotalPayment}\"," +
            "\"${adMaxPayment}\", \"${adMinPayment}\", \"${productName}\")")
    public boolean insertMonth(ReducedDataVO data) throws Exception;

    @Insert("insert into yearly values (\"${date}\", \"${branchName}\", \"${count}\", \"${maxPayment}\"," +
            "\"${minPayment}\", \"${totalPayment}\", \"${adCount}\", \"${adTotalPayment}\"," +
            "\"${adMaxPayment}\", \"${adMinPayment}\", \"${productName}\")")
    public boolean insertYear(ReducedDataVO data) throws Exception;

    @Insert("insert into total values (\"${date}\", \"${branchName}\", \"${count}\", \"${maxPayment}\"," +
            "\"${minPayment}\", \"${totalPayment}\", \"${adCount}\", \"${adTotalPayment}\"," +
            "\"${adMaxPayment}\", \"${adMinPayment}\", \"${productName}\")")
    public boolean insertTotal(ReducedDataVO data) throws Exception;

    @Select("select * from total")
    public ArrayList<ReducedDataVO> getTotal() throws Exception;

    @Select("select * from total where branchName = \"${branchName}\"")
    public ArrayList<ReducedDataVO> getFiltered(@Param("branchName") String branchName) throws Exception;

    @Select("select * from daily")
    public ArrayList<ReducedDataVO> getDaily() throws Exception;

}

