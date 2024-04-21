package cn.zsc.mapper;

import cn.zsc.entity.Classroom;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ClassroomMapper {

    @Select("SELECT * FROM classroom WHERE classroom_id = #{classroom_id}")
    @Results({
            @Result(property = "classroom_id", column = "classroom_id"),
            @Result(property = "classroom_name", column = "classroom_name"),
            @Result(property = "classroom_capacity", column = "classroom_capacity"),
            @Result(property = "classroom_state", column = "classroom_state")
    })
    Classroom findById(int classroom_id);

    @Delete("DELETE FROM classroom WHERE classroom_id = #{classroom_id}")
    void deleteClassroom(@Param("classroom_id") Long classroom_id);

    @Select("SELECT * FROM classroom LIMIT #{size} OFFSET #{offset}")
    @Results({
            @Result(property = "classroom_id", column = "classroom_id"),
            @Result(property = "classroom_name", column = "classroom_name"),
            @Result(property = "classroom_capacity", column = "classroom_capacity"),
            @Result(property = "classroom_state", column = "classroom_state")
    })
    List<Classroom> findAll(@Param("offset") int offset, @Param("size") int size);


    @Select("SELECT COUNT(*) FROM classroom")
    int count();


    @Insert("INSERT INTO classroom (classroom_name, classroom_capacity, classroom_id, classroom_state) VALUES (#{classroom_name}, #{classroom_capacity}, #{classroom_id}, #{classroom_state})")
    void insertClassroom(Classroom classroom);

    @Select("SELECT classroom_name, classroom_capacity FROM classroom WHERE classroom_state = '可用'")
    @Results({
            @Result(property = "classroom_name", column = "classroom_name"),
            @Result(property = "classroom_capacity", column = "classroom_capacity"),
            @Result(property = "classroom_state", column = "classroom_state")
    })
    List<Classroom> getAllAvailableClassrooms();

    @Select("SELECT classroom_capacity FROM classroom WHERE classroom_name = #{classroon_name}")
    int getCapacity(String classroom_name);

    @Update("UPDATE classroom SET classroom_name = #{classroom_name}, " +
            "classroom_capacity = #{classroom_capacity}, " +
            "classroom_state = #{classroom_state} " +
            "WHERE classroom_id = #{classroom_id}")
    int updateClassroom(Classroom classroom);

    @Insert({
            "<script>",
            "INSERT INTO classroom (classroom_id, classroom_name, classroom_capacity, classroom_state) VALUES ",
            "<foreach collection='classrooms' item='classroom' index='index' separator=','>",
            "(#{classroom.classroom_id}, #{classroom.classroom_name}, #{classroom.classroom_capacity}, #{classroom.classroom_state})",
            "</foreach>",
            "</script>"
    })
    void batchInsert(@Param("classrooms") List<Classroom> classrooms);



}