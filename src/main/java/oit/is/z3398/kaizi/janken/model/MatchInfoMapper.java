package oit.is.z3398.kaizi.janken.model;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface MatchInfoMapper {

  @Insert("INSERT INTO matchinfo (user1, user2, user1Hand, isActive) VALUES (#{user1}, #{user2}, #{user1Hand}, #{isActive});")
  @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
  void insertMatchInfo(MatchInfo matchInfo);

  @Select("SELECT * FROM matchinfo WHERE isActive = TRUE")
  ArrayList<MatchInfo> selectAllActiveMatchInfo();

  // 相手(user1)が自分(user2)を指名して待っている、アクティブな行を探す
  @Select("SELECT * FROM matchinfo WHERE isActive = TRUE AND user1 = #{user1} AND user2 = #{user2} LIMIT 1")
  MatchInfo selectActiveMatchInfo(@Param("user1") int user1, @Param("user2") int user2);

  // 待機状態を解除する
  @Update("UPDATE matchinfo SET isActive = FALSE WHERE id = #{id}")
  void updateIsActiveFalse(int id);
}
