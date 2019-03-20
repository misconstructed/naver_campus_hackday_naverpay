package com.naver_pay.mapper;

import com.naver_pay.VO.UserVO;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

   @Insert("insert into user(id, password, name, state) values (\"${id}\", \"${password}\", \"${name}\", \"${state}\")")
   public boolean insertUser(@Param("id") String id,
                            @Param("password") String password,
                            @Param("name") String name,
                            @Param("state") int state) throws Exception;

   @Select("select * from user where id = \"${id}\" and password = \"${password}\"")
   public UserVO selectUser(@Param("id") String id,
                            @Param("password") String password) throws  Exception;
}
