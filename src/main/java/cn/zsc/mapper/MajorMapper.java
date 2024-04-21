package cn.zsc.mapper;

import cn.zsc.entity.Major;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MajorMapper {

    @Select("SELECT * FROM major WHERE major_Id = #{majorId}")
    @Results({
            @Result(property = "majorId", column = "major_id"),
            @Result(property = "majorName", column = "major_name"),
            @Result(property = "major_collegeId", column = "major_college_id")
    })
    Major findById(int majorId);

    @Select("SELECT major_id FROM major WHERE major_name = #{majorName}")
    Integer findMajorIdByName(@Param("majorName") String majorName);

    @Select("SELECT * FROM major WHERE major_name = #{majorName}")
    Major findMajorByName(String majorName);

    @Insert("INSERT INTO major(major_id, major_name, major_college_id) VALUES(#{majorId}, #{majorName}, #{major_collegeId})")
    void insertMajor(Major major);

    @Select("SELECT * FROM major LIMIT #{size} OFFSET #{offset}")
    @Results({
            @Result(property = "majorId", column = "major_id"),
            @Result(property = "majorName", column = "major_name"),
            @Result(property = "major_collegeId", column = "major_college_id")
    })
    List<Major> findAll(@Param("offset") int offset, @Param("size") int size);

    @Select("SELECT COUNT(*) FROM major")
    int count();

    @Delete("DELETE FROM major WHERE major_id=#{majorId}")
    void deleteMajor(int majorId);

    @Select("SELECT major_name FROM major")
    List<String> getAllMajorNames();

    @Update("UPDATE major SET major_name = #{majorName}, major_college_id = #{major_collegeId} " +
            "WHERE major_id = #{majorId}")
    int updateMajor(Major major);

    @Insert({
            "<script>",
            "INSERT INTO major (major_id, major_name, major_college_id)",
            "VALUES",
            "<foreach collection='majors' item='major' index='index' separator=','>",
            "(#{major.majorId}, #{major.majorName}, #{major.major_collegeId})",
            "</foreach>",
            "</script>"
    })
    void batchInsert(@Param("majors") List<Major> majors);


}
