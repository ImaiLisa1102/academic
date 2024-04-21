package cn.zsc.mapper;

import cn.zsc.entity.College;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface CollegeMapper {

  @Select("SELECT * FROM college WHERE college_id = #{collegeId}")
  College findCollegeById(int collegeId);

  @Insert("INSERT INTO college(college_name, college_leader) VALUES(#{collegeName}, #{collegeLeader})")
  void insertCollege(College college);

  @Select("SELECT * FROM college LIMIT #{size} OFFSET #{offset}")
  @Results({
          @Result(property = "collegeId", column = "college_id"),
          @Result(property = "collegeName", column = "college_name"),
          @Result(property = "collegeLeader", column = "college_leader")
  })
  List<College> findAll(@Param("offset") int offset, @Param("size") int size);

  @Select("SELECT COUNT(*) FROM college")
  int count();

  @Delete("DELETE FROM college WHERE college_id=#{collegeId}")
  void deleteCollege(int collegeId);



  @Update("UPDATE college SET college_name = #{collegeName}, " +
          "college_leader = #{collegeLeader} " +
          "WHERE college_id = #{collegeId}")
  int updateCollege(College college);


  @Insert({
          "<script>",
          "INSERT INTO college (college_id, college_name, college_leader)",
          "VALUES ",
          "<foreach collection='colleges' item='college' separator=','>",
          "(#{college.collegeId}, #{college.collegeName}, #{college.collegeLeader})",
          "</foreach>",
          "</script>"
  })
  void batchInsert(@Param("colleges") List<College> colleges);


}
