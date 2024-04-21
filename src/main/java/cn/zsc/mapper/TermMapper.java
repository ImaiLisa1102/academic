package cn.zsc.mapper;

import cn.zsc.entity.Term;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TermMapper {

    @Select("SELECT * FROM term WHERE term_Id = #{termId}")
    @Results({
            @Result(property = "termId", column = "term_id"),
            @Result(property = "termName", column = "term_name"),
            @Result(property = "termStartDate", column = "term_start_date"),
            @Result(property = "termEndDate", column = "term_end_date")
    })
    Term findById(String termId);
    @Select("SELECT * FROM term WHERE term_name = #{termName}")
    Term findTermByName(String termName);

    @Insert("INSERT INTO term(term_id, term_name, term_start_date, term_end_date) VALUES(#{termId}, #{termName}, #{termStartDate}, #{termEndDate})")
    void insertTerm(Term term);

    @Select("SELECT * FROM term LIMIT #{size} OFFSET #{offset}")
    @Results({
            @Result(property = "termId", column = "term_id"),
            @Result(property = "termName", column = "term_name"),
            @Result(property = "termStartDate", column = "term_start_date"),
            @Result(property = "termEndDate", column = "term_end_date")
    })
    List<Term> findAll(@Param("offset") int offset, @Param("size") int size);

    @Select("SELECT COUNT(*) FROM term")
    int count();

    @Delete("DELETE FROM term WHERE term_id=#{termId}")
    void deleteTerm(String termId);

    @Select("SELECT term_Id FROM term")
    List<String> selectAllTermIds();

    @Update("UPDATE term SET term_name = #{termName}, term_start_date = #{termStartDate}, " +
            "term_end_date = #{termEndDate} WHERE term_id = #{termId}")
    int updateTerm(Term term);


    @Insert({
            "<script>",
            "INSERT INTO term (term_id, term_name, term_start_date, term_end_date)",
            "VALUES ",
            "<foreach collection='terms' item='term' index='index' separator=','>",
            "(#{term.termId}, #{term.termName}, #{term.termStartDate}, #{term.termEndDate})",
            "</foreach>",
            "</script>"
    })
    void batchInsert(@Param("terms") List<Term> terms);


}
