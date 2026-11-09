package oit.is.z3398.kaizi.janken.model;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface MatchMapper {
  @Select("select id, user1, user2, user1Hand, user2Hand from matches")
  ArrayList<Match> selectAllMatches();

  @Insert("INSERT INTO matches (user1, user2, user1Hand, user2Hand, isActive) VALUES (#{user1}, #{user2}, #{user1Hand}, #{user2Hand}, #{isActive});")
  @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
  void insertMatch(Match match);

  // 自分(user1 = 先手)の、まだ表示していない試合結果を探す
  @Select("SELECT * FROM matches WHERE isActive = TRUE AND user1 = #{user1} LIMIT 1")
  Match selectActiveMatchByUser1(int user1);

  // 表示し終わった試合を FALSE にする
  @Update("UPDATE matches SET isActive = FALSE WHERE id = #{id}")
  void updateIsActiveFalse(int id);
}
