package oit.is.z3398.kaizi.janken.model;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
  @Select("SELECT * from Users")
  ArrayList<User> selectAllByUserName();

  @Select("SELECT * FROM Users WHERE userName = #{userName}")
  User selectByName(String userName);

  @Select("SELECT * FROM users WHERE id = #{id}")
  User selectById(int id);

}
